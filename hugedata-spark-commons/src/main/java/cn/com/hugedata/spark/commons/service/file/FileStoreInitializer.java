package cn.com.hugedata.spark.commons.service.file;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件存储初始化.
 */
public final class FileStoreInitializer {
	/**
	 * logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FileStoreInitializer.class);

	/**
	 * 不允许创建实例.
	 */
	private FileStoreInitializer() {
	}

	/**
	 * 文件存储初始化.
	 * @param storePath 存储路径
	 */
	public static void initialize(String storePath) {
		LOGGER.info("Initialize file store.");

		// 获取文件存储目录
		if (storePath.endsWith("/") || storePath.endsWith("\\")) {
			storePath = storePath.substring(0, storePath.length() - 1);
		}
		LOGGER.info("File store base path: {}", storePath);

		// 创建目录
		File storePathFile = new File(storePath);
		if (!storePathFile.exists()) {
			LOGGER.info("Making file store directory: {}", storePath);
			storePathFile.mkdirs();
		}
	}
}
