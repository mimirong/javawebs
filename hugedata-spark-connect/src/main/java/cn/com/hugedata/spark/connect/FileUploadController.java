package cn.com.hugedata.spark.connect;

import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.file.FileStoreService;
import cn.com.hugedata.spark.commons.service.file.FileUrlHelperService;
import cn.com.hugedata.spark.commons.service.session.LoginSessionInfo;
import cn.com.hugedata.spark.commons.service.session.LoginSessionService;
import cn.com.hugedata.spark.commons.storage.entity.LogInterfaceCall;
import cn.com.hugedata.spark.commons.storage.mapper.LogInterfaceCallMapper;

/**
 * 用于上传文件的Controller.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    
    /** 上传类型: 文件. */
    private static final String TYPE_FILE = "file";

    /** 上传类型: 图片. */
    private static final String TYPE_IMAGE = "image";
    
    @Autowired
    private FileStoreService fileStoreService;
    
    @Autowired
    private FileUrlHelperService fileUrlHelperService;
    
    @Autowired
    private LoginSessionService loginSessionService;
    
    @Autowired
    private LogInterfaceCallMapper logInterfaceCallMapper;
    
    /**
     * 上传文件.
     */
    @RequestMapping(value = { "", "/", "execute" }, produces = { "application/json; charset=utf-8" })
    @ResponseBody
    public String upload(
            @RequestParam(value = "session", required = false) String sessionToken,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "type", defaultValue = "file") String type,
            @RequestParam(value = "client", required = false) String client,
            @RequestParam(value = "version", required = false) String version,
            @RequestParam(value = "versionCode", required = false) Integer versionCode) {
        Date start = new Date();

        LOGGER.info("Start upload: {}", file.getOriginalFilename());
        InterfaceResponse respObj = uploadInner(sessionToken, file, type);
        String resp = respObj.toJSONString();
        LOGGER.info("Upload response: {}", resp);
        
        // 记录接口日志
        LogInterfaceCall log = new LogInterfaceCall();
        log.setUserId(respObj.getExtraUserId());
        log.setMethod("UPLOAD");
        log.setClientType(client);
        log.setClientVersion(version);
        log.setClientVersionCode(versionCode);
        log.setRequestTime(start);
        log.setProcessDuration((int) (System.currentTimeMillis() - start.getTime()));
        log.setIsSuccess(0 == respObj.getResult());
        log.setErrorMessage(respObj.getMessage());
        log.setRequestLength((int) file.getSize());
        log.setResponseLength(resp.length());
        logInterfaceCallMapper.insertSelective(log);
        
        return resp;
    }

    /**
     * 上传文件.
     */
    public InterfaceResponse uploadInner(String sessionToken, MultipartFile file, String type) {
        try {
            // 检查参数
            if (StringUtils.isEmpty(sessionToken)) {
                LOGGER.error("Parameter [session] can't be empty.");
                return InterfaceResponse.createFailResponse(41, "参数session不能为空");
            }
            if (file == null) {
                LOGGER.error("Parameter [file] can't be empty.");
                return InterfaceResponse.createFailResponse("参数file不能为空");
            }

            // 检查sessionToken
            LoginSessionInfo session = loginSessionService.findLoginInfo(sessionToken);
            if (session == null) {
                LOGGER.error("Invalid login session token.");
                return InterfaceResponse.createFailResponse(
                        InterfaceResponse.RESPONSE_REQUIRE_LOGIN, "登录Session无效");
            }
            
            // 根据上传类型进行检查
            if (type.equals(TYPE_FILE)) {
                LOGGER.info("Uploading generic file.");
            } else if (type.equals(TYPE_IMAGE)) {
                checkImageFileExtension(file.getOriginalFilename());
                LOGGER.info("Uploading image file.");
            } else {
                LOGGER.error("Invalid file type: {}", type);
                return InterfaceResponse.createFailResponse("上传类型无效");
            }
            
            // 保存
            String fileId = fileStoreService.save(file);
            
            // 返回
            JSONObject resp = new JSONObject();
            resp.put("fileId", fileId);
            resp.put("fileName", file.getOriginalFilename());
            resp.put("url", fileUrlHelperService.fixDownloadUrl(fileId, file.getOriginalFilename()));
            return InterfaceResponse.createSuccessResponse(resp);
            
        } catch (ServiceException e) {
            LOGGER.error("Failed to upload file.", e);
            return InterfaceResponse.createFailResponse(e.getMessage());
        }
    }
    
    /**
     * 根据文件后缀检查文件是否为图片文件.
     * @param fileName          文件名
     * @return                  是否为图片文件
     * @throws ServiceException 检查失败，文件不是图片文件
     */
    private void checkImageFileExtension(String fileName) throws ServiceException {
        String ext = FilenameUtils.getExtension(fileName).toLowerCase();
        if (StringUtils.isEmpty(ext)) {
            LOGGER.error("File is not image file: {}", fileName);
            throw new ServiceException("NotImageFile", "不支持的文件类型，请上传图片文件");
        }
        boolean success = ext.equals("png") || ext.equals("jpg") || ext.equals("jpeg") || ext.equals("bmp");
        if (!success) {
            LOGGER.error("File is not image file: {}", fileName);
            throw new ServiceException("NotImageFile", "不支持的文件类型，请上传图片文件");
        }
    }
}
