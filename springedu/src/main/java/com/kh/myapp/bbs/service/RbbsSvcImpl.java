package com.kh.myapp.bbs.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.kh.myapp.bbs.dao.RbbsDAO;
import com.kh.myapp.bbs.dto.RbbsDTO;

@Service
public class RbbsSvcImpl implements RbbsSvc{

	private static final Logger logger = LoggerFactory.getLogger(RbbsSvcImpl.class);
	
	@Inject
	@Qualifier("rbbsDAOImplXML")
	RbbsDAO rbbsdao;

	@Override
	public int write(RbbsDTO rbbsdto) {
		int cnt = 0;
		cnt = rbbsdao.write(rbbsdto);
		return cnt;
	}

	@Override
	public List<RbbsDTO> list(String bnum) {
		List<RbbsDTO> list = null;
		list = rbbsdao.list(bnum);
		return list;
	}

	@Override
	public List<RbbsDTO> list(String bnum, int startRec, int endRec) throws Exception {
		List<RbbsDTO> list = null;
		list = rbbsdao.list(bnum, startRec, endRec);
		return list;
	}


	@Override
	public int modify(RbbsDTO rbbsdto) {
		int cnt = 0;
		cnt = rbbsdao.modify(rbbsdto);
		return cnt;
	}

	@Override
	public int delete(String rnum) throws Exception {
		int cnt = 0;
		cnt = rbbsdao.delete(rnum);
		return cnt;
	}

	@Override
	public int goodOrbad(String rnum, String goodOrbad) {
		int cnt = 0;
		cnt = rbbsdao.goodOrbad(rnum, goodOrbad);
		return cnt;
	}

	@Override
	public int reply(RbbsDTO rbbsdto) throws Exception {
		int cnt = 0;
		cnt = rbbsdao.reply(rbbsdto);
		return cnt;
	}

	@Override
	public int totalRec(String rnum) {
		int cnt = 0;
		cnt = rbbsdao.totalRec(rnum);
		return cnt;
	}

	@Override
	public int replyTotalRec(String bnum) {
		int cnt = 0;
		cnt = rbbsdao.replyTotalRec(bnum);
		return cnt;
	}

	@Override
	public int updateStep(int rgroup, int rstep) {
		int cnt = 0;
		cnt = rbbsdao.updateStep(rgroup, rstep);
		return cnt;
	}

	



}
