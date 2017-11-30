package com.hualu.main.java.web;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Signlog;
import com.hualu.main.java.form.OperatorForm;
import com.hualu.main.java.service.OperatorService;
import com.hualu.main.java.service.SignlogService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.converter.GenericConverter;

@Controller
@RequestMapping(value = "/operator")
public class OperatorWeb implements ServletContextAware {
	
	@Autowired
	private OperatorService service;
	
	@Autowired
	private SignlogService signlogService;
	
	@ResponseBody
	@RequestMapping(value = "/signin/")
	public String signin(HttpServletRequest request) {
		String loginname = request.getParameter("loginname");
		String passwd = request.getParameter("passwd");
		int role = Integer.parseInt(request.getParameter("role"));
		Operator operator = service.signin(loginname, passwd, role);
		if(operator != null) {
			// add new signlog
			Signlog signlog = new Signlog();
			signlog.setOperatorid(operator.getId());
			signlog.setSignintime(Hualu.getStandardTimestamp());
			signlog.setIp(Hualu.getIpAddress(request));
			signlogService.saveOrUpdate(signlog);
			// other actions
			HttpSession session = request.getSession();
			session.setAttribute("user", operator);
			return String.valueOf(Status.SUCCESS);
		}
		return String.valueOf(Status.ERROR);
	}
	
	@RequestMapping(value = "/signout/")
	public ModelAndView signout(HttpSession session) {
		ModelAndView mav = new ModelAndView("admin");
		session.invalidate();
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate/")
	public String saveOrUpdate(@ModelAttribute("operator") OperatorForm operatorForm) {
		Operator operator = new Operator();
		try {
			operator = (Operator) GenericConverter.convert(operatorForm, operator);
			if(operator.getCreatetime() == null) {
				operator.setCreatetime(Hualu.getStandardTimestamp());
			}
			service.saveOrUpdate(operator);
		} catch (ParseException e) {
			return String.valueOf(Status.ERROR);
		}
		return String.valueOf(Status.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/")
	public String update(HttpServletRequest request) {
		String newPasswd = request.getParameter("newPasswd");
		Operator operator = (Operator) request.getSession().getAttribute("user");
		return String.valueOf(service.update(operator, newPasswd));
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateIsonline/")
	public String updateIsonline(HttpServletRequest request) {
		Operator operator = (Operator) request.getSession().getAttribute("user");
		int result = Status.ERROR;
		if(operator != null) {
			int currentStatus = operator.getIsonline();
			if(Status.OPERATOR_ONLINE == currentStatus) {
				operator.setIsonline(Status.OPERATOR_RESTING);
			} else if(Status.OPERATOR_RESTING == currentStatus) {
				operator.setIsonline(Status.OPERATOR_ONLINE);
			}
			service.saveOrUpdate(operator);
			result = Status.SUCCESS;
		}
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/remove/{id}/")
	public String remove(@PathVariable int id) {
		return String.valueOf(service.remove(id));
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}