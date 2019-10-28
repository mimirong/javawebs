package cn.com.hugedata.spark.commons.web.features;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.ErrorPageBuilder;
import cn.com.hugedata.spark.commons.web.datatables.DatatableColumnInfo;
import cn.com.hugedata.spark.commons.web.datatables.DatatableOrderInfo;
import cn.com.hugedata.spark.commons.web.datatables.DatatableUtils;

/**
 * FeatureController的默认实现.
 * @author 高鹏
 *
 * @param <ENTITY>  实体类类型
 * @param <ID>      实体类ID类型
 * @param <SERVICE> 服务类类型
 * @param <MODEL>   MVC Model类型
 */
public abstract class FeatureControllerImpl<
        ENTITY  extends BaseEntity<ID>, 
        ID      extends Serializable, 
        SERVICE extends FeatureService<ENTITY, ID, MODEL>,
        MODEL   extends FeatureModel>
    
extends FeatureController<ENTITY, ID, MODEL> {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureControllerImpl.class);
    
    /**
     * 功能名称.
     */
    private String featureName = null;
    
    /**
     * 服务类.
     */
    @Autowired
    protected SERVICE service;
    
    /**
     * Spring消息源.
     */
    @Autowired
    private MessageSource messageSource;
    
    
    /**
     * 通过Controller获取Feature项目的名称.
     * @return Feature名称
     */
    protected String getFeatureName() {
        if (featureName == null) {
            String suffix = "Controller";
            String className = this.getClass().getSimpleName();
            if (!className.endsWith(suffix)) {
                throw new RuntimeException("Invalid controller name: " + className);
            }
            String name = className.substring(0, className.length() - suffix.length());
            LOGGER.info("Feature controller found: {}", name);
            featureName = name;
        }
        return featureName;
    }

    @Override
    @RequestMapping("/list")
    public ModelAndView list(
            HttpServletRequest request, 
            HttpServletResponse response,
            Locale locale) {
        ModelAndView mv = new ModelAndView("/features/" + getFeatureName() + "/" + getFeatureName() + "_List");
        return mv;
    }
    
    @RequestMapping("/detail")
    public ModelAndView detail(
            HttpServletRequest request, 
            HttpServletResponse response,
            @RequestParam("serviceId") int serviceId,
            Locale locale) {
        ModelAndView mv = new ModelAndView("/features/" + getFeatureName() + "/" + getFeatureName() + "_Detail");
        mv.addObject("serviceId",serviceId);
        return mv;
    }
    
    @Override
    @RequestMapping("/listData")
    @ResponseBody
    public PageEntity<ENTITY> listData(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("start") int start,
            @RequestParam("length") int length,
            @RequestParam(value = "searchParams", defaultValue = "{}") String searchParams,
            Locale locale) {
        // 加载列信息条件
        List<DatatableColumnInfo> columns = DatatableUtils.loadColumnInfoFromRequest(request);
        LOGGER.debug("Datatable columns loaded: {}", columns.size());
        
        // 加载排序条件
        List<DatatableOrderInfo> orders = DatatableUtils.loadOrderInfoFromRequest(request);
        LOGGER.debug("Datatable order columns loaded: {}", orders.size());
        
        // 查询
        LOGGER.debug("Search parameters: " + searchParams);
        String convertedSearchParams = searchParams;
        return service.list(parseSearchParams(convertedSearchParams), start, length, parseSearchOrders(columns, orders));
    }

    @Override
    @RequestMapping("/toAdd")
    public ModelAndView toAdd(
            HttpServletRequest request, 
            HttpServletResponse response,
            Locale locale) {
        ModelAndView mv = new ModelAndView("/features/" + getFeatureName() + "/" + getFeatureName() + "_Add");
        mv.addObject("entityJson", "{}");
        return mv;
    }

    @Override
    @RequestMapping(value = "/doAdd", produces = "text/html; charset=UTF-8")
    public ModelAndView doAdd(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @ModelAttribute MODEL model,
            BindingResult bindingResult,
            Locale locale) {
        try {
            if (bindingResult.hasErrors()) {
                LOGGER.error("Javascript Validations Are Skipped !!");
                throw new ServiceException("Javascript validations ar skipped",
                        "manager.features.javascript_validation_skipped");
            }
            service.add(model);
            
            ModelAndView mv = new ModelAndView("/features/add_success");
            mv.addObject("serviceType", getFeatureName());
            return mv;
        } catch (ServiceException e) {
            ModelAndView mv = new ModelAndView("/features/" + getFeatureName() + "/" + getFeatureName() + "_Add");
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("entityJson", JSON.toJSONString(model));
            copyPropertiesToModelAndView(mv, model);
            return mv;
        }
    }

    @RequestMapping(value = "/doAdd", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public AjaxResponse doAddAjax(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @ModelAttribute MODEL model,
            BindingResult bindingResult,
            Locale locale) {
        try {
            if (bindingResult.hasErrors()) {
                LOGGER.error("Javascript Validations Are Skipped !!");
                return AjaxResponse.createFailResponse("请求参数不正确");
            }
            service.add(model);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
    
    @Override
    @RequestMapping(value = "toModify", produces = "text/html; charset=UTF-8")
    public ModelAndView toModify(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("id") ID id,
            Locale locale) {
        try {
            MODEL model = service.queryForModify(id);
            ModelAndView mv = new ModelAndView("/features/" + getFeatureName() + "/" + getFeatureName() + "_Modify");
            copyPropertiesToModelAndView(mv, model);
            mv.addObject("entityJson", JSON.toJSONString(model));
            return mv;
            
        } catch (ServiceException e) {
            return new ErrorPageBuilder()
                .setErrorTitleText(messageSource.getMessage("manager.features.failed_modify", null, null))
                .setErrorMessageText(e.getMessage())
                .create();
        }
    }
    
    @RequestMapping(value = "toModify", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public AjaxResponse toModifyAjax(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("id") ID id,
            Locale locale) {
        try {
            MODEL model = service.queryForModify(id);
            return AjaxResponse.createSuccessResponse(model);
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "doModify", produces = "text/html; charset=UTF-8")
    public ModelAndView doModify(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @ModelAttribute MODEL model,
            BindingResult bindingResult,
            Locale locale) {
        try {
            if (bindingResult.hasErrors()) {
                LOGGER.error("Javascript Validations Are Skipped !!");
                throw new ServiceException("Javascript validations ar skipped",
                        "manager.features.javascript_validation_skipped");
            }
            service.modify(model);
            return new ModelAndView("/features/modify_success");
        } catch (ServiceException e) {
            ModelAndView mv = new ModelAndView("/features/" + getFeatureName() + "/" + getFeatureName() + "_Modify");
            mv.addObject("errorMessage", e.getMessage());
            mv.addObject("entityJson", JSON.toJSONString(model));
            copyPropertiesToModelAndView(mv, model);
            return mv;
        }
    }

    @RequestMapping(value = "doModify", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public AjaxResponse doModifyAjax(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @ModelAttribute MODEL model,
            BindingResult bindingResult,
            Locale locale) {
        try {
            if (bindingResult.hasErrors()) {
                LOGGER.error("Javascript Validations Are Skipped !!");
                return AjaxResponse.createFailResponse("请求参数不正确");
            }
            service.modify(model);
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }

    @Override
    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResponse delete(
            HttpServletRequest request, 
            HttpServletResponse response,
            @RequestParam("idlist") List<ID> idlist,
            Locale locale) {
        try {
            service.delete(idlist);
            LOGGER.debug("Delete ids: " + StringUtils.join(idlist, "#"));
            return AjaxResponse.createSuccessResponse();
        } catch (ServiceException e) {
            return AjaxResponse.createFailResponse(e.getMessage());
        }
    }
 
    /**
     * 解析DataTables请求的排序信息，并转换为Mapper所用的格式.
     * @param cols   字段信息
     * @param orders 排序信息
     * @return       Mapper所用的排序信息
     */
    protected List<OrderItem> parseSearchOrders(List<DatatableColumnInfo> cols, List<DatatableOrderInfo> orders) {
        if (orders == null || orders.isEmpty()) {
            return null;
        }
        
        List<OrderItem> items = new ArrayList<OrderItem>();
        for (DatatableOrderInfo o : orders) {
            OrderItem item = new OrderItem();
            item.setColumn(cols.get(o.getColumn()).getData());
            item.setDir(o.getDir());
            items.add(item);
        }
        return items;
    }
    
    /**
     * 将JSON格式的查询条件转换为Mybatis使用的Map格式.
     * @param expr 查询条件
     * @return Map查询条件
     */
    protected Map<String, Object> parseSearchParams(String expr) {
        if (StringUtils.isEmpty(expr)) {
            return null;
        }

        Map<String, Object> map = new TreeMap<String, Object>();
        JSONObject obj = JSON.parseObject(expr);
        for (Object key : obj.keySet()) {
            Object value = obj.get(key.toString());
            if (value instanceof String) {
                if (StringUtils.isNotEmpty((String) value)) {
                    map.put(key.toString(), value);
                }   
            } else {
                map.put(key.toString(), value);
            }
        }
        return map;
    }

    /**
     * 获取当前FeatureControllerImpl的所使用的实体类类型.
     * @return 实体类类型
     */
    @SuppressWarnings("unchecked")
    protected Class<? extends BaseEntity<ID>> getEntityType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<? extends BaseEntity<ID>>) type.getActualTypeArguments()[0];
    }

    /**
     * 获取当前FeatureControllerImpl的所使用的实体类ID类型.
     * @return 实体类ID类型
     */
    protected Class<?> getIdType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<?>) type.getActualTypeArguments()[1];
    }

    /**
     * 获取当前FeatureControllerImpl的所使用的服务类类型.
     * @return 服务类类型
     */
    @SuppressWarnings("unchecked")
    protected Class<? extends FeatureService<ENTITY, ID, MODEL>> getServiceType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<? extends FeatureService<ENTITY, ID, MODEL>>) type.getActualTypeArguments()[0];
    }
    
    /**
     * 将Object中的所有属性复制到ModelAndView.
     * @param mv  目标ModelAndVer
     * @param obj 源Object
     */
    protected void copyPropertiesToModelAndView(ModelAndView mv, Object obj) {
        try {
            PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(obj);
            for (PropertyDescriptor property : properties) {
                if (property.getName().equals("class")) {
                    continue;
                }
                
                // 获取字段信息
                String fieldName = property.getName();
                Object value = property.getReadMethod().invoke(obj);
                LOGGER.debug("Parsing model field: {} -> {}", fieldName, value);
                
                // 复制到entity
                PropertyDescriptor targetProperty = PropertyUtils.getPropertyDescriptor(obj, fieldName);
                if (targetProperty != null && value != null) {
                    if (value instanceof CommonsMultipartFile) {
                        LOGGER.warn("Commons multipart file is not supported.");
                    } else {
                        mv.addObject(fieldName, value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to copy properties", e);
            
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to copy properties", e);

        } catch (InvocationTargetException e) {
            LOGGER.error("Failed to copy properties", e);
            
        } catch (NoSuchMethodException e) {
            LOGGER.error("Failed to copy properties", e);
        }
    }
 
}
