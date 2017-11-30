package com.hualu.main.java.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.service.SmsService;
import com.hualu.main.java.util.Hualu;

@Controller
@RequestMapping(value = "/sms")
public class SmsWeb implements ServletContextAware {
	
	@Autowired
	private SmsService smsService;
	
	@ResponseBody
	@RequestMapping(value = "/recordReceived/{userid}/{rtId}/")
	@Deprecated
	public String recordReceived(HttpServletRequest request, @PathVariable int userid, @PathVariable int rtId) {
		return smsService.recordReceived(userid, rtId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordProblem/{rtId}/")
	public String recordProblem(HttpServletRequest request, @PathVariable int rtId) {
		return smsService.recordProblem(rtId);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordNormal/{rtId}/")
	public String recordNormal(HttpServletRequest request, @PathVariable int rtId) {
		return smsService.recordNormal(rtId);
	}
	
	@RequestMapping(value = "/dataReceived/open/{rtId}/")
	public ModelAndView openDataReceived(HttpServletRequest request, @PathVariable int rtId) {
		ModelAndView mav = new ModelAndView("common/sms/data_received");
		RecordTaskComponent rtc = smsService.openDataReceived(rtId);
		if(rtc != null) {
			mav.addObject("rtc", rtc);
		}
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/dataReceived/{rtId}/")
	public String dataReceived(HttpServletRequest request, @PathVariable int rtId) {
		String[] indexs = request.getParameterValues("index");
		return new Gson().toJson(smsService.dataReceived(rtId, indexs));
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}