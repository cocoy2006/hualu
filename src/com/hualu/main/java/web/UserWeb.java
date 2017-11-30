package com.hualu.main.java.web;

import java.text.ParseException;
import java.util.List;

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

import com.hualu.main.java.component.HealthTaskComponent;
import com.hualu.main.java.component.PlanComponent;
import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.component.RemindComponent;
import com.hualu.main.java.component.ReportTaskComponent;
import com.hualu.main.java.entity.ReportTask;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.form.UserForm;
import com.hualu.main.java.service.PlanService;
import com.hualu.main.java.service.RemindService;
import com.hualu.main.java.service.ReportTaskService;
import com.hualu.main.java.service.UserService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.converter.GenericConverter;

@Controller
@RequestMapping(value = "/user")
public class UserWeb implements ServletContextAware {
	
	private static final String HOST = "user/";
	private static final String COMMON = "common/";
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private RemindService remindService;
	
	@Autowired
	private ReportTaskService reportTaskService;
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/main/")
	public ModelAndView main(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "main");
		User user = (User) session.getAttribute("user");
		mav.addObject("user", user);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/signin/")
	public String signin(HttpServletRequest request) {
		String loginname = request.getParameter("loginname");
		String passwd = request.getParameter("passwd");
		User user = service.signin(loginname, passwd);
		if(user != null) {
			HttpSession session = request.getSession();
			// find number of unread messages
			int unreadRemindNumber = remindService.findRemindNumber(user.getId(), Status.REMIND_UNREAD);
			session.setAttribute("unreadRemindNumber", unreadRemindNumber);
			// other actions
			session.setAttribute("user", user);
			return String.valueOf(Status.SUCCESS);
		}
		return String.valueOf(Status.ERROR);
	}
	
	@RequestMapping(value = "/signout/")
	public ModelAndView signout(HttpSession session) {
		ModelAndView mav = new ModelAndView("index");
		if(session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate/")
	public String saveOrUpdate(@ModelAttribute("user") UserForm userForm) {
		User user = new User();
		int result = Status.SUCCESS;
		try {
			user = (User) GenericConverter.convert(userForm, user);
			result = service.saveOrUpdate(user);
		} catch (ParseException e) {
			result = Status.ERROR;
		}
		return String.valueOf(result);
	}
	
	@RequestMapping(value = "/record/")
	public ModelAndView record() {
		ModelAndView mav = new ModelAndView(HOST + "record");
		return mav;
	}
	
	@RequestMapping(value = "/recordTaskList/")
	public ModelAndView recordTaskList(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/record_task_list");
		User user = (User) session.getAttribute("user");
//		mav.addObject("u", user);
		List<RecordTaskComponent> rtcs = service.findRecordTaskList(user.getId());
		mav.addObject("rtcs", rtcs);
		return mav;
	}
	
	@RequestMapping(value = "/recordTask/{id}/")
	public ModelAndView recordTask(HttpSession session, @PathVariable int id) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/record_task_modal");
		RecordTaskComponent rtc = service.findRecordTask(id);
		mav.addObject("rtc", rtc);
		return mav;
	}
	
	@RequestMapping(value = "/health/")
	public ModelAndView health() {
		ModelAndView mav = new ModelAndView(HOST + "health");
		return mav;
	}
	
	@RequestMapping(value = "/healthTaskList/")
	public ModelAndView healthTaskList(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/health_task_list");
		User user = (User) session.getAttribute("user");
		List<HealthTaskComponent> htcs = service.findHealthTaskList(user.getId());
		mav.addObject("htcs", htcs);
		return mav;
	}
	
	@RequestMapping(value = "/healthTask/{id}/")
	public ModelAndView healthTask(HttpSession session, @PathVariable int id) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/health_task_modal");
		HealthTaskComponent htc = service.findHealthTask(id);
		mav.addObject("htc", htc);
		return mav;
	}
	
	@RequestMapping(value = "/device/")
	public ModelAndView device() {
		ModelAndView mav = new ModelAndView(HOST + "device");
		return mav;
	}
	
	@RequestMapping(value = "/msg/")
	public ModelAndView msg(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "msg");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/remindNumber/{status}/")
	public String remindNumber(HttpSession session, @PathVariable int status) {
		User user = (User) session.getAttribute("user");
		int unreadRemindNumber = remindService.findRemindNumber(user.getId(), status);
		return String.valueOf(unreadRemindNumber);
	}
	
	@RequestMapping(value = "/remindList/")
	public ModelAndView remindList(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/remind_list");
		User user = (User) session.getAttribute("user");
		mav.addObject("user", user);
		List<RemindComponent> rcs = remindService.findByUserid(user.getId());
		mav.addObject("rcs", rcs);
		return mav;
	}
	
	@RequestMapping(value = "/remindList/{status}/")
	public ModelAndView remindList(HttpSession session, @PathVariable int status) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/remind_list");
		User user = (User) session.getAttribute("user");
		mav.addObject("user", user);
		List<RemindComponent> rcs = remindService.findByUseridAndStatus(user.getId(), status);
		mav.addObject("rcs", rcs);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/planNumber/{status}/")
	public String planNumber(HttpSession session, @PathVariable int status) {
		User user = (User) session.getAttribute("user");
		int unreadPlanNumber = planService.findPlanNumber(user.getId(), status);
		return String.valueOf(unreadPlanNumber);
	}
	
	@RequestMapping(value = "/planList/")
	public ModelAndView planList(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/plan_list");
		User user = (User) session.getAttribute("user");
		mav.addObject("user", user);
		List<PlanComponent> pcs = planService.findByUserid(user.getId());
		mav.addObject("pcs", pcs);
		return mav;
	}
	
	@RequestMapping(value = "/report/")
	public ModelAndView report() {
		ModelAndView mav = new ModelAndView(HOST + "report");
		return mav;
	}
	
	@RequestMapping(value = "/reportTaskList/")
	public ModelAndView reportTaskList(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/report_task_list");
		User user = (User) session.getAttribute("user");
		mav.addObject("user", user);
		List<ReportTask> rts = service.findReportTaskList(user.getId());
		mav.addObject("rts", rts);
		return mav;
	}
	
	@RequestMapping(value = "/reportTask/{id}/")
	public ModelAndView reportTask(@PathVariable int id) {
		ModelAndView mav = new ModelAndView(COMMON + "report/report_task_modal");
		ReportTaskComponent rtc = reportTaskService.findReportTask(id);
		mav.addObject("rtc", rtc);
		return mav;
	}
	
	@RequestMapping(value = "/info/")
	public ModelAndView info() {
		ModelAndView mav = new ModelAndView(HOST + "info");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update/")
	public String update(HttpServletRequest request) {
		String newPasswd = request.getParameter("newPasswd");
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			return String.valueOf(service.update(user, newPasswd));
		}
		return String.valueOf(Status.ERROR);
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