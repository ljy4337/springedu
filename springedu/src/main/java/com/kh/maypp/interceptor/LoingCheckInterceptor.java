package com.kh.maypp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoingCheckInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(LoingCheckInterceptor.class);
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean wasLogin = false;
		wasLogin = request.getSession().getAttribute("user") != null;
		
		logger.info("로그인인터셉터");
		if(!wasLogin) {
			response.sendRedirect("http://127.0.0.1:8080/login/loginForm");
		}
		return wasLogin;
	}


}
