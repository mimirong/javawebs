package cn.com.hugedata.spark.management.services.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;

/**
 * 用于处理管理后台权限.
 */
public class PrivilegeInfo {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeInfo.class);
    
    /**
     * 当前权限所属的系统类型.
     */
    private String systemType;
    
    /**
     * 保存ContextPath在验证URI时使用.
     */
    private String contextPath;
    
    /**
     * 保存用户ID.
     */
    private Integer userId;
    
    /**
     * 菜单数据JSON.
     */
    private String menuJson;


    /**
     * 保存允许访问的URI.
     */
    private HashSet<String> allowedUris = new HashSet<String>();
    
    /**
     * 保存允许使用的权限.
     */
    private HashSet<String> allowedRights = new HashSet<String>();
    
    /**
     * 保存rightId到uri的对应关系.
     */
    private HashMap<String, ArrayList<String>> rightIdToUrisMap = new HashMap<>();
    
    /**
     * 创建一个权限和校验工具实例.
     * @param contextPath ContextPath
     */
    public PrivilegeInfo(String contextPath) {
        this.contextPath = contextPath;
        
        // 以下增加默认所有用户允许使用的URI
        allowedUris = new HashSet<String>();
        
        // 主界面
        allowedUris.add("/");
        allowedUris.add("/index");
        allowedUris.add("/empty");
        allowedUris.add("/switchSystem");

        // 修改密码页面
        allowedUris.add("/modifyPwd");
        allowedUris.add("/modifyPwd/index");
        allowedUris.add("/modifyPwd/viewLoginCode");
        allowedUris.add("/modifyPwd/doModify");
    }

    /**
     * 增加允许访问的URI和权限.
     * @param id  权限ID
     * @param uri 允许访问的URI
     */
    public void addPrivilege(String id, String uri) {
        LOGGER.debug("Adding privilege: {} - {}", id, uri);
        allowedUris.add(uri);
        allowedRights.add(id);
        
        ArrayList<String> list = rightIdToUrisMap.get(id);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(uri);
    }
    
    /**
     * 删除一个权限.
     * @param id 权限ID
     */
    public void removePrivilege(String id) {
        LOGGER.debug("Removing privilege: {}", id);
        allowedRights.remove(id);
        
        ArrayList<String> list = rightIdToUrisMap.get(id);
        if (list != null) {
            for (String uri : list) {
                LOGGER.debug("Removing uri: {}", uri);
                allowedUris.remove(uri);
            }
        }
    }
    
    /**
     * 检查URI是否为允许访问的URI.
     * @param uri 需要检查的URI
     * @return 是否允许访问
     */
    public boolean checkPrivilege(String uri) {
        LOGGER.debug("Checking privilege uri: {}", uri);
        
        // 去掉开头的contextPath
        if (StringUtils.isNotEmpty(contextPath) && uri.startsWith(contextPath)) {
            uri = uri.substring(contextPath.length());
        }
        
        // 去掉结尾";"以后的部分
        int pos = uri.lastIndexOf(';');
        if (pos > 0) {
            uri = uri.substring(0, pos);
        }
        
        return allowedUris.contains(uri);
    }
    
    /**
     * 检查当前用户是否可以使用某一权限.
     * @param rightId 权限ID
     * @return        是否可以使用权限
     */
    public boolean checkRight(String rightId) {
        return allowedRights.contains(rightId);
    }
    
    /**
     * 获取JSON格式的权限URI列表.
     * @return 权限URI列表(JSON Array)
     */
    public String getPrivilegeListJson() {
        JSONArray arr = new JSONArray();
        arr.addAll(allowedUris);
        return arr.toJSONString();
    }
    
    /**
     * 获取JSON格式的权限ID列表.
     * @return 权限ID列表(JSON Array)
     */
    public String getRightListJson() {
        JSONArray arr = new JSONArray();
        arr.addAll(allowedRights);
        return arr.toJSONString();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getMenuJson() {
        return menuJson;
    }

    public void setMenuJson(String menuJson) {
        this.menuJson = menuJson;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

}
