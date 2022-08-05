package com.cspl.integration.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class AuthInterceptor extends HandlerInterceptorAdapter {
	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Ends :: Request URI::" + request.getRequestURI() + ":: total Time Taken in ms=" + timeTaken);
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		log.info("Starts :: Request URI::" + request.getRequestURI());
		return true;
	}

}
