package cn.com.hugedata.spark.commons.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.hugedata.spark.commons.exception.HttpNotFoundException;
import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;

/**
 * 用于在开发过程中下载文件.
 */
@Controller(value = "commonsWebFileDownloadController")
@RequestMapping("/commons-web/file-download")
public class FileDownloadController {

    @Autowired
    private FileStoreService fileStoreService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @RequestMapping("/{folderName}/{fileId}")
    public ResponseEntity<byte[]> download(
            @PathVariable("folderName") String folderName,
            @PathVariable("fileId") String fileId,
            @RequestParam(value = "attname", required = false) String attachmentName) {
        try {
            if (!(folderName + "/").equals(fileUrlHelperService.findFolderName(fileId))) {
                throw new HttpNotFoundException("File not exist.");
            }
            byte[] data = fileStoreService.load(fileId);
            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/octet-stream");
            if (StringUtils.isNotEmpty(attachmentName)) {
                headers.add("Content-Disposition", "attachment; "
                        + "filename=" + URLEncoder.encode(attachmentName, "UTF-8") + ";"
                        + "filename*=UTF-8''" + URLEncoder.encode(attachmentName, "UTF-8"));
            }
            return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
            
        } catch (ServiceException e) {
            throw new RuntimeException(e);
            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
