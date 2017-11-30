package com.hualu.main.java.web;

import java.text.ParseException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.hualu.main.java.entity.Plan;
import com.hualu.main.java.form.PlanForm;
import com.hualu.main.java.service.PlanService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.converter.GenericConverter;

@Controller
@RequestMapping(value = "/plan")
public class PlanWeb implements ServletContextAware {
	
	@Autowired
	private PlanService planService;
	
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate/")
	public String saveOrUpdate(@ModelAttribute("plan") PlanForm planForm) {
		Plan plan = new Plan();
		try {
			plan = (Plan) GenericConverter.convert(planForm, plan);
			planService.saveOrUpdate(plan);
		} catch (ParseException e) {
			return String.valueOf(Status.ERROR);
		}
		return String.valueOf(Status.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping(value = "/remove/{id}/")
	public String remove(@PathVariable int id) {
		Plan plan = planService.findById(id);
		try {
			plan.setStatus(Status.REMIND_INVALID);
			planService.saveOrUpdate(plan);
		} catch (Exception e) {
			return String.valueOf(Status.ERROR);
		}
		return String.valueOf(Status.SUCCESS);
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}