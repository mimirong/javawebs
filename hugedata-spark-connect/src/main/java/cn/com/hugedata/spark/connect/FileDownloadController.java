package cn.com.hugedata.spark.connect;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.hugedata.spark.commons.exception.HttpNotFoundException;
import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.storage.entity.SysFileInfo;

/**
 * 用于下载的Controller.
 */
@Controller
public class FileDownloadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadController.class);
    
    @Autowired
    private FileStoreService fileStoreService;

    @RequestMapping(value = "/files/{fileId}", method = { RequestMethod.HEAD, RequestMethod.GET })
    public void files(
            @PathVariable("fileId") String fileId,
            @RequestHeader(value = "Accept-Encoding", required = false) String acceptEncoding,
            @RequestHeader(value = "If-None-Match", required = false) String ifNonMatch,
            @RequestHeader(value = "If-Modified-Since", required = false) String ifModifiedSince,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
     
        // 查询文件
        SysFileInfo fi = fileStoreService.loadFileInfo(fileId);
        if (fi == null) {
            LOGGER.error("File id not found: {}", fileId);
            throw new HttpNotFoundException();
        }

        SimpleDateFormat fmt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        
        // 获取请求信息
        if (StringUtils.isNotEmpty(ifNonMatch) && ifNonMatch.equals(fi.getFileId())) {
            response.setStatus(304);
            return;
        }
        
        Date ifModifiedSinceDate = parseDate(ifModifiedSince, fmt);
        if (ifModifiedSinceDate != null && ifModifiedSinceDate.getTime() > fi.getCreateTime().getTime()) {
            response.setStatus(304);
            return;
        }
        
        // 返回头像信息
        response.setContentType(findContentType(fi.getFileName()));
        response.setHeader("Cache-Control", "max-age=31536000");
        response.setHeader("Cache-Control", "must-revalidate");
        response.setHeader("Last-Modified", fmt.format(fi.getCreateTime()));
        response.setHeader("ETag", fi.getFileId());
        
        if (checkAcceptGzip(acceptEncoding)) {
            response.setHeader("Content-Encoding", "gzip");
            try (OutputStream out = response.getOutputStream();
                    GZIPOutputStream gout = new GZIPOutputStream(out)) {
                fileStoreService.loadToStream(fi.getFileId(), gout);
            } catch (ServiceException e) {
                throw new IOException(e);
            }
            
        } else {
            try (OutputStream out = response.getOutputStream()) {
                fileStoreService.loadToStream(fi.getFileId(), out);
            } catch (ServiceException e) {
                throw new IOException(e);
            }
        }
    }
    
    private boolean checkAcceptGzip(String acceptEncoding) {
        if (StringUtils.isEmpty(acceptEncoding)) {
            return false;
        }
        
        String[] strs = acceptEncoding.split(",");
        for (String str : strs) {
            if (str.trim().equals("gzip")) {
                return true;
            }
        }
        
        return false;
    }
    
    private Date parseDate(String str, SimpleDateFormat fmt) {
        try {
            if (StringUtils.isEmpty(str)) {
                return null;
            }
            return fmt.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }
    
    private String findContentType(String filename) {
        String t = URLConnection.guessContentTypeFromName(filename);
        if (StringUtils.isEmpty(t)) {
            return "application/octet-stream";
        }
        return t;
    }
    
}
