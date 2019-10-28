package cn.com.hugedata.spark.commons.service.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;
import cn.com.hugedata.spark.commons.storage.mapper.SysFileInfoMapper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 文件服务.
 */
@Service
public class FileStoreService {
	/**
	 * logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileStoreService.class);
	
	/**
	 * 文件读写缓存大小.
	 */
	private static final int BUFFER_SIZE = 4096;

	/**
	 * 用于保存文件信息.
	 */
	@Autowired
	private SysFileInfoMapper sysFileInfoMapper;

	/**
	 * 缓存有效的目录，避免频繁检查.
	 */
	private HashSet<String> validPathCache = new HashSet<String>();
	
	/**
	 * 文件保存路径.
	 */
	@Value("${file.storePath}")
	private String storePath;

	/**
	 * 初始化模块. (Spring调用)
	 */
	@PostConstruct
	public void initialize() {
		// 初始化Filestore
		FileStoreInitializer.initialize(storePath);
	}

	/**
	 * 清理.
	 */
	@PreDestroy
	public void destroy() {
	}

	/**
	 * 生成文件ID(32位). "F" + Timestamp(17) + UUID(14)
	 * @return 文件ID
	 */
	public String makeId() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return "F" + fmt.format(new Date()) + RandomStringUtils.randomAlphanumeric(14).toUpperCase();
	}

    /**
     * 保存文件. data大小不应当超过1M，超过1M应当使用startSave方法.
     * @param filename  文件名
     * @param data      文件内容
     * @return          文件ID
     * @throws ServiceException
     */
    public String save(String filename, byte[] data) throws ServiceException {
        String fileId = makeId();
        save(fileId, filename, data);
        return fileId;
    }

	/**
	 * 保存文件. data大小不应当超过1M，超过1M应当使用startSave方法.
	 * @param fileId    文件ID
	 * @param filename  文件名
	 * @param data      文件内容
	 * @throws ServiceException
	 */
	public void save(String fileId, String filename, byte[] data) throws ServiceException {
		FileOutputStream out = null;
		try {
			// 检查文件大小
			if (data.length > 1 * 1024 * 1024) {
				LOGGER.error("File too large: " + data.length);
			}

			// 获取保存路径
			String path = getFilePath(fileId);
			ensureFilePath(path);
			LOGGER.info("Saving file " + fileId + " to " + path);

			// 保存文件
			out = new FileOutputStream(path + File.separatorChar + fileId);
			out.write(data);
			out.flush();
			
			// 保存文件信息
			SysFileInfo fi = createFileInfo(fileId, filename, data.length);
            sysFileInfoMapper.insertSelective(fi);
            
		} catch (IOException e) {
		    LOGGER.error("Failed to save file.", e);
		    throw new ServiceException("FileStoreService-FailedSaveFile", "保存文件失败");
            
        } finally {
			IOUtils.closeQuietly(out);
		}
	}

    /**
     * 保存上传的文件. 
     * @param file    文件
     * @return        文件ID
     * @throws ServiceException
     */
    public String save(MultipartFile file) throws ServiceException {
        String fileId = makeId();
        save(fileId, file);
        return fileId;
    }

	/**
	 * 保存上传的文件. 
     * @param fileId  文件ID
	 * @param file    文件
	 * @throws ServiceException
	 */
	public long save(String fileId, MultipartFile file) throws ServiceException {
	    return save(fileId, file, file.getOriginalFilename());
	}

    /**
     * 保存上传的文件. 
     * @param fileId  文件ID
     * @param file    文件
     * @throws ServiceException
     */
    public long save(String fileId, MultipartFile file, String filename) throws ServiceException {
        OutputStream out = null;
        InputStream in = null;
        try {
            // 获取保存路径
            String path = getFilePath(fileId);
            ensureFilePath(path);
            LOGGER.info("Saving multipart file " + fileId + " to " + path);

            // 保存文件
            out = new FileOutputStream(path + File.separatorChar + fileId);
            in = file.getInputStream();
            long ret = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            while (true) {
                int read = in.read(buffer);
                if (read < 0) {
                    break;
                }
                ret += read;
                out.write(buffer, 0, read);
            }
            out.flush();
            
            // 保存文件信息
            SysFileInfo fi = createFileInfo(fileId, filename, file.getContentType(), (int) file.getSize());
            sysFileInfoMapper.insertSelective(fi);
            
            return ret;
            
        } catch (IOException e) {
            LOGGER.error("Failed to save file.", e);
            throw new ServiceException("FileStoreService-FailedSaveFile", "保存文件失败");
            
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
        }
    }
	
	/**
	 * 根据文件ID读取一个文件，如果文件不存在，返回null.
	 * @param fileId  文件ID
	 * @return        文件内容
	 * @throws ServiceException
	 */
	public byte[] load(String fileId) throws ServiceException {
		InputStream in = null;
		try {
			// 获取保存路径
			String path = getFilePath(fileId);
			LOGGER.info("Reading file " + fileId + " from " + path);

			// 检查文件是否存在
			File file = new File(path + File.separatorChar + fileId);
			if (!file.exists()) {
				LOGGER.warn("File does not exist: " + fileId);
				return null;
			}

			// 读取文件
			in = new FileInputStream(file);
			ByteArrayOutputStream memory = new ByteArrayOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			while (true) {
				int read = in.read(buffer);
				if (read < 0) {
					break;
				}
				memory.write(buffer, 0, read);
			}
			return memory.toByteArray();
            
        } catch (IOException e) {
            LOGGER.error("Failed to save file.", e);
            throw new ServiceException("FileStoreService-FailedSaveFile", "读取文件失败");
            
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * 保存文件.
	 * @param fileId 文件ID
	 * @return       用于保存文件的输出流，保存完成后应当调用close()方法.
	 * @throws ServiceException
	 */
	public OutputStream openStreamForSave(final String fileId, final String filename) throws ServiceException {
		try {
            // 获取保存路径
            String path = getFilePath(fileId);
            ensureFilePath(path);
            LOGGER.info("Saving file " + fileId + " to " + path);
            
            // 打开输出流
            final String filepath = path + File.separatorChar + fileId;
            final OutputStream out = new FileOutputStream(filepath);

            // 输出流Interceptor
            MethodInterceptor mi = new MethodInterceptor() {
            	boolean isClosed = false;
            	
            	@Override
            	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    Object ret = method.invoke(out, args);
                    if (method.getName().equals("close") && !isClosed)
                    {
                        LOGGER.info("Closing output stream and saving file info: {}", fileId);
                        SysFileInfo fi = createFileInfo(fileId, filename, (int) new File(filepath).length());
                        sysFileInfoMapper.insertSelective(fi);
                        isClosed = true;
                    }
                    return ret;
            	}
            };
            
            // Enhance
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OutputStream.class);
            enhancer.setCallback(mi);
            return (OutputStream) enhancer.create();
            
        } catch (IOException e) {
            LOGGER.error("Failed to save file.", e);
            throw new ServiceException("FileStoreService-FailedSaveFile", "保存文件失败");
        }
	}

	/**
	 * 读取文件.
	 * @param fileId 文件ID
	 * @return       用于读取文件的输入流，读取完成后应当调用close()方法.
	 * @throws ServiceException
	 */
	public InputStream openStreamForLoad(String fileId) throws ServiceException {
		try {
            // 获取保存路径
            String path = getFilePath(fileId);
            LOGGER.info("Reading file " + fileId + " from " + path);

            // 检查文件是否存在
            File file = new File(path + File.separatorChar + fileId);
            if (!file.exists()) {
            	LOGGER.warn("File does not exist: " + fileId);
            	return null;
            }

            // 读取文件
            return new FileInputStream(file);

        } catch (IOException e) {
            LOGGER.error("Failed to save file.", e);
            throw new ServiceException("FileStoreService-FailedSaveFile", "读取文件失败");
        }
	}

	/**
	 * 从指定的输入流读取数据，并保存到指定的文件
	 * @param fileId  文件ID
	 * @param in      用于读取文件内容的输入流
	 * @return        读取并保存的字节数
	 * @throws ServiceException
	 */
	public long saveFromStream(String fileId, String filename, InputStream in) throws ServiceException {
		OutputStream out = null;
		try {
			// 获取保存路径
			String path = getFilePath(fileId);
			ensureFilePath(path);
			LOGGER.info("Saving file " + fileId + " to " + path);

			// 保存文件
			out = new FileOutputStream(path + File.separatorChar + fileId);
			long ret = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while (true) {
				int read = in.read(buffer);
				if (read < 0) {
					break;
				}
				ret += read;
				out.write(buffer, 0, read);
			}
			out.flush();
			
			// 保存文件信息
			SysFileInfo fi = createFileInfo(fileId, filename, (int) ret);
            sysFileInfoMapper.insertSelective(fi);
			
			return ret;
            
        } catch (IOException e) {
            LOGGER.error("Failed to save file.", e);
            throw new ServiceException("FileStoreService-FailedSaveFile", "保存文件失败");
            
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * 读取文件，并直接写入到指定的输出流
	 * @param fileId  文件ID
	 * @param out     用于输出文件内容的输出流
	 * @return        实际写入输出流的大小
	 * @throws ServiceException
	 */
	public long loadToStream(String fileId, OutputStream out) throws ServiceException {
		InputStream in = null;
		long ret = 0;
		try {
			in = openStreamForLoad(fileId);
			if (in == null) {
				return -1;
			}

			byte[] buffer = new byte[BUFFER_SIZE];
			while (true) {
				int read = in.read(buffer);
				if (read < 0) {
					break;
				}
				ret += read;
				out.write(buffer, 0, read);
			}
			out.flush();

			return ret;
            
        } catch (IOException e) {
            LOGGER.error("Failed to save file.", e);
            throw new ServiceException("FileStoreService-FailedSaveFile", "读取文件失败");
            
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * 从文件库中删除文件.
	 * @param fileId  文件ID
	 * @return        是否成功
	 */
	public boolean deleteFile(String fileId) {
		try {
			// 获取保存路径
			String path = getFilePath(fileId);
			LOGGER.info("Deleting file " + fileId + " from " + path);

			// 检查文件是否存在
			File file = new File(path + File.separatorChar + fileId);
			if (!file.exists()) {
				LOGGER.warn("File does not exist: " + fileId);
				return true;
			}

			return file.delete();
		} catch (Exception e) {
			LOGGER.error("Failed to delete file.", e);
			return false;
		}
	}
	
	/**
	 * 根据文件ID获取文件保存的完整路径.
	 * @param fileId 文件ID
	 * @return       文件保存的完整路径
	 */
	public String getFileSavePath(String fileId) {
	    return getFilePath(fileId) + File.separatorChar + fileId;
	}

	/**
	 * 根据文件ID获得文件保存实际路径. "FyyyyMM_0" to "FyyyyMM_9"
	 * @param fileId  文件ID
	 * @return        文件实际路径
	 */
	public String getFilePath(String fileId) {
		String path = storePath;
		if (path.endsWith("/") || path.endsWith("\\")) {
			path = path.substring(0, path.length() - 1);
		}
		path += File.separator;
		path += fileId.substring(0, 7);
		path += "_";
		path += fileId.substring(fileId.length() - 1);
		return path;
	}

	/**
	 * 创建文件保存目录.
	 * @param path 保存目录
	 */
	private void ensureFilePath(String path) {
		if (validPathCache.contains(path)) {
			return;
		}
		File file = new File(path);
		if (!file.exists()) {
			LOGGER.debug("Creating file store path: {}", path);
			file.mkdir();
		}
		validPathCache.add(path);
	}
	
	/**
	 * 根据文件ID获取文件信息.
	 * @param fileId  文件ID
	 * @return        文件信息
	 */
	public SysFileInfo loadFileInfo(String fileId) {
	    return sysFileInfoMapper.selectById(fileId);
	}
	
	/**
	 * 根据文件名创建用于保存到数据库的文件信息实例.
	 * @param fileId    文件ID
	 * @param filename  文件名
	 * @param size      文件大小
	 * @return          文件信息实例
	 */
	private SysFileInfo createFileInfo(String fileId, String filename, long size) {
		SysFileInfo fi = new SysFileInfo();
		fi.setFileId(fileId);
		fi.setFileName(filename);
		fi.setFileSize(size);
		fi.setMimeType(null);
		fi.setCreateTime(new Date());
		fi.setExtension(FilenameUtils.getExtension(filename));
		return fi;
	}
	
	/**
	 * 根据文件名创建用于保存到数据库的文件信息实例.
	 * @param fileId    文件ID
	 * @param filename  文件名
	 * @param mimeType  文件Mime-Type
	 * @param size      文件大小
	 * @return          文件信息实例
	 */
	private SysFileInfo createFileInfo(String fileId, String filename, String mimeType, long size) {
	    SysFileInfo fi = new SysFileInfo();
		fi.setFileId(fileId);
		fi.setFileName(filename);
		fi.setFileSize(size);
		fi.setMimeType(null);
		fi.setCreateTime(new Date());
		fi.setExtension(FilenameUtils.getExtension(filename));
		return fi;
	}
    
    /**
     * 创建用于Http下载的ResponseEntity
     * @param fileId
     * @return
     * @throws ServiceException
     */
    public ResponseEntity<FileSystemResource> createResponseEntityForDownload(String fileId) throws ServiceException {
        try {
            // Get file real path
            String path = this.getFileSavePath(fileId);
            
            // Get file info
            SysFileInfo fi = this.loadFileInfo(fileId);
            if (fi == null) {
                LOGGER.error("File info not found: {}", fileId);
                throw new ServiceException("FileStoreService-FileNotFound", "文件不存在");
            }
            
            // Returning
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", fi.getMimeType());
            headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fi.getFileName(), "utf-8"));
            return new ResponseEntity<FileSystemResource>(new FileSystemResource(path), headers, HttpStatus.OK);
            
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Failed to create response entity.", e);
            throw new ServiceException("FileStoreService-FailedCreateEntity", "读取文件失败");
        }
    }

	// ----------------------------------------------------------------------------------------------------

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }
}
