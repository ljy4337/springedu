package com.kh.myapp.login;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.myapp.member.dto.MemberDTO;

@Repository(value="loginDAOImplXML")
public class LoginDAOImplXML implements LoginDAO{

	@Inject
	SqlSession sqlSession;
	@Override
	public boolean isMember(String id, String pw) {
		boolean result = false;
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("pw", pw);
		int cnt = sqlSession.selectOne("ismember", map); 
		if(cnt > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public MemberDTO login(String id, String pw) {
		MemberDTO memberDTO = new MemberDTO();
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("pw", pw);
		memberDTO = (MemberDTO) sqlSession.selectOne("mappers.login.login", map); 
		return memberDTO;
	}

	@Override
	public String findID(String nickname, String tel) {
		String id =  null;
		Map<String,String> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("tel", tel);
		id = sqlSession.selectOne("mappers.login.findID", map);
		
		return id;
	}

	@Override
	public String findPW(String id, String nickname, String tel) {
		String pw =  null;
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("nickname", nickname);
		map.put("tel", tel);
		pw = sqlSession.selectOne("mappers.login.findPW", map);
		
		return pw;
	}

	@Override
	public boolean isidMember(String nickname, String tel) {
		boolean result = false;
		Map<String,String> map = new HashMap<>();
		map.put("nickname",nickname);
		map.put("tel", tel);
		int cnt = sqlSession.selectOne("isidmember", map); 
		if(cnt > 0) {
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean ispwMember(String id, String nickname, String tel) {
		boolean result = false;
		Map<String,String> map = new HashMap<>();
		map.put("id",id);
		map.put("nickname",nickname);
		map.put("tel", tel);
		int cnt = sqlSession.selectOne("ispwmember", map); 
		if(cnt > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean keepLogin(String id, String khssid, Timestamp khssidperiod) {
		boolean result = false;
		Map<String,Object> map = new HashMap<>();
		map.put("id",id);
		map.put("khssid",khssid);
		map.put("khssidperiod", khssidperiod);
		int cnt = sqlSession.update("keepLogin", map); 
		if(cnt > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public String findKhssid(String khssid) {
		String id =  null;
		
		id = sqlSession.selectOne("mappers.login.findID", khssid);
		
		return id;
	}

//	@Override
//	public boolean keepLoginNot(String id, String khssid, Timestamp khssidperiod) {
//		boolean result = false;
//		Map<String,Object> map = new HashMap<>();
//		map.put("id",id);
//		map.put("khssid",khssid);
//		map.put("khssidperiod", khssidperiod);
//		int cnt = sqlSession.update("keepLoginNot", map); 
//		if(cnt > 0) {
//			result = true;
//		}
//		return result;
//	}

}
