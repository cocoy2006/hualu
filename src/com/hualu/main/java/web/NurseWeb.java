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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hualu.main.java.component.HealthTaskComponent;
import com.hualu.main.java.component.RecordTaskComponent;
import com.hualu.main.java.component.UserComponent;
import com.hualu.main.java.component.dm.DeviceComponent;
import com.hualu.main.java.component.nurse.DoctorComponent;
import com.hualu.main.java.component.pagination.RecordTaskPagination;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.service.DeviceReceiveService;
import com.hualu.main.java.service.HealthTaskService;
import com.hualu.main.java.service.NurseService;
import com.hualu.main.java.service.OperatorService;
import com.hualu.main.java.service.RemindService;
import com.hualu.main.java.service.UserService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Page;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.fileupload.FileUploadListener;

@Controller
@RequestMapping(value = "/nurse")
public class NurseWeb implements ServletContextAware {
	
	private static final String HOST = "nurse/";
	
	@Autowired
	private DeviceReceiveService deviceReceiveService;
	
	@Autowired
	private HealthTaskService healthTaskService;
	
	@Autowired
	private NurseService nurseService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private RemindService remindService;
	
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
	public String recordTaskListTest(HttpServletRequest request, @PathVariable int status) {
		Page<RecordTaskPagination> page = new Page<RecordTaskPagination>();
		page.setsEcho(request.getParameter("sEcho"));
		Operator nurse = (Operator) request.getSession().getAttribute("user");
		Integer iTotalRecords = nurseService.findRecordTaskCount(nurse.getId(), status);
		page.setiTotalRecords(iTotalRecords);
		page.setiTotalDisplayRecords(iTotalRecords);
		Integer iDisplayStart = Integer.valueOf(request.getParameter("iDisplayStart"));
		List<RecordTaskPagination> aaData = nurseService.findRecordTaskList(nurse.getId(), status, iDisplayStart);
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
		RecordTaskComponent rtc = nurseService.findRecordTask(taskid);
		mav.addObject("rtc", rtc);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordTask/next/{nrtId}/")
	public String nextRecordTask(HttpServletRequest request, @PathVariable int nrtId) {
		String advice = request.getParameter("advice");
		String piecestarttime = request.getParameter("piecestarttime");
		String pieceendtime = request.getParameter("pieceendtime");
		String pieceStartIndex = request.getParameter("pieceStartIndex");
		String pieceEndIndex = request.getParameter("pieceEndIndex");
		String pieceStartPage = request.getParameter("pieceStartPage");
		String pieceEndPage = request.getParameter("pieceEndPage");
		String ecgwaveLength = request.getParameter("ecgwaveLength");
		int result = nurseService.nextRecordTask(nrtId, piecestarttime, pieceendtime, 
				pieceStartIndex, pieceEndIndex, pieceStartPage, pieceEndPage, ecgwaveLength, advice);
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/recordTask/recall/{nrtId}/")
	public String recallRecordTask(HttpServletRequest request, @PathVariable int nrtId) {
		String recalladvice = request.getParameter("recalladvice");
		int result = nurseService.recallRecordTask(nrtId, recalladvice);
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
		if(status == Status.HEALTH_TASK_RUNNING) {
			mav.setViewName(HOST + "snippet/health_task_running_list");
		} else if(status == Status.HEALTH_TASK_DONE) {
			mav.setViewName(HOST + "snippet/health_task_done_list");
		}
		Operator nurse = (Operator) session.getAttribute("user");
		mav.addObject("nurse", nurse);
		List<HealthTaskComponent> htcs = nurseService.findHealthTaskList(nurse.getId(), status);
		mav.addObject("htcs", htcs);
		return mav;
	}
	
	@RequestMapping(value = "/health/save/")
	public ModelAndView healthSave(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "health_save");
		String taskid = request.getParameter("taskid");
		if(taskid != null) {
			HealthTaskComponent htc = healthTaskService.findComponentById(Integer.parseInt(taskid));
			mav.addObject("htc", htc);
		}
		return mav;
	}
	
	@RequestMapping(value = "/selectUser/")
	public ModelAndView selectUser() {
		ModelAndView mav = new ModelAndView(HOST + "snippet/select_user_modal");
		List<User> users = userService.findAll();
		mav.addObject("users", users);
		return mav;
	}
	
	@RequestMapping(value = "/healthSelectDoctor/")
	public ModelAndView healthSelectDoctor() {
		ModelAndView mav = new ModelAndView(HOST + "snippet/health_task_select_doctor_modal");
		List<DoctorComponent> dcs = nurseService.findDoctors();
		mav.addObject("dcs", dcs);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/healthTask/save/")
	public String saveHealthTask(HttpServletRequest request) {
		Operator nurse = (Operator) request.getSession().getAttribute("user");
		int userid = Integer.parseInt(request.getParameter("userid"));
		int doctorid = Integer.parseInt(request.getParameter("doctorid"));
		String disname = request.getParameter("disname");
		String disscript = request.getParameter("disscript");
		String donetreat = request.getParameter("donetreat");
		String desireaid = request.getParameter("desireaid");
		String cloudrecordid = request.getParameter("cloudrecordid");
		nurseService.saveHealthTask(nurse, userid, doctorid, disname,
				disscript, donetreat, desireaid, cloudrecordid);
		return String.valueOf(Status.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping(value = "/healthTask/update/{taskid}/")
	public String updateHealthTask(HttpServletRequest request,
			@PathVariable int taskid) {
		String disname = request.getParameter("disname");
		String disscript = request.getParameter("disscript");
		String donetreat = request.getParameter("donetreat");
		String desireaid = request.getParameter("desireaid");
		String cloudrecordid = request.getParameter("cloudrecordid");
		nurseService.updateHealthTask(taskid, disname, disscript, donetreat,
				desireaid, cloudrecordid);
		return String.valueOf(Status.SUCCESS);
	}
	
	@RequestMapping(value = "/user/")
	public ModelAndView user() {
		ModelAndView mav = new ModelAndView(HOST + "user");
		List<Operator> doctors = operatorService.findByRole(Status.OPERATOR_DOCTOR);
		mav.addObject("doctors", doctors);
		return mav;
	}
	
	@RequestMapping(value = "/userlist/")
	public ModelAndView userList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/user_list");
		String doctoridString = request.getParameter("doctorid");
		String userlevelString = request.getParameter("userlevel");
		String usergroupString = request.getParameter("usergroup");
		String name = request.getParameter("name");
		String loginname = request.getParameter("loginname");
		int doctorid = 0;
		if(doctoridString != null) {
			doctorid = Integer.parseInt(doctoridString);
		}
		int userlevel = 0;
		if(userlevelString != null) {
			userlevel = Integer.parseInt(userlevelString);
		}
		int usergroup = 0;
		if(usergroupString != null) {
			usergroup = Integer.parseInt(usergroupString);
		}
		List<UserComponent> ucs = userService.findComponentAll(doctorid, userlevel, usergroup, name, loginname);
		mav.addObject("ucs", ucs);
		return mav;
	}
	
	@RequestMapping(value = "/userModal/{mode}/")
	public ModelAndView userModal(HttpServletRequest request, @PathVariable int mode) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/user_modal");
		List<Operator> doctors = operatorService.findByRole(Status.OPERATOR_DOCTOR);
		mav.addObject("doctors", doctors);
		if(mode == 2) { // update
			int id = Integer.parseInt(request.getParameter("id"));
			User user = userService.findById(id);
			mav.addObject("u", user);
		}
		return mav;
	}
	
	@RequestMapping(value = "/boundModal/{userid}/")
	public ModelAndView boundModal(HttpServletRequest request, @PathVariable int userid) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/user_bound_modal");
		mav.addObject("userid", userid);
		List<DeviceComponent> dcs = deviceReceiveService.findAll(Status.DEVICE_OCCUPATION);
		mav.addObject("dcs", dcs);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/bound/")
	public String bound(HttpServletRequest request) {
		int deviceid = Integer.parseInt(request.getParameter("deviceid"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		int result = nurseService.bound(deviceid, userid);
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping(value = "/unbound/")
	public String unbound(HttpServletRequest request) {
		int userid = Integer.parseInt(request.getParameter("userid"));
		int result = nurseService.unbound(userid);
		return String.valueOf(result);
	}
	
	@RequestMapping(value = "/reportModal/{userid}/")
	public ModelAndView reportModal(HttpServletRequest request, @PathVariable int userid) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/report_modal");
		mav.addObject("userid", userid);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/reportUpload/")
	public String reportUpload(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		try {
			int userid = Integer.parseInt(request.getParameter("userid"));
			String boundtime = request.getParameter("boundtime");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
			nurseService.reportUpload(userid, boundtime, file);
			// force set percentDone to 100%
			FileUploadListener listener = (FileUploadListener) session.getAttribute("uploadProgressListener");
			if(listener != null) {
				listener.setPercentDone(100);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return String.valueOf(Status.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping(value = "/percentDone", method = RequestMethod.GET)
	public String loadPercent(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		FileUploadListener listener = (FileUploadListener) session.getAttribute("uploadProgressListener");
		if(listener == null) {
			return "-1";
		}
		double percent = listener.getPercentDone();
		return String.valueOf(percent);
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
		Operator nurse = (Operator) request.getSession().getAttribute("user");
		mav.addObject("operator", nurse);
		if(mode == 2) { // update
			int id = Integer.parseInt(request.getParameter("id"));
			Remind remind = remindService.findById(id);
			mav.addObject("remind", remind);
		}
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