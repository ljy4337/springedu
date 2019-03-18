package com.kh.myapp;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.login.LoginDAOImplXML;
import com.kh.myapp.member.dao.MemberDAOImplXML;
import com.kh.myapp.member.dto.MemberDTO;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
class logintestxml {

	private static Logger logger = LoggerFactory.getLogger(logintestxml.class);

	@Inject
	LoginDAOImplXML xml;

	@Test
	void test() {

		MemberDTO mdto = new MemberDTO();
		mdto = xml.login("test3@test.com", "1234");
		logger.info("login : " + mdto);
		
	}

	


}
