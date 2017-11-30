package com.hualu.main.java.web;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorWeb {
	
	private static final Logger LOG = Logger.getLogger(ErrorWeb.class.getName());
	
	/** 
	@ExceptionHandler(RuntimeException.class)
    public ModelAndView error(RuntimeException e, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("error/runtime");
        LOG.severe(e.getMessage());
        String uri = request.getRequestURI();
        if(uri.indexOf("/api/") > 0) {
        	return null;
        }
        return mav;
    }  
    */
            
    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("error/exception");
        LOG.severe(e.getMessage());
        return mav;
    }  
    
    /** 
    @ExceptionHandler(NullPointerException.class)
    public ModelAndView error(ModelAndView mav) {
        mav.setViewName("error");
　　　　	mav.addObject("param", "NullPointer error");
        return mav;
    }
    */
	
}