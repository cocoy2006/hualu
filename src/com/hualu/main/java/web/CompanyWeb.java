package com.hualu.main.java.web;

import java.text.ParseException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.hualu.main.java.entity.Company;
import com.hualu.main.java.form.CompanyForm;
import com.hualu.main.java.service.CompanyService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.converter.GenericConverter;

@Controller
@RequestMapping(value = "/company")
public class CompanyWeb implements ServletContextAware {
	
	@Autowired
	private CompanyService companyService;
	
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate/")
	public String saveOrUpdate(@ModelAttribute("company") CompanyForm companyForm) {
		Company company = new Company();
		try {
			company = (Company) GenericConverter.convert(companyForm, company);
			companyService.saveOrUpdate(company);
		} catch (ParseException e) {
			return String.valueOf(Status.ERROR);
		}
		return String.valueOf(Status.SUCCESS);
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}