package com.kh.myapp.member.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kh.myapp.controller.MemberController;
import com.kh.myapp.member.dao.MemberDAO;
import com.kh.myapp.member.dto.MemberDTO;

@Service
public class MemberSvcImpl implements MemberSvc {
	private static final Logger logger = LoggerFactory.getLogger(MemberSvcImpl.class);
	@Inject // 동일타입의 인스턴스를 주입받는다.
	// @Qualifier 동일타입이 있을경우 매값에 구분자로 구현클래스명 또는
	// ex) @Repository("name")인스턴스 이름을 지정할수있다.
	@Qualifier("memberDAOImplXML")
	MemberDAO mdao;

	// 회원 등록
	@Override
	public boolean insert(MemberDTO memberDTO) {
		boolean success = false;
		success = mdao.insert(memberDTO);
		return success;
	}

	// 회원 수정
	@Override
	public boolean modify(MemberDTO memberDTO) {
		boolean success = false;
		success = mdao.modify(memberDTO);
		return success;

	}

	// 회원 삭제(회원용)
	@Override
	public boolean delete(String id, String pw) {
		boolean success = false;
		success = mdao.delete(id, pw);
		return success;

	}

	// 회원 삭제(관리자용)
	@Override
	public boolean adminDelete(String id) {
		boolean success = false;
		success = mdao.adminDelete(id);
		return success;
	}

	// 회원 조회
	@Override
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO = null;

		memberDTO = mdao.getMember(id);
		return memberDTO;
	}

	// 회원 목록 조회
	@Override
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> list = null;
		list = mdao.getMemberList();
		return list;
	}

	@Override
	public String idcheck(String id) {
		logger.info("service" + id);
		String idchk = null;

		idchk = mdao.idcheck(id);
		logger.info("service" + idchk);
		return idchk;
	}

	@Override
	public MemberDTO getMemberpw(String id) {
		MemberDTO memberDTO = null;

		memberDTO = mdao.getMemberpw(id);
		return memberDTO;
	}

	@Override
	public boolean modifypw(MemberDTO memberDTO) {
		boolean success = false;
		success = mdao.modifypw(memberDTO);
		return success;
	}

}
