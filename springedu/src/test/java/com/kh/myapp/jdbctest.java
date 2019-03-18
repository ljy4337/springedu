package com.kh.myapp;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.member.dao.MemberDAOImplJDBC;
import com.kh.myapp.member.dto.MemberDTO;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
class jdbctest {
	
	private static final Logger logger = LoggerFactory.getLogger(jdbctest.class);
	
	@Inject
	MemberDAOImplJDBC memberDAOImplJdbc;
	//회원 등록
//	@Ignore
	@Test 
	@Disabled
	void memberInsert() {
		logger.info("MemberDAOImplJDBC:"+memberDAOImplJdbc);
		logger.info("memberInsert 호출");
		boolean success = false;
		
		for(int i=1;i<=10;i++) {
			MemberDTO memberDTO = new MemberDTO();
			memberDTO.setId("test"+i+"@test.com");
			memberDTO.setPw("1234");
			memberDTO.setTel("010-1234-5678");
			memberDTO.setNickName("테스터_"+i);
			memberDTO.setGender("남");
			memberDTO.setRegion("울산");
			memberDTO.setBirth("2020-01-"+i);
			
			success = memberDAOImplJdbc.insert(memberDTO);
		}
		logger.info("회원등록:"+success);
				
	}
	//회원 수정
	@Test
	@Disabled
	void memberModify() {
		boolean success = false;
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("test1@test.com");
		memberDTO.setPw("1234");
		memberDTO.setTel("016-1234-5678");
		memberDTO.setNickName("테스터수");
		memberDTO.setGender("남");
		memberDTO.setRegion("울산");
		memberDTO.setBirth("2022-01-01");
		
		success = memberDAOImplJdbc.modify(memberDTO);
		
		logger.info("회원수정:"+success);		
		
	}
	//회원 삭제
	@Test
	@Disabled
	void delete() {
		boolean success = false;
		success = memberDAOImplJdbc.delete("test1@test.com","1234");
		
		logger.info("회원삭제:"+success);	
	}
	
	@Test
	@Disabled
	void admindelete() {
		boolean success = false;
		success = memberDAOImplJdbc.adminDelete("test2@test.com");
		
		logger.info("회원삭제:"+success);	
	}
	//회원 조회
	@Test
	@Disabled
	void getMember() {
	 MemberDTO mdto = memberDAOImplJdbc.getMember("test2@test.com");
	 logger.info("회원조회:"+mdto);
		
	}
	
	//회원 목록조회
	@Test
	@Disabled
	void getMemberList() {
	 List<MemberDTO> list = memberDAOImplJdbc.getMemberList();
	 list.stream().forEach(m->{logger.info(m.toString());});
	}
	
	//회원 목록조회
	@Test
	@Disabled
	void getMemberList2() {
	 List<MemberDTO> list = memberDAOImplJdbc.getMemberList();
	 for(MemberDTO mdto:list) {
		 logger.info(mdto.toString());
	 }
	}
	
	//회원 목록조회
	@Test
	@Disabled
	void getMemberList3() {
	 List<MemberDTO> list = memberDAOImplJdbc.getMemberList();
	 for(int i=0; i< list.size(); i++) {
		 logger.info(list.get(i).toString());
	 }
	}
	
	

}
