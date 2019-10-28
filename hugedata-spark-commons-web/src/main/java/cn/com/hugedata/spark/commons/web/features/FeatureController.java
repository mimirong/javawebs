package cn.com.hugedata.spark.commons.web.features;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.storage.base.BaseEntity;
import cn.com.hugedata.spark.commons.web.AjaxResponse;

/**
 * 一个管理功能默认的Controller.
 * @author 高鹏
 *
 * @param <ENTITY> 实体类类型
 * @param <ID>     实体类ID类型
 * @param <MODEL>  MVC Model类型
 */
public abstract class FeatureController<
        ENTITY extends BaseEntity<ID>, 
        ID     extends Serializable,
        MODEL  extends FeatureModel> {

    /**
     * 进入查询页面.
     * @param request  HttpRequest
     * @param response HttpResponse
     * @return         ModelAndView
     */
    public abstract ModelAndView list(
            HttpServletRequest request, 
            HttpServletResponse response,
            Locale locale);
    
    /**
     * AJAX查询数据.
     * @param request      HttpRequest
     * @param response     HttpResponse
     * @param start        分页查询开始记录
     * @param length       分页查询记录数
     * @param searchParams 查询参数
     * @return             分页查询结果
     */
    public abstract PageEntity<ENTITY> listData(
            HttpServletRequest request, 
            HttpServletResponse response,
            @RequestParam("start") int start,
            @RequestParam("length") int length,
            @RequestParam("searchParams") String searchParams,
            Locale locale);
    
    /**
     * 进入新增页面.
     * @param request  HttpRequest
     * @param response HttpResponse
     * @return         ModelAndView
     */
    public abstract ModelAndView toAdd(
            HttpServletRequest request, 
            HttpServletResponse response,
            Locale locale);

    /**
     * 新增处理.
     * @param request  HttpRequest
     * @param response HttpResponse
     * @param model    MVC Model
     * @return         ModelAndView
     */
    public abstract ModelAndView doAdd(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @ModelAttribute MODEL model,
            BindingResult bindingResult,
            Locale locale);
    
    /**
     * 进入修改页面.
     * @param request  HttpRequest
     * @param response HttpResponse
     * @param id       需要修改的项ID
     * @return         ModelAndView
     */
    public abstract ModelAndView toModify(
            HttpServletRequest request,
            HttpServletResponse response,
            ID id,
            Locale locale);

    /**
     * 修改处理.
     * @param request  HttpRequest
     * @param response HttpResponse
     * @param model    MVC Model
     * @return         ModelAndView
     */
    public abstract ModelAndView doModify(
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @ModelAttribute MODEL model,
            BindingResult bindingResult,
            Locale locale);

    /**
     * AJAX删除.
     * @param request  HttpRequest
     * @param response HttpResponse
     * @param idList   需要删除的ID列表
     * @return         AJAX处理结果
     */
    public abstract AjaxResponse delete(
            HttpServletRequest request, 
            HttpServletResponse response,
            @RequestParam("idlist") List<ID> idList,
            Locale locale);
}
