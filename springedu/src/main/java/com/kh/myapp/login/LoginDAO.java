package com.kh.myapp.login;

import java.sql.Timestamp;

import com.kh.myapp.member.dto.MemberDTO;

public interface LoginDAO {
	// 등록된 회원인지 판별
	public boolean isMember(String id, String pw);
	
	// 아이디 찾기 시 등록된 회원인지 판별
	public boolean isidMember(String nickname, String tel);
	
	// 비밀번호 찾기 시 등록된 회원인지 판별
	public boolean ispwMember(String id, String nickname, String tel);
	
	// 판별 후 로그인
	public MemberDTO login(String id, String pw);
	
	// 아이디 찾기
	public String findID(String nickname, String tel);
	
	// 비밀번호 찾기
	public String findPW(String id, String nickname, String tel);
	
	//자동로그이상태 유지 적용
	boolean keepLogin(String id, String khssid, Timestamp khssidperiod);
	
	//유효한 khssid가 있는지 체크하여 있다면 회원 id를 반환
	String findKhssid(String khssid);
	

}
