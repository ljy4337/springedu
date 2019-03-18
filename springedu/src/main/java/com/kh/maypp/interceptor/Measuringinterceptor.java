package com.kh.maypp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class Measuringinterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(Measuringinterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("beginTime", System.currentTimeMillis());
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long beginTime = (long)request.getAttribute("beginTime");
		long endTime = System.currentTimeMillis();
		
		logger.info("IP : " + request.getRemoteAddr() + "요청경로 : "+ request.getRequestURI() + (endTime-beginTime)/1000+"초");
	}




}
