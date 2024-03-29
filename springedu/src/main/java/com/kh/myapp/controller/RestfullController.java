package com.kh.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kh.myapp.member.dto.MemberDTO;

@RestController // 리소스(데이터) 자제를 반환하는데 사용 (json, xml, 문자열)
@RequestMapping("/rest")
public class RestfullController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestfullController.class);

	@RequestMapping(value = "/json/member", method = RequestMethod.GET, produces = "application/json")
	public MemberDTO read() {
		MemberDTO mdto = new MemberDTO();
		mdto.setId("test1@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		return mdto;
	}

	@RequestMapping(value = "/xml/member", method = RequestMethod.GET, produces = "application/xml")
	public MemberDTO read2() {

		return read();
	}

	@RequestMapping(value = "/json/members", method = RequestMethod.GET, produces = "application/json")
	public List<MemberDTO> list() {
		List<MemberDTO> list = new ArrayList<>();
		MemberDTO mdto = null;

		mdto = new MemberDTO();
		mdto.setId("test1@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.add(mdto);

		mdto = new MemberDTO();
		mdto.setId("test2@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.add(mdto);
		mdto = new MemberDTO();
		mdto.setId("test3@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.add(mdto);
		mdto = new MemberDTO();
		mdto.setId("test4@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.add(mdto);

		return list;
	}

	@RequestMapping(value = "/xml/members", method = RequestMethod.GET, produces = "application/xml")
	public List<MemberDTO> list2() {

		return list();
	}

	@RequestMapping(value = "/json/map/members", method = RequestMethod.GET, produces = "application/json")
	public Map<Integer, MemberDTO> list3() {
		Map<Integer, MemberDTO> list = new HashMap<>();
		MemberDTO mdto = null;

		mdto = new MemberDTO();
		mdto.setId("test1@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.put(1, mdto);

		mdto = new MemberDTO();
		mdto.setId("test2@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.put(2, mdto);
		mdto = new MemberDTO();
		mdto.setId("test3@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.put(3, mdto);
		mdto = new MemberDTO();
		mdto.setId("test4@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.put(4, mdto);

		return list;
	}

	@RequestMapping(value = "/xml/map/members", method = RequestMethod.GET, produces = "application/xml")
	public Map<String, MemberDTO> list4() {
		Map<String, MemberDTO> list = new HashMap<>();
		MemberDTO mdto = null;

		mdto = new MemberDTO();
		mdto.setId("test1@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.put("test1", mdto);

		mdto = new MemberDTO();
		mdto.setId("test2@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.put("test2", mdto);
		
		mdto = new MemberDTO();
		mdto.setId("test3@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.put("test3", mdto);
		
		mdto = new MemberDTO();
		mdto.setId("test4@test.com");
		mdto.setNickName("test1");
		mdto.setGender("남");
		mdto.setBirth("2000-01-01");
		mdto.setTel("010-0000-0000");
		mdto.setRegion("울산");
		list.put("test4", mdto);
		
		return list;
	}
	@RequestMapping(value = "array/json/members", method = RequestMethod.GET, produces = "application/json")
	public String[] list5() {
		String[] str = new String[] {"123","234","345","3354"};

		return str;
	}
	@RequestMapping(value = "array/xml/members", method = RequestMethod.GET, produces = "application/xml")
	public String[] list6() {
		String[] str = new String[] {"123","234","345","3354"};

		return str;
	}
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public ResponseEntity<String> write(@RequestBody MemberDTO mdto) {
		ResponseEntity<String> resCode = null;
		logger.info("등록 : " + mdto.toString());
		resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		

		return resCode;
	}
	
	@RequestMapping(value = "/member/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> modify(@RequestBody MemberDTO mdto, @PathVariable("id") String id) {
		ResponseEntity<String> resCode = null;
		logger.info("수정 아이디 : " + id);
		logger.info("수정 내용 : " + mdto.toString());
		resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		

		return resCode;
	}
	
	@RequestMapping(value = "/member/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") String id) {
		ResponseEntity<String> resCode = null;
		logger.info("수정 : " + id);
		resCode = new ResponseEntity<String>("success", HttpStatus.OK);
		

		return resCode;
	}
}
