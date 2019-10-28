package cn.com.hugedata.spark.govaffairs.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.hugedata.spark.commons.service.PageEntity;
import cn.com.hugedata.spark.commons.service.approval.ContentUtil;
import cn.com.hugedata.spark.commons.storage.entity.ItMessage;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.web.AjaxResponse;
import cn.com.hugedata.spark.commons.web.login.Login;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;
import cn.com.hugedata.spark.govaffairs.services.InteractiveService;

/**
 * 办事指南.
 */
@Controller
@RequestMapping("/interactive")
public class InteractiveController {

	@Autowired
	private InteractiveService interactiveService;

	@RequestMapping("/list")
	@Login(required = false)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/spark/interactive/list");
		mv.addObject("menuId", "701");
		return mv;
	}

	@RequestMapping("/listData")
	@ResponseBody
	@Login(required = false)
	public PageEntity<ItMessage> listData(
			@RequestParam(value = "password", defaultValue = "") String password, @RequestParam("start") Integer start,
			@RequestParam("length") Integer length) {
		return interactiveService.list(password, start, length);
	}

	@RequestMapping("/writeLetter")
	@Login(required = true)
	public ModelAndView writeLetter() {
		ModelAndView mv = new ModelAndView("/spark/interactive/writeLetter");
		mv.addObject("messageCode", "XJ" + ContentUtil.produceRandom());
		return mv;
	}

	@RequestMapping("/writeLetterData")
	@ResponseBody
	@Login(required = true)
	public AjaxResponse writeLetterData(@ModelAttribute ItMessage itMessage) {
		UcUserInfo user = LoginUtils.getCurrentLogin();
		itMessage.setUserId(user.getUserId());
		itMessage.setName(user.getName());
		itMessage.setUserType(user.getUserType());
		itMessage.setIdCard(user.getIdCard());
		itMessage.setMobile(user.getMobile());
		itMessage.setEmail(user.getEmail());
		itMessage.setAddress(user.getFullAddress());
		itMessage.setStatus("0");
		itMessage.setIsDeleted(false);
		itMessage.setSubmitTime(new Date());
		itMessage.setPassword(ContentUtil.produceRandom());
		interactiveService.writeLetterData(itMessage);
		return AjaxResponse.createSuccessResponse(itMessage.getPassword());
	}

	@RequestMapping("/letterDetail")
	@ResponseBody
	@Login(required = false)
	public ModelAndView letterDetail(@RequestParam("messageId") Integer messageId) {
		ModelAndView mv = new ModelAndView("/spark/interactive/letterDetail");
		mv.addObject("messageId", messageId);
		mv.addObject("menuId", 701);
		return mv;
	}

	@RequestMapping("/letterDetailData")
	@ResponseBody
	@Login(required = false)
	public AjaxResponse letterDetailData(@RequestParam("messageId") Integer messageId) {
		ItMessage itMessage = interactiveService.letterDetailData(messageId);
		return AjaxResponse.createSuccessResponse(itMessage);
	}

}
