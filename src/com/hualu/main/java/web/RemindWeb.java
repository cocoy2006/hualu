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

import com.hualu.main.java.entity.Remind;
import com.hualu.main.java.form.RemindForm;
import com.hualu.main.java.service.RemindService;
import com.hualu.main.java.util.Hualu;
import com.hualu.main.java.util.Status;
import com.hualu.main.java.util.converter.GenericConverter;

@Controller
@RequestMapping(value = "/remind")
public class RemindWeb implements ServletContextAware {
	
	@Autowired
	private RemindService remindService;
	
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate/")
	public String saveOrUpdate(@ModelAttribute("remind") RemindForm remindForm) {
		Remind remind = new Remind();
		try {
			remind = (Remind) GenericConverter.convert(remindForm, remind);
			remindService.saveOrUpdate(remind);
		} catch (ParseException e) {
			return String.valueOf(Status.ERROR);
		}
		return String.valueOf(Status.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping(value = "/remove/{id}/")
	public String remove(@PathVariable int id) {
		Remind remind = remindService.findById(id);
		try {
			remind.setEndtime(Hualu.getStandardTimestamp());
			remind.setStatus(Status.REMIND_INVALID);
			remindService.saveOrUpdate(remind);
		} catch (Exception e) {
			return String.valueOf(Status.ERROR);
		}
		return String.valueOf(Status.SUCCESS);
	}
	
	public void setServletContext(ServletContext sc) {
		Hualu.setServletContext(sc);
	}
	
}