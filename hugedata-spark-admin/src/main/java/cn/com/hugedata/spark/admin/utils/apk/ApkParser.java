package cn.com.hugedata.spark.admin.utils.apk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.hugedata.spark.commons.exception.ServiceException;

public class ApkParser {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ApkParser.class);
    
    /**
     * apktool.jar相对位置.
     */
    private static final String APKTOOL_PATH = "/WEB-INF/apktool/apktool.jar";
    
    /**
     * 用于读取APK中字符串资源的路径.
     */
    private static final String[] APK_STRING_PATHS = {
        "values-zh-rCN",
        "values-zh-rHK",
        "values-zh-rTW",
        "values-zh",
        "values"
    };
    
    /**
     * ServletContext实例.
     */
    private ServletContext servletContext;
    
    /**
     * 保存APK中解析的字符串.
     */
    private Map<String, String> stringResources;
    
    /**
     * 保存解析的APK信息.
     */
    private ApkInfo apkInfo = new ApkInfo();
    
    /**
     * APK解析后的目录.
     */
    private File apktoolOutputDir = null;
    
    /**
     * 用于统计解析APK的时间
     */
    private StopWatch stopWatch;
    
    /**
     * 统计的时间段标题.
     */
    private List<String> summarySubjects;
    
    /**
     * 统计的时间点.
     */
    private List<Long> summaryTimes;
    
    /**
     * 创建ApkParser.
     * @param servletContext ServletContext实例
     */
    public ApkParser(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 解析APK文件，并返回解析结果.
     * @param file  APK文件
     * @return      解析结果
     * @throws ServiceException
     */
    public ApkInfo parse(File file) throws ServiceException {
        InputStream procStdout = null;
        InputStream procStderr = null;
        try {
            LOGGER.info("Parsing apk: {}", file.getAbsolutePath());
            
            stopWatch = new StopWatch();
            stopWatch.start();
            summarySubjects = new ArrayList<String>();
            summaryTimes = new ArrayList<Long>();
            
            // ApkTool输出目录
            String unpackDir = makeUnpackDir();
            LOGGER.info("Unpack apk to: {}", unpackDir);
            
            // apktool.jar位置
            String apkToolJar = servletContext.getRealPath(APKTOOL_PATH);
            LOGGER.info("Using apktool: {}", apkToolJar);
            markTimeSummary("Prepare");
            
            // java可执行文件位置
            String javaExec = JavaExecutableLocator.findJavaExecutable();
            if (javaExec == null) {
                LOGGER.error("Java executable not found");
                throw new ServiceException("Java executable not found", 
                        "client_update.apk_parser.no_java_executable");
            }
            
            // ApkTool执行
            ProcessBuilder pb = new ProcessBuilder(javaExec, "-jar", apkToolJar, "d", file.getAbsolutePath(),
                    "-o", unpackDir, "--no-src");
            Process proc = pb.start();
            procStdout = proc.getInputStream();
            procStderr = proc.getErrorStream();
            int exitCode = proc.waitFor();
            markTimeSummary("Unpack apk (apktool)");
            
            // 判断ApkTool执行结果
            LOGGER.info("Apktool exited with code: {}", exitCode);
            LOGGER.info("Apktool stdout: {}", IOUtils.toString(procStdout));
            LOGGER.info("Apktool stderr: {}", IOUtils.toString(procStderr));
            if (exitCode != 0) {
                LOGGER.error("Failed to execute apktool, exit code: {}", exitCode);
                throw new ServiceException("Failed to execute apktool",
                        "client_update.apk_parser.failed_to_decode_apk");
            }
            LOGGER.info("Apktool execution succeeded.");
            
            // ApkTool输出目录
            apktoolOutputDir = new File(unpackDir);
            if (!apktoolOutputDir.exists()) {
                LOGGER.error("Apktool unpack directory not exist: {}", apktoolOutputDir.getAbsolutePath());
                throw new ServiceException("Apktool unpack directory not exist", 
                        "client_update.apk_parser.failed_to_decode_apk");
            }
            apktoolOutputDir.deleteOnExit();
            markTimeSummary("Before read unpacked");

            // 解析处理
            parseStringResources();
            markTimeSummary("Parse string resoures");
            parseApktoolYml();
            markTimeSummary("Parse apktool.yml");
            parseAndroidManifest();
            markTimeSummary("Parse manifest (with icons)");
            
            // 删除临时文件
            try {
                FileUtils.deleteDirectory(apktoolOutputDir);
            } catch (IOException e) {
                LOGGER.warn("Failed to delete apktool output directory.", e);
            }
            markTimeSummary("Clean up");
            
            apkInfo.setApkFileSize(file.length());
            logTimeSummary();
            return apkInfo;
            
        } catch (IOException e) {
            LOGGER.error("Failed to parse apk file", e);
            throw new ServiceException("Failed to parse apk file", "client_update.apk_parser.exception_decode_apk");
        
        } catch (InterruptedException e) {
            LOGGER.error("Failed to parse apk file. (interrupted)", e);
            throw new ServiceException("Failed to parse apk file", "client_update.apk_parser.exception_decode_apk");
    
        } finally {
            IOUtils.closeQuietly(procStdout);
            IOUtils.closeQuietly(procStderr);
        }
    }
    
    /**
     * 将ApkInfo中的字符串资源名称解析为实际字符串.
     */
    private void parseStringResources() throws ServiceException {
        LOGGER.info("Parsing apk strings");
        stringResources = new HashMap<String, String>();
        
        for (String path : APK_STRING_PATHS) {
            LOGGER.info("Loading strings from: {}", path);
            File valuesDir = new File(apktoolOutputDir, "res" + File.separatorChar + path);
            if (!valuesDir.exists() || !valuesDir.isDirectory()) {
                continue;
            }
            for (File xmlFile : valuesDir.listFiles()) {
                if (!xmlFile.getName().endsWith(".xml")) {
                    continue;
                }
                parseStringXml(xmlFile);
            }
        }
        
        LOGGER.info("Strings parsed: {}", stringResources.size());
    }
    
    /**
     * 解析一个包含字符串信息的XML文件.
     */
    private void parseStringXml(File file) throws ServiceException {
        try {
            if (!file.exists()) {
                return;
            }
            LOGGER.info("Loading strings from file: {}", file.getAbsolutePath());
            
            // 读取文件
            Document document = new SAXReader().read(file);
            Element rootElement = document.getRootElement();
            
            // 判断根节点类型，如果不为<resources>，忽略整个文件
            if (!"resources".equals(rootElement.getName())) {
                return;
            }
            
            // 读取所有<string>节点
            @SuppressWarnings("unchecked")
            List<Element> stringElements = rootElement.elements("string");
            for (Element stringElement : stringElements) {
                String name = stringElement.attributeValue("name");
                if (!stringResources.containsKey(name)) {
                    stringResources.put(name, stringElement.getText());
                }
            }
            
        } catch (DocumentException e) {
            // 发生错误直接忽略
            LOGGER.error("Failed to parse xml file: {}", file.getAbsolutePath());
        }
    }
    
    /**
     * 解析AndroidManifeset.xml.
     */
    private void parseAndroidManifest() throws ServiceException {
        try {
            String path = apktoolOutputDir.getAbsolutePath() + File.separatorChar + "AndroidManifest.xml";
            Document doc = new SAXReader().read(new File(path));

            // 根节点：<manifest package="">
            Element manifestElement = doc.getRootElement();
            apkInfo.setPackageName(manifestElement.attributeValue("package"));
            
            // <application label="" icon="">
            Element applicationElement = manifestElement.element("application");
            apkInfo.setApplicationName(getStringResource(applicationElement.attributeValue("label")));
            apkInfo.setApplicationIcon(getDrawableResource(applicationElement.attributeValue("icon"))); 
            
        } catch (DocumentException e) {
            LOGGER.error("Failed to read AndroidManifest.xml", e);
            throw new ServiceException("\"Failed to read AndroidManifest.xml",
                    "client_update.apk_parser.failed_to_decode_manifest");
        }
    }
    
    /**
     * 解析ApkTool输出的apktool.yml.
     */
    private void parseApktoolYml() throws ServiceException {
        InputStream in = null;
        try {
            String path = apktoolOutputDir.getAbsolutePath() + File.separatorChar + "apktool.yml";
            in = new FileInputStream(path);
            List<String> lines = IOUtils.readLines(in);
            for (String line : lines) {
                int pos = line.indexOf(":");
                if (pos <= 0) {
                    continue;
                }
                String name = line.substring(0, pos).trim();
                String value = line.substring(pos + 1).trim();
                value = StringUtils.strip(value, "\'\"");
                if ("minSdkVersion".equals(name)) {
                    apkInfo.setMinSdkVersion(Integer.parseInt(value));
                } else if ("targetSdkVersion".equals(name)) {
                    apkInfo.setTargetSdkVersion(Integer.parseInt(value));
                } else if ("versionCode".equals(name)) {
                    apkInfo.setVersionCode(Integer.parseInt(value));
                } else if ("versionName".equals(name)) {
                    apkInfo.setVersionName(value);
                }
            }
            
        } catch (IOException e) {
            LOGGER.error("Failed to parse apktool.yml", e);
            throw new ServiceException("Failed to parse apktool.yml", 
                    "client_update.apk_parser.failed_to_decode_yml");
            
        } catch (NumberFormatException e) {
            LOGGER.error("Failed to parse apktool.yml number");
            throw new ServiceException("Failed to parse apktool.yml",
                    "client_update.apk_parser.failed_to_decode_yml");
            
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    /**
     * 生成用于ApkTool输出的目录路径.
     * @return 用于ApkTool输出的目录路径
     * @throws ServiceException  
     */
    private String makeUnpackDir() throws ServiceException {
        try {
            File temp = File.createTempFile(RandomStringUtils.randomAlphanumeric(32), "");
            temp.deleteOnExit();
            String path = temp.getAbsolutePath();
            temp.delete();
            
            int pos = path.lastIndexOf(File.separatorChar);
            if (pos <= 0) {
                LOGGER.error("Failed to create unpack dir from temp file: {}", path);
                throw new ServiceException("Failed to create unpack dir from temp file", 
                        "client_update.apk_parser.failed_create_tempdir");
            }
            
            String tempDir = path.substring(0, pos);
            LOGGER.debug("Temporary directory found: {}", tempDir);
            
            return tempDir + File.separatorChar + RandomStringUtils.randomAlphanumeric(32);
            
        } catch (IOException e) {
            LOGGER.error("Failed to create unpack dir", e);
            throw new ServiceException("Failed to create unpack dir from temp file", 
                    "client_update.apk_parser.failed_create_tempdir");
        }
    }
    
    /**
     * 获取字符串资源值，如果找不到资源，返回name.
     * @param name 字符串资源名称，可以为"@string/name"的格式
     * @return     如果找到，返回字符串.
     *             如果没有找到，返回name，如果name包含"@string/"，只返回"/"之后的内容
     */
    private String getStringResource(String name) {
        if (stringResources == null) {
            return name;
        }
        if (name == null) {
            return "";
        }
        if (name.startsWith("@string/")) {
            name = name.substring("@string/".length());
        }
        String text = stringResources.get(name);
        if (StringUtils.isEmpty(text)) {
            return name;
        } else {
            return text;
        }
    }
    
    /**
     * 获取图片资源，并返回图片临时文件.
     * @param name 图片资源名称，可以为"@drawable/name"的格式
     * @return     图片临时文件(已设置deleteOnExit)，使用后可以直接删除
     */
    private File getDrawableResource(String name) {
        InputStream imageFileIn = null;
        OutputStream imageFileOut = null;
        try {
            // 判断name为空
            if (StringUtils.isEmpty(name)) {
                return null;
            }
            
            // 去掉name中的"@drawable/"部分
            if (name.startsWith("@drawable/")) {
                name = name.substring("@drawable/".length());
            }
            final String imageFilename = name;
            
            // 获取res目录
            File resDir = new File(apktoolOutputDir, "res");
            if (!resDir.exists() || !resDir.isDirectory()) {
                return null;
            }
            
            // 图片文件相关的Filter
            FilenameFilter drawableDirFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith("drawable");
                }
            };
            FilenameFilter drawableFileFilter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith(imageFilename);
                }
            };
            
            // 获取文件
            long maxFileSize = 0;  // 最大图片文件的大小
            File imageFile = null; // 最大图片文件
            
            for (File drawableDir : resDir.listFiles(drawableDirFilter)) {
                for (File drawableFile : drawableDir.listFiles(drawableFileFilter)) {
                    long size = drawableFile.length();
                    if (size > maxFileSize) {
                        maxFileSize = size;
                        imageFile = drawableFile;
                    }
                }
            }
            
            // 复制文件到临时目录
            if (imageFile == null) {
                return null;
            }
            
            // 获取文件后缀
            String tempFilename = RandomStringUtils.randomAlphanumeric(32);
            int extPos = imageFile.getName().lastIndexOf('.');
            if (extPos > 0) {
                tempFilename += imageFile.getName().substring(extPos);
            }

            imageFileIn = new FileInputStream(imageFile);
            File tempFile = File.createTempFile(tempFilename, "");
            imageFileOut = new FileOutputStream(tempFile);
            IOUtils.copy(imageFileIn, imageFileOut);
            
            tempFile.deleteOnExit();
            return tempFile;
            
        } catch (IOException e) {
            LOGGER.error("Failed to parse app icon", e);
            return null;
            
        } finally {
            IOUtils.closeQuietly(imageFileIn);
            IOUtils.closeQuietly(imageFileOut);
        }
    }
    
    /**
     * 记录一个时间点.
     * @param subject 时间点操作内容(当前时间之前的操作)
     */
    private void markTimeSummary(String subject) {
        summarySubjects.add(subject);
        summaryTimes.add(stopWatch.getTime());
    }
    
    /**
     * 输出时间统计信息.
     */
    private void logTimeSummary() {
        for (int i = 0; i < Math.min(summarySubjects.size(), summaryTimes.size()); i++) {
            long time = summaryTimes.get(i);
            if (i > 0) {
                time = summaryTimes.get(i) - summaryTimes.get(i - 1);
            }
            LOGGER.info("Time summary: {} -> {}", summarySubjects.get(i), time);
        }
    }
}
