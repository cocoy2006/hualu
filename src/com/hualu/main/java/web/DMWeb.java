package com.hualu.main.java.web;

import java.sql.Timestamp;
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

import com.hualu.main.java.component.dm.DeviceComponent;
import com.hualu.main.java.component.dm.PackageComponent;
import com.hualu.main.java.entity.Company;
import com.hualu.main.java.entity.DevicePackage;
import com.hualu.main.java.entity.Operator;
import com.hualu.main.java.service.CompanyService;
import com.hualu.main.java.service.DMService;
import com.hualu.main.java.service.DeviceService;
import com.hualu.main.java.service.OperatorService;
import com.hualu.main.java.service.PackageService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;

@Controller
@RequestMapping(value = "/dm")
public class DMWeb implements ServletContextAware {

	private static final String HOST = "dm/";
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DMService service;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private PackageService packageService;
	
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
	
	@RequestMapping(value = "/checkoutModal/{id}/")
	public ModelAndView checkoutModal(@PathVariable int id) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/device_checkout_modal");
		mav.addObject("deviceid", id);
		String occupationtime = Hualu.getStandardDatetime();
		mav.addObject("occupationtime", occupationtime);
		List<Operator> nurses = operatorService.findByRole(Status.OPERATOR_NURSE);
		mav.addObject("nurses", nurses);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkout/")
	public String checkout(HttpServletRequest request) {
		int nurseid = Integer.parseInt(request.getParameter("nurseid"));
		String address = request.getParameter("address");
		String memo = request.getParameter("memo");
		String occupationtimeString = request.getParameter("occupationtime");
		Timestamp occupationtime;
		if(occupationtimeString != "") {
			occupationtime = Timestamp.valueOf(occupationtimeString);
		} else {
			occupationtime = Hualu.getStandardTimestamp();
		}
		String[] deviceids = request.getParameterValues("deviceid");
		service.checkout(deviceids, nurseid, occupationtime, address, memo);
		return String.valueOf(Status.SUCCESS);
	}
	
	@RequestMapping(value = "/add/")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView(HOST + "add");
		List<Company> companys = companyService.findAll();
		mav.addObject("companys", companys);
		return mav;
	}
	
	@RequestMapping(value = "/packagelistForAdd/")
	public ModelAndView packagelistForAdd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/add_package_list");
		String comidString = request.getParameter("comid");
		int comid = 0;
		if(comidString != null) {
			comid = Integer.parseInt(comidString);
		}
		List<PackageComponent> pcs = service.loadAllPackages(comid);
		mav.addObject("pcs", pcs);
		return mav;
	}
	
	@RequestMapping(value = "/devicelistForAdd/")
	public ModelAndView devicelistForAdd(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/add_device_list");
		String packageIdString = request.getParameter("packageId");
		int packageId = 0;
		if(packageIdString != null) {
			packageId = Integer.parseInt(packageIdString);
		}
		List<DeviceComponent> dcs = service.loadAllDevices(packageId);
		mav.addObject("dcs", dcs);
		return mav;
	}
	
	@RequestMapping(value = "/company/")
	public ModelAndView company() {
		ModelAndView mav = new ModelAndView(HOST + "company");
		return mav;
	}
	
	@RequestMapping(value = "/companylist/")
	public ModelAndView companylist() {
		ModelAndView mav = new ModelAndView(HOST + "snippet/company_list");
		List<Company> companys = companyService.findAll();
		mav.addObject("companys", companys);
		return mav;
	}
	
	@RequestMapping(value = "/companymodal/{mode}/")
	public ModelAndView companymodal(HttpServletRequest request, @PathVariable int mode) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/company_modal");
		if(mode == 2) { // update
			int id = Integer.parseInt(request.getParameter("id"));
			Company company = companyService.findById(id);
			mav.addObject("company", company);
		}
		return mav;
	}
	
	@RequestMapping(value = "/pack/{comid}/")
	public ModelAndView pack(@PathVariable int comid) {
		ModelAndView mav = new ModelAndView(HOST + "pack");
		Company company = companyService.findById(comid);
		mav.addObject("company", company);
		return mav;
	}
	
	@RequestMapping(value = "/packlist/{comid}/")
	public ModelAndView packlist(@PathVariable int comid) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/company_package_list");
		Company company = companyService.findById(comid);
		mav.addObject("company", company);
		List<DevicePackage> packages = packageService.findByComid(comid);
		mav.addObject("packages", packages);
		return mav;
	}
	
	@RequestMapping(value = "/packmodal/{mode}/")
	public ModelAndView packmodal(HttpServletRequest request, @PathVariable int mode) {
		ModelAndView mav = new ModelAndView(HOST + "snippet/package_modal");
		int comid = Integer.parseInt(request.getParameter("comid"));
		Company company = companyService.findById(comid);
		mav.addObject("company", company);
		if(mode == 2) { // update
			int id = Integer.parseInt(request.getParameter("id"));
			DevicePackage pack = packageService.findById(id);
			mav.addObject("pack", pack);
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