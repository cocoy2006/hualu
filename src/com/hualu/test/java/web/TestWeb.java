package com.hualu.test.java.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.timer.TimerTask;

@Controller
public class TestWeb implements ServletContextAware {
	
	@Autowired
	private TimerTask timer;
	
	@RequestMapping(value = "/api/")
	public ModelAndView api(HttpSession session) {
		ModelAndView mav = new ModelAndView("test/api");
		return mav;
	}
	
	@RequestMapping(value = "/tester/")
	public ModelAndView tester(HttpSession session) {
		ModelAndView mav = new ModelAndView("test/tester");
		timer.updateRecordTask();
		return mav;
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}
