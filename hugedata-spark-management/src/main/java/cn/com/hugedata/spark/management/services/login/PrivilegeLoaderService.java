package cn.com.hugedata.spark.management.services.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.UcManageRight;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.querymapper.UcManageRightQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.management.commons.ServerConstants;

/**
 * 用于加载菜单和权限.
 */
@Service
public class PrivilegeLoaderService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivilegeLoaderService.class);

    @Autowired
    private UcManageRightQueryMapper ucManageRightQueryMapper;
    
    /**
     * 获取用户的权限信息.
     * @param user       用户信息
     * @param systemType 系统类型
     * @return           权限信息
     */
    public PrivilegeInfo loadPrivilegeInfo(UcUserInfo user) {
        HttpServletRequest request = findHttpRequest();
        HttpSession session = request.getSession();
        
        // 处理未登录的情况
        if (user == null) {
            LOGGER.error("User is null when loading privileges.");
            return null;
        }
        
        LOGGER.info("Load privilege data for user: {}", user.getUserId());

        // 从Session获取权限信息
        PrivilegeInfo privilege = (PrivilegeInfo) session.getAttribute(ServerConstants.PRIVILEGE_SESSION_KEY);
        if (privilege != null && user.getUserId().equals(privilege.getUserId())) {
            LOGGER.debug("Privilege info found in session.");
            return privilege;
        }
        
        // 重新加载权限信息
        LOGGER.debug("Reloading privilege info.");
        privilege = new PrivilegeInfo(request.getContextPath());
        privilege.setUserId(user.getUserId());
        privilege.setMenuJson("[]");
        session.setAttribute(ServerConstants.PRIVILEGE_SESSION_KEY, privilege);
        
        // 从数据库加载所有权限信息
        List<UcManageRight> rights;
        if (UcUserInfo.USER_TYPE_SYSTEM.equals(user.getUserType()) 
                || UcUserInfo.USER_TYPE_PARK.equals(user.getUserType())) {
            rights =  ucManageRightQueryMapper.queryRightsForAdmin(MyBatisUtils.buildParameterMap(
                    "userId", user.getUserId(),
                    "order", Arrays.asList(new OrderItem("sortIndex", "asc"))
            ));
        } else {
            rights =  ucManageRightQueryMapper.queryRightsForUser(MyBatisUtils.buildParameterMap(
                    "userId", user.getUserId(),
                    "order", Arrays.asList(new OrderItem("sortIndex", "asc"))
            ));    
        }
        
        // 根据用户信息去掉部分权限
        rights = filterRights(rights, user);
        
        // 判断用户是否没有配置权限
        if (rights == null || rights.isEmpty()) {
            LOGGER.error("No rights found for login user: {}", user.getUserId());
            return privilege;
        }
        
        // 获取所有第一层权限
        LinkedHashMap<String, List<UcManageRight>> map = new LinkedHashMap<String, List<UcManageRight>>();
        for (UcManageRight r : rights) {
            if (StringUtils.isEmpty(r.getParentId()) && r.getDisplay()) {
                ArrayList<UcManageRight> list = new ArrayList<UcManageRight>();
                list.add(r);
                map.put(r.getRightId(), list);
            }
        }
        
        // 第二层权限
        for (UcManageRight r : rights) {
            if (!StringUtils.isEmpty(r.getParentId()) && r.getDisplay()) {
                List<UcManageRight> list = map.get(r.getParentId());
                if (list != null) {
                    list.add(r);
                }
            }
        }

        // 保存权限到PrivilegeInfo
        for (UcManageRight r : rights) {
            if (!StringUtils.isEmpty(r.getUrl())) {
                for (String a : r.getUrl().split("\\,")) {
                    privilege.addPrivilege(r.getRightId(), a.trim());
                }
            }
        }

        // 构造菜单数据JSON
        JSONArray root = new JSONArray();
        for (Entry<String, List<UcManageRight>> e : map.entrySet()) {
            JSONArray menus = new JSONArray();
            for (int i = 1; i < e.getValue().size(); i++) {
                JSONObject menu2 = new JSONObject();
                menu2.put("id", e.getValue().get(i).getRightId());
                menu2.put("title", e.getValue().get(i).getName());
                menu2.put("iconName", e.getValue().get(i).getIconName());
                if (e.getValue().get(i).getUrl().indexOf(',') > 0) {
                    menu2.put("url", e.getValue().get(i).getUrl().split("\\,")[0].trim());
                } else {
                    menu2.put("url", e.getValue().get(i).getUrl());
                }
                menus.add(menu2);
            }

            JSONObject menu1 = new JSONObject();
            menu1.put("id", e.getKey());
            menu1.put("title", e.getValue().get(0).getName());
            menu1.put("iconName", e.getValue().get(0).getIconName());
            menu1.put("systemType", e.getValue().get(0).getSystemType());
            menu1.put("menus", menus);
            if (e.getValue().get(0).getUrl().indexOf(',') > 0) {
                menu1.put("url", e.getValue().get(0).getUrl().split("\\,")[0].trim());
            } else {
                menu1.put("url", e.getValue().get(0).getUrl());
            }
            root.add(menu1);
        }
        privilege.setMenuJson(root.toString());
        
        return privilege;
    }

    /**
     * 获得HttpServletRequest对象.
     * @return HttpServletRequest对象
     */
    private HttpServletRequest findHttpRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attrs.getRequest();
        return request;
    }
    
    /**
     * 去掉部分权限.
     * TODO: 后期改为通过角色获取权限信息.
     */
    private List<UcManageRight> filterRights(List<UcManageRight> rights, UcUserInfo user) {
        List<UcManageRight> filtered = new ArrayList<>();
        for (UcManageRight right : rights) {
            // 不是招商局用户 &&不是管理员  去掉入园退园申请功能
            if ((user.getDeptId() == null || !user.getDeptId().equals(5)) 
                    && !user.getUserType().equals(UcUserInfo.USER_TYPE_SYSTEM)) {
                if (right.getRightId().startsWith("govaffairs/joinapply")) {
                    continue;
                }
            }

            // 不是经济发展局用户 &&不是管理员  去掉入园退园申请功能
            if ((user.getDeptId() == null || !user.getDeptId().equals(1)) 
                    && !user.getUserType().equals(UcUserInfo.USER_TYPE_SYSTEM)) {
                if (right.getRightId().startsWith("govaffairs/quitapply")) {
                    continue;
                }
            }
            
            filtered.add(right);
        }
        return filtered;
    }
}
