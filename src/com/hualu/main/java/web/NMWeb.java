package com.hualu.main.java.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.component.SignlogComponent;
import com.hualu.main.java.component.pagination.RecordTaskPagination;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.service.NMService;
import com.hualu.main.java.service.OperatorService;
import com.hualu.main.java.service.SignlogService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Page;
import com.hualu.main.java.util.Status;

@Controller
@RequestMapping(value = "/nm")
public class NMWeb implements ServletContextAware {
	
	private final static String HOST = "nm/";
	
	@Autowired
	private NMService nmService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private SignlogService signlogService;
	
	@RequestMapping(value = "/main/")
	public ModelAndView main(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "main");
		Operator user = (Operator) session.getAttribute("user");
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/record/")
	public ModelAndView record() {
		ModelAndView mav = new ModelAndView(HOST + "record");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordTaskList/{status}/")
	public String recordTaskListTest(HttpServletRequest request, @PathVariable int status) {
		Page<RecordTaskPagination> page = new Page<RecordTaskPagination>();
		page.setsEcho(request.getParameter("sEcho"));
		Operator nurse = (Operator) request.getSession().getAttribute("user");
		Integer iTotalRecords = nmService.findRecordTaskCount(nurse.getId(), status);
		page.setiTotalRecords(iTotalRecords);
		page.setiTotalDisplayRecords(iTotalRecords);
		Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		List<RecordTaskPagination> aaData = nmService.findRecordTaskList(nurse.getId(), status, iDisplayStart);
		if(aaData != null) {
			page.setAaData(aaData);
		} else {
			page.setAaData(new ArrayList<RecordTaskPagination>());
		}
		return new Gson().toJson(page);
	}
	
	@RequestMapping(value = "/recordTask/{taskid}/")
	public ModelAndView recordTask(HttpSession session, @PathVariable int taskid) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/record_task_modal");
		Operator nurse = (Operator) session.getAttribute("user");
		mav.addObject("nurse", nurse);
		RecordTaskComponent rtc = nmService.findRecordTask(taskid);
		mav.addObject("rtc", rtc);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordTask/{mrtId}/next/")
	public String nextRecordTask(HttpServletRequest request, @PathVariable int mrtId) {
		int result = nmService.nextRecordTask(mrtId);
		return String.valueOf(result);
	}
	
	@RequestMapping(value = "/signlog/")
	public ModelAndView signlog() {
		ModelAndView mav = new ModelAndView(HOST + "signlog");
		List<Operator> operators = operatorService.findAll();
		mav.addObject("operators", operators);
		return mav;
	}
	
	@RequestMapping(value = "/signlogList/")
	public ModelAndView signlogList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/signlog_list");
		Operator operator = (Operator) request.getSession().getAttribute("user");
		int self = operator.getRole();
		String roleString = request.getParameter("role");
		String operatoridString = request.getParameter("operatorid");
		int role = 0;
		if(roleString != null) {
			role = Integer.parseInt(roleString);
		}
		int operatorid = 0;
		if(operatoridString != null) {
			operatorid = Integer.parseInt(operatoridString);
		}
		List<SignlogComponent> scs = signlogService.findComponentAll(self, role, operatorid);
		mav.addObject("scs", scs);
		return mav;
	}
	
	@RequestMapping(value = "/signlogOperatorList/")
	public ModelAndView signlogOperatorList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/signlog_operator_list");
		String roleString = request.getParameter("role");
		int role = 0;
		if(roleString != null) {
			role = Integer.parseInt(roleString);
		}
//		List<Operator> operators;
//		if(role != 0) {
//			operators = operatorService.findByRole(role);
//		} else {
//			operators = operatorService.findByRole(Status.OPERATOR_NURSE);
//			operators.addAll(operatorService.findByRole(Status.OPERATOR_NURSE_LEADER));
//		}
		mav.addObject("operators", nmService.findOperatorsByRole(role));
		return mav;
	}
	
	@RequestMapping(value = "/info/")
	public ModelAndView info() {
		ModelAndView mav = new ModelAndView(HOST + "info");
		return mav;
	}

	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}