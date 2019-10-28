package cn.com.hugedata.spark.admin.utils.apk;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于查找Java可执行文件路径.
 * 
 * @author gaopeng
 */
public class JavaExecutableLocator {
    
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaExecutableLocator.class);
    
    /**
     * 找到的Java可执行文件.
     */
    private static String javaExecutablePath = null;
    
    /**
     * 是否已经找到(或未找到)Java可执行文件.
     */
    private static boolean javaExecutablePathFound = false;

    /**
     * 查找Java可执行文件路径.
     * @return Java可执行文件路径，如果没有找到，返回null
     */
    public static String findJavaExecutable() {
        // 如果已经找到，直接返回
        if (javaExecutablePathFound) {
            LOGGER.info("Java executable path found in cache: {}", javaExecutablePath);
            return javaExecutablePath;
        }
        
        // 从PATH查找
        String execPath = findInPath();
        if (StringUtils.isNotEmpty(execPath)) {
            LOGGER.info("Java executable found: {}", execPath);
            javaExecutablePathFound = true;
            javaExecutablePath = execPath;
            return execPath;
        }
        
        // 从当前进程信息查找
        execPath = findFromProcessInfo();
        if (StringUtils.isNotEmpty(execPath)) {
            LOGGER.info("Java executable found: {}", execPath);
            javaExecutablePathFound = true;
            javaExecutablePath = execPath;
            return execPath;
        }
        
        return null;
    }
    
    /**
     * 从PATH查找Java可执行文件路径.
     * @return 如果找到，返回"java"，否则返回null
     */
    private static String findInPath() {
        try {
            Runtime.getRuntime().exec("java");
            return "java";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 根据当前进程信息查找Java可执行文件路径.
     * @return 
     */
    private static String findFromProcessInfo() {
        try {
            // 获取pid
            Integer pid = findCurrentProcessId();
            if (pid == null) {
                LOGGER.error("Current process pid not found.");
                return null;
            }
            
            // 获取进程可执行文件
            File file = new File("/proc/" + pid + "/exe");
            if (!file.exists()) {
                LOGGER.error("File not found: {}", file.getAbsolutePath());
                return null;
            }
            
            // 获取实际路径
            if (!Files.isSymbolicLink(file.toPath())) {
                LOGGER.error("File is not symbol link: {}", file.getAbsolutePath());
                return null;
            }
            
            // 获取实际路径
            Path realPath = file.toPath().toRealPath();
            String execPath = realPath.toFile().getAbsolutePath();
            LOGGER.info("Java executable path found from process info: {}", execPath);
            return execPath;
            
        } catch (IOException e) {
            LOGGER.error("Failed to get executable path", e);
            return null;
        }
    }
    
    /**
     * 获取当前进程的PID，如果失败，返回null.
     * @return 当前进程PID
     */
    private static Integer findCurrentProcessId() {
        try {
            String runtimeName = ManagementFactory.getRuntimeMXBean().getName();
            if (runtimeName.indexOf('@') < 0) {
                LOGGER.error("Invalid runtime name: {}", runtimeName);
                return null;
            }
            
            String processIdStr = runtimeName.substring(0, runtimeName.indexOf('@'));
            int processId = Integer.parseInt(processIdStr);
            LOGGER.info("Current process id found: {}", processId);
            
            return processId;
            
        } catch (NumberFormatException e) {
            LOGGER.error("Failed to find current process id", e);
            return null;
        }
    }
}
