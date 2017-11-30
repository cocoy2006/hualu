package com.hualu.main.java.web;

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
import com.hualu.main.java.component.OperationlogComponent;
import com.hualu.main.java.component.SignlogComponent;
import com.hualu.main.java.component.dm.DeviceComponent;
import com.hualu.main.java.entity.Company;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.entity.User;
import com.hualu.main.java.service.CompanyService;
import com.hualu.main.java.service.DeviceReceiveService;
import com.hualu.main.java.service.DeviceService;
import com.hualu.main.java.service.ManagerService;
import com.hualu.main.java.service.OperationlogService;
import com.hualu.main.java.service.OperatorService;
import com.hualu.main.java.service.SignlogService;
import com.hualu.main.java.service.UserService;
import com.hualu.main.java.util.Hualu;

@Controller
@RequestMapping(value = "/manager")
public class ManagerWeb implements ServletContextAware {
	
	private final static String HOST = "manager/";
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceReceiveService drService;
	
	@Autowired
	private ManagerService service;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SignlogService signlogService;
	
	@Autowired
	private OperationlogService operationlogService;
	
	@RequestMapping(value = "/main/")
	public ModelAndView main(HttpSession session) {
		ModelAndView mav = new ModelAndView(HOST + "main");
		Operator user = (Operator) session.getAttribute("user");
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value = "/device/")
	public ModelAndView device() {
		ModelAndView mav = new ModelAndView(HOST + "device");
		List<Company> companys = companyService.findAll();
		mav.addObject("companys", companys);
		return mav;
	}
	
	@RequestMapping(value = "/devicelist/")
	public ModelAndView devicelist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/device_list");
		String dmodel = request.getParameter("dmodel");
		String dsn = request.getParameter("dsn");
		String dname = request.getParameter("dname");
		String comidString = request.getParameter("comid");
		String dstatusString = request.getParameter("dstatus");
		int comid = 0;
		if(comidString != null) {
			comid = Integer.parseInt(comidString);
		}
		int dstatus = 0;
		if(dstatusString != null) {
			dstatus = Integer.parseInt(dstatusString);
		}
		List<DeviceComponent> dcs = deviceService.loadAllDevices(dmodel, dsn, dname, comid, dstatus);
		mav.addObject("dcs", dcs);
		return mav;
	}
	
	@RequestMapping(value = "/selectNurse/")
	public ModelAndView selectNurse() {
		ModelAndView mav = new ModelAndView(HOST + "snippet/select_nurse_modal");
		List<Operator> nurses = operatorService.findNurses();
		mav.addObject("nurses", nurses);
		return mav;
	}
	
	@RequestMapping(value = "/selectUser/")
	public ModelAndView selectUser() {
		ModelAndView mav = new ModelAndView(HOST + "snippet/select_user_modal");
		List<User> users = userService.findAll();
		mav.addObject("users", users);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateNurse/{deviceid}/{nurseid}/")
	public String updateNurse(@PathVariable int deviceid, @PathVariable int nurseid) {
		int result = drService.updateNurse(deviceid, nurseid);
		return new Gson().toJson(result);
	}
	
	@RequestMapping(value = "/operator/")
	public ModelAndView operator() {
		ModelAndView mav = new ModelAndView(HOST + "operator");
		return mav;
	}
	
	@RequestMapping(value = "/operatorlist/")
	public ModelAndView operatorlist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/operator_list");
		String roleString = request.getParameter("role");
		int role = 0;
		if(roleString != null) {
			role = Integer.parseInt(roleString);
		}
		mav.addObject("operators", service.findOperatorsByRole(role));
		return mav;
	}
	
	@RequestMapping(value = "/operatorModal/{mode}/")
	public ModelAndView operatorModal(HttpServletRequest request, @PathVariable int mode) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/operator_modal");
		if(mode == 2) { // update
			int id = Integer.parseInt(request.getParameter("id"));
			Operator operator = operatorService.findById(id);
			mav.addObject("operator", operator);
		}
		return mav;
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
		mav.addObject("operators", service.findOperatorsByRole(role));
		return mav;
	}
	
	@RequestMapping(value = "/operationlog/")
	public ModelAndView operationlog() {
		ModelAndView mav = new ModelAndView(HOST + "operationlog");
		return mav;
	}
	
	@RequestMapping(value = "/operationloglist/")
	public ModelAndView operationlogList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/operationlog_list");
		String operatorid = request.getParameter("operatorid");
		String operatorrole = request.getParameter("operatorrole");
		String operationtype = request.getParameter("operationtype");
		String targettype = request.getParameter("targettype");
		String start = request.getParameter("start");
		String finish = request.getParameter("finish");
		List<OperationlogComponent> ocs = operationlogService
			.findAll(operatorid, operatorrole, operationtype, targettype, start, finish);
		mav.addObject("ocs", ocs);
		return mav;
	}
	
	@RequestMapping(value = "/operationlogOperatorlist/")
	public ModelAndView operationlogOperatorlist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/operationlog_operator_list");
		String roleString = request.getParameter("operatorrole");
		int role = 0;
		if(roleString != null) {
			role = Integer.parseInt(roleString);
		}
		mav.addObject("operators", service.findOperatorsByRole(role));
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