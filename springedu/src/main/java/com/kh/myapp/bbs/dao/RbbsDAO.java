package com.kh.myapp.bbs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.kh.myapp.bbs.dto.RbbsDTO;


public interface RbbsDAO {

		// 댓글 등록
		int write(RbbsDTO rbbsdto);
		
		// 댓글 목록
		List<RbbsDTO> list(String bnum);
		List<RbbsDTO> list(String bnum, int startRec, int endRec) throws Exception;
		
		// 댓글 수정
		int modify(RbbsDTO rbbsdto);

		
		// 댓글 삭제
		int delete(String rnum) throws Exception;
		
		// 댓글 좋아요 싫어요
		int goodOrbad(String rnum, String goodOrbad);
		
		// 대댓글 등록
		int reply(RbbsDTO rbbsdto) throws Exception;
		
		// 댓글 총계
		int totalRec(String bnum);
		
		// 대댓글 총계
		int replyTotalRec(String bnum);
		
		int updateStep(int rgroup, int rstep);
		
		
}
