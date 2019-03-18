package com.kh.myapp.login;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kh.myapp.member.dto.MemberDTO;

@Service
public class LoginSvcImpl implements LoginSvc {

	@Inject
	@Qualifier("loginDAOImplXML")
	LoginDAO loginDAO;

	//회원 유무체크
		@Override
		public boolean isMember(String id, String pw) {

			return loginDAO.isMember(id, pw);
		}

		//로그인
		@Override
		public MemberDTO login(String id, String pw) {
		
			return loginDAO.login(id, pw);
		}

		@Override
		public boolean isidMember(String nickname, String tel) {
			return loginDAO.isidMember(nickname, tel);
		}

		@Override
		public String findID(String nickname, String tel) {
			// TODO Auto-generated method stub
			return loginDAO.findID(nickname, tel);
		}

		@Override
		public String findPW(String id, String nickname, String tel) {
			// TODO Auto-generated method stub
			return loginDAO.findPW(id, nickname, tel);
		}

		@Override
		public boolean ispwMember(String id, String nickname, String tel) {
			// TODO Auto-generated method stub
			return loginDAO.ispwMember(id, nickname, tel);
		}
	
	

}
