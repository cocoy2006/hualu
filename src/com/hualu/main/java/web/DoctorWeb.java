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
import com.hualu.main.java.component.HealthTaskComponent;
import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.component.ReportTaskComponent;
import com.hualu.main.java.component.pagination.RecordTaskPagination;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Plan;
import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.service.DoctorService;
import com.hualu.main.java.service.PlanService;
import com.hualu.main.java.service.RemindService;
import com.hualu.main.java.service.ReportTaskService;
import com.hualu.main.java.service.UserService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Page;
import com.hualu.main.java.util.Status;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorWeb implements ServletContextAware {
	
	private static final String HOST = "doctor/";
	private static final String COMMON = "common/";
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private RemindService remindService;
	
	@Autowired
	private ReportTaskService reportTaskService;
	
	@Autowired
	private UserService userService;
	
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
	public String recordTaskList(HttpServletRequest request, @PathVariable int status) {
		Page<RecordTaskPagination> page = new Page<RecordTaskPagination>();
		page.setsEcho(request.getParameter("sEcho"));
		Operator doctor = (Operator) request.getSession().getAttribute("user");
		Integer iTotalRecords = doctorService.findRecordTaskCount(doctor.getId(), status);
		page.setiTotalRecords(iTotalRecords);
		page.setiTotalDisplayRecords(iTotalRecords);
		Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		List<RecordTaskPagination> aaData = doctorService.findRecordTaskList(doctor.getId(), status, iDisplayStart);
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
		Operator doctor = (Operator) session.getAttribute("user");
		mav.addObject("doctor", doctor);
		RecordTaskComponent rtc = doctorService.findRecordTask(taskid);
		mav.addObject("rtc", rtc);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordTask/next/{drtId}/")
	public String nextRecordTask(HttpServletRequest request, @PathVariable int drtId) {
		String advice = request.getParameter("advice");
		int result = doctorService.nextRecordTask(drtId, advice);
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordTask/draft/{drtId}/")
	public String draftRecordTask(HttpServletRequest request, @PathVariable int drtId) {
		String advice = request.getParameter("advice");
		int result = doctorService.draftRecordTask(drtId, advice);
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordTask/reject/{drtId}/")
	public String rejectRecordTask(HttpServletRequest request, @PathVariable int drtId) {
		String rejectadvice = request.getParameter("rejectadvice");
		int result = doctorService.rejectRecordTask(drtId, rejectadvice);
		return String.valueOf(result);
	}
	
	@RequestMapping(value = "/health/")
	public ModelAndView health() {
		ModelAndView mav = new ModelAndView(HOST + "health");
		return mav;
	}
	
	@RequestMapping(value = "/healthTaskList/{status}/")
	public ModelAndView healthTaskList(HttpSession session, @PathVariable int status) {
		ModelAndView mav = new ModelAndView();
		if(status == Status.HEALTH_TASK_RUNNING || status == Status.HEALTH_TASK_DRAFT) {
			mav.setViewName(HOST + "snippet/health_task_running_list");
		} else if(status == Status.HEALTH_TASK_DONE) {
			mav.setViewName(HOST + "snippet/health_task_done_list");
		}
		Operator doctor = (Operator) session.getAttribute("user");
		mav.addObject("doctor", doctor);
		List<HealthTaskComponent> htcs = doctorService.findHealthTaskList(doctor.getId(), status);
		mav.addObject("htcs", htcs);
		return mav;
	}
	
	@RequestMapping(value = "/healthTask/{dhtId}/")
	public ModelAndView healthTask(HttpSession session, @PathVariable int dhtId) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/health_task_modal");
		HealthTaskComponent htc = doctorService.findHealthTask(dhtId);
		mav.addObject("htc", htc);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/healthTask/update/{dhtId}/{mode}/")
	public String healthTaskUpdate(HttpServletRequest request, 
			@PathVariable int dhtId, @PathVariable int mode) {
		String advice = request.getParameter("advice");
		int result = doctorService.updateHealthTask(dhtId, advice, mode);
		return String.valueOf(result);
	}
	
	@RequestMapping(value = "/selectUser/")
	public ModelAndView selectUser() {
		ModelAndView mav = new ModelAndView(HOST + "snippet/select_user_modal");
		List<User> users = userService.findAll();
		mav.addObject("users", users);
		return mav;
	}
	
	@RequestMapping(value = "/remind/")
	public ModelAndView remind() {
		ModelAndView mav = new ModelAndView(HOST + "remind");
		return mav;
	}
	
	@RequestMapping(value = "/remindUser/{id}/")
	public ModelAndView remindUserInfo(@PathVariable int id) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/remind_user");
		User user = userService.findById(id);
		mav.addObject("u", user);
		List<Remind> reminds = remindService.findBySent(id, Status.REMIND_UNSENT);
		mav.addObject("reminds", reminds);
		return mav;
	}
	
	@RequestMapping(value = "/remindModal/{mode}/")
	public ModelAndView remindModal(HttpServletRequest request, @PathVariable int mode) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/remind_modal");
		Operator doctor = (Operator) request.getSession().getAttribute("user");
		mav.addObject("operator", doctor);
		if(mode == 2) { // update
			int id = Integer.parseInt(request.getParameter("id"));
			Remind remind = remindService.findById(id);
			mav.addObject("remind", remind);
		}
		return mav;
	}
	
	@RequestMapping(value = "/plan/")
	public ModelAndView plan() {
		ModelAndView mav = new ModelAndView(HOST + "plan");
		return mav;
	}
	
	@RequestMapping(value = "/planUser/{id}/")
	public ModelAndView planUserInfo(@PathVariable int id) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/plan_user");
		User user = userService.findById(id);
		mav.addObject("u", user);
		List<Plan> plans = planService.findBySent(id, Status.UNSENT);
		mav.addObject("plans", plans);
		return mav;
	}
	
	@RequestMapping(value = "/planModal/{mode}/")
	public ModelAndView planModal(HttpServletRequest request, @PathVariable int mode) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/plan_modal");
		Operator doctor = (Operator) request.getSession().getAttribute("user");
		mav.addObject("operator", doctor);
		if(mode == 2) { // update
			int id = Integer.parseInt(request.getParameter("id"));
			Plan plan = planService.findById(id);
			mav.addObject("plan", plan);
		}
		return mav;
	}
	
	@RequestMapping(value = "/report/")
	public ModelAndView report() {
		ModelAndView mav = new ModelAndView(HOST + "report");
		return mav;
	}
	
	@RequestMapping(value = "/reportTaskList/{status}/")
	public ModelAndView reportTaskList(HttpSession session, @PathVariable int status) {
		ModelAndView mav = new ModelAndView();
		if(status == Status.ReportTask.DOCTOR_PROCESSING.getInt() 
				|| status == Status.ReportTask.DOCTOR_DRAFT.getInt()) {
			mav.setViewName(HOST + "snippet/report_task_doctor_processing_list");
		} else if(status == Status.ReportTask.DONE.getInt()) {
			mav.setViewName(HOST + "snippet/report_task_done_list");
		}
		Operator doctor = (Operator) session.getAttribute("user");
		mav.addObject("doctor", doctor);
		List<ReportTaskComponent> rtcs = doctorService.findReportTaskList(doctor.getId(), status);
		mav.addObject("rtcs", rtcs);
		return mav;
	}
	
	@RequestMapping(value = "/reportTask/{id}/")
	public ModelAndView reportTask(@PathVariable int id) {
		ModelAndView mav = new ModelAndView(COMMON + "report/report_task_modal");
		ReportTaskComponent rtc = reportTaskService.findReportTask(id);
		mav.addObject("rtc", rtc);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/reportTask/next/{id}/")
	public String nextReportTask(HttpServletRequest request, @PathVariable int id) {
		String advice = request.getParameter("advice");
		int result = doctorService.nextReportTask(id, advice);
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/reportTask/draft/{id}/")
	public String draftReportTask(HttpServletRequest request, @PathVariable int id) {
		String advice = request.getParameter("advice");
		int result = doctorService.draftReportTask(id, advice);
		return String.valueOf(result);
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