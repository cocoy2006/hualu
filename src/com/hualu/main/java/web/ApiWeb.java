package com.hualu.main.java.web;

import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.google.gson.Gson;
import com.hualu.main.java.component.api.Result;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.service.ApiService;
import com.hualu.main.java.service.OperatorService;
import com.hualu.main.java.service.UserService;
import com.hualu.main.java.util.Hualu;

@Controller
@RequestMapping(value = "/api")
public class ApiWeb implements ServletContextAware {
	
	private static final Logger LOG = Logger.getLogger(ApiWeb.class.getName());
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/signin/operator/{role}/{loginname}/{passwd}/")
	public String signin(@PathVariable int role,
			@PathVariable String loginname, @PathVariable String passwd) {
		Operator operator = operatorService.signin(loginname, passwd, role);
		Result result = null;
		if(operator != null) {
			result = new Result();
			result.setResult(operator.getId());
		}
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/signin/user/{loginname}/{passwd}/")
	public String signin(@PathVariable String loginname, @PathVariable String passwd) {
		User user = userService.signin(loginname, passwd);
		Result result = null;
		if(user != null) {
			result = new Result();
			result.setResult(user.getId());
		}
		return new Gson().toJson(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordtasks/operator/{operatorid}/{page}/")
	public String recordtasksForOperator(@PathVariable int operatorid, @PathVariable int page) {
		return new Gson().toJson(apiService.recordtasksForOperator(operatorid, page));
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordtasks/user/{userid}/{page}/")
	public String recordtasksForUser(@PathVariable int userid, @PathVariable int page) {
		return new Gson().toJson(apiService.recordtasksForUser(userid, page));
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordtask/{id}/")
	public String recordtask(@PathVariable int id) {
		try {
			return new Gson().toJson(apiService.recordtask(id));
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			return new Gson().toJson(null);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/recorddata/{id}/{page}/")
	public String recorddata(@PathVariable int id, @PathVariable int page) {
		try {
			return new Gson().toJson(apiService.recorddata(id, page));
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			return new Gson().toJson(null);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/reporttasks/operator/{operatorid}/{page}/")
	public String reporttasksForOperator(@PathVariable int operatorid, @PathVariable int page) {
		return new Gson().toJson(apiService.reporttasksForOperator(operatorid, page));
	}
	
	@ResponseBody
	@RequestMapping(value = "/reporttasks/user/{userid}/{page}/")
	public String reporttasksForUser(@PathVariable int userid, @PathVariable int page) {
		return new Gson().toJson(apiService.reporttasksForUser(userid, page));
	}
	
	@ResponseBody
	@RequestMapping(value = "/reporttask/{id}/")
	public String reporttask(@PathVariable int id) {
		try {
			return new Gson().toJson(apiService.reporttask(id));
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			return new Gson().toJson(null);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/reminds/operator/{operatorid}/{page}/")
	public String remindsForOperator(@PathVariable int operatorid, @PathVariable int page) {
		return new Gson().toJson(apiService.remindsForOperator(operatorid, page));
	}
	
	@ResponseBody
	@RequestMapping(value = "/reminds/user/{userid}/{page}/")
	public String remindsForUser(@PathVariable int userid, @PathVariable int page) {
		return new Gson().toJson(apiService.remindsForUser(userid, page));
	}
	
	@ResponseBody
	@RequestMapping(value = "/remind/{id}/")
	public String remind(@PathVariable int id) {
		return new Gson().toJson(apiService.remind(id));
	}
	
	@ResponseBody
	@RequestMapping(value = "/remind/read/{id}/")
	public String read(@PathVariable int id) {
		return new Gson().toJson(apiService.read(id));
	}
	
	@ResponseBody
	@RequestMapping(value = "/healthtasks/operator/{operatorid}/{page}/")
	public String healthtasksForOperator(@PathVariable int operatorid, @PathVariable int page) {
		return new Gson().toJson(apiService.healthtasksForOperator(operatorid, page));
	}
	
	@ResponseBody
	@RequestMapping(value = "/healthtasks/user/{userid}/{page}/")
	public String healthtasksForUser(@PathVariable int userid, @PathVariable int page) {
		return new Gson().toJson(apiService.healthtasksForUser(userid, page));
	}
	
	@ResponseBody
	@RequestMapping(value = "/healthtask/{id}/")
	public String healthtask(@PathVariable int id) {
		return new Gson().toJson(apiService.healthtask(id));
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}