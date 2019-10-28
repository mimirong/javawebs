package cn.com.hugedata.spark.commons.web.tags.management;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivilegeTag extends BodyTagSupport {
    
    private static final long serialVersionUID = -2994809503647087921L;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeTag.class);

    /**
     * (JSP) 权限URI.
     */
    private String uri;
    
    /**
     * (JSP) 权限ID.
     */
    private String rightId;
    
    /**
     * 检查权限的Handler.
     */
    private static PrivilegeCheckHandler privilegeCheckHandler = null;
    
    /**
     * 初始化PrivilegeTag.
     * @param handler 用于检查权限是否有效的Handler
     */
    public static void init(PrivilegeCheckHandler handler) {
        LOGGER.info("Privilege tag initialized with handler: {}", handler.getClass().getSimpleName());
        privilegeCheckHandler = handler;
    }

    @Override
    public int doStartTag() throws JspException {
        if (StringUtils.isEmpty(uri) && StringUtils.isEmpty(rightId)) {
            throw new JspException("Parameter [rightId] and [uri] must have at least one value.");
        }
        
        if (privilegeCheckHandler == null) {
            LOGGER.error("PrivilegeTag not initialized. Call PrivilegeTag.init() to initialize tag.");
            throw new JspException("PrivilegeTag not initialized.");
        }
        
        boolean havePrivilege = false;
        if (StringUtils.isEmpty(uri)) {
            havePrivilege = privilegeCheckHandler.checkRight((HttpServletRequest) pageContext.getRequest(), rightId);
        } else {
            havePrivilege = privilegeCheckHandler.checkUri((HttpServletRequest) pageContext.getRequest(), uri);
        }
        
        if (havePrivilege) {
            reset();
            return EVAL_BODY_INCLUDE;
        } else {
            reset();
            return SKIP_BODY;
        }
    }

    private void reset() {
        this.uri = null;
        this.rightId = null;
    }
    
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    //------------------------------------------------------------------------------------------------
    
    /**
     * 实现检查权限的功能.
     */
    public interface PrivilegeCheckHandler {

        /**
         * 检查当前用户是否有权限使用一个功能.
         * @param request Http请求
         * @param rightId 权限ID
         * @return        是否有权限
         */
        boolean checkRight(HttpServletRequest request, String rightId);
        
        /**
         * 检查当前用户是否有权限访问一个URI.
         * @param request Http请求
         * @param uri     需要检查的URI
         * @return        是否有权限
         */
        boolean checkUri(HttpServletRequest request, String uri);
    }
}
