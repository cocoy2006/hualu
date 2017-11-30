package com.hualu.main.java.web;

import java.text.ParseException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.hualu.main.java.entity.DevicePackage;
import com.hualu.main.java.form.DevicePackageForm;
import com.hualu.main.java.service.PackageService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.converter.GenericConverter;

@Controller
@RequestMapping(value = "/pack")
public class PackageWeb implements ServletContextAware {
	
	@Autowired
	private PackageService packageService;
	
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate/")
	public String saveOrUpdate(@ModelAttribute("pack") DevicePackageForm packForm) {
		DevicePackage pack = new DevicePackage();
		try {
			pack = (DevicePackage) GenericConverter.convert(packForm, pack);
			packageService.saveOrUpdate(pack);
		} catch (ParseException e) {
			return String.valueOf(Status.ERROR);
		}
		return String.valueOf(Status.SUCCESS);
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}