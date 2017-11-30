package com.hualu.main.java.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.hualu.main.java.service.DeviceService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Controller
@RequestMapping(value = "/device")
public class DeviceWeb implements ServletContextAware {
	
	@Autowired
	private DeviceService service;
	
	@ResponseBody
	@RequestMapping(value = "/save/")
	public String save(HttpServletRequest request) {
		String packageIdString = request.getParameter("packageId");
		int packageId = 0;
		if(packageIdString != null) {
			packageId = Integer.parseInt(packageIdString);
		}
		String dsn = request.getParameter("dsn");
		int id = service.save(packageId, dsn);
		// operation log
		request.getSession().setAttribute("targetid", id);
		return String.valueOf(Status.SUCCESS);
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}