package com.kh.myapp.login;

import org.springframework.stereotype.Service;

import com.kh.myapp.member.dto.MemberDTO;

@Service
public interface LoginSvc {

	public boolean isMember(String id, String pw);

	public boolean isidMember(String nickname, String tel);
	
	public boolean ispwMember(String id, String nickname, String tel);

	public MemberDTO login(String id, String pw);

	// 아이디 찾기
	public String findID(String nickname, String tel);

	// 비밀번호 찾기
	public String findPW(String id, String nickname, String tel);
}
