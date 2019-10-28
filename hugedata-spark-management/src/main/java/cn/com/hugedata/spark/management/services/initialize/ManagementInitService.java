package cn.com.hugedata.spark.management.services.initialize;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.web.tags.management.PrivilegeTag;
import cn.com.hugedata.spark.commons.web.tags.management.PrivilegeTag.PrivilegeCheckHandler;
import cn.com.hugedata.spark.management.commons.ServerConstants;
import cn.com.hugedata.spark.management.services.login.PrivilegeInfo;

/**
 * 后台管理初始化. 每次启动时执行.
 */
@Service
public class ManagementInitService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementInitService.class);

    /**
     * 初始化系统管理员信息.
     */
    @PostConstruct
    public void init() {
        LOGGER.info("Initialize management.");
        
        // 初始化后台管理标签
        initTags();
    }
    
    /**
     * 初始化后台管理标签.
     */
    private void initTags() {
        PrivilegeTag.init(new PrivilegeCheckHandler() {
            @Override
            public boolean checkUri(HttpServletRequest request, String uri) {
                PrivilegeInfo privileges =
                        (PrivilegeInfo) request.getSession().getAttribute(ServerConstants.PRIVILEGE_SESSION_KEY);
                return privileges != null && privileges.checkPrivilege(uri);
            }
            
            @Override
            public boolean checkRight(HttpServletRequest request, String rightId) {
                PrivilegeInfo privileges =
                        (PrivilegeInfo) request.getSession().getAttribute(ServerConstants.PRIVILEGE_SESSION_KEY);
                return privileges != null && privileges.checkRight(rightId);
            }
        });
    }
}
