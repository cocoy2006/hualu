package com.hualu.main.java.web;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.hualu.main.java.entity.User;
import com.hualu.main.java.service.UserService;
import com.hualu.main.java.util.Hualu;

@Controller
public class IndexWeb implements ServletContextAware {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/")
	public String _() {
		return "index";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/{index}/")
	public String index(@PathVariable String index) {
		return index;
	}
	
	@RequestMapping(value = "/userinfo/{id}/")
	public ModelAndView userinfo(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("common/user_info");
		User user = userService.findById(id);
		mav.addObject("u", user);
		return mav;
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}