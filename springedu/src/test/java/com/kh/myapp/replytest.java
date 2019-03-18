package com.kh.myapp;

import javax.inject.Inject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.myapp.bbs.dao.RbbsDAO;
import com.kh.myapp.bbs.dto.RbbsDTO;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
class replytest {

	private static Logger logger = LoggerFactory.getLogger(replytest.class);

	@Inject
	@Qualifier("rbbsDAOImplXML")
	RbbsDAO rbbsDAO;
	
	
	@Test
	@Disabled
	void write() {
		RbbsDTO rbbsdto = new RbbsDTO();
		int cnt = 0;
		int bnum = 11330;
		rbbsdto.setBnum(bnum);
		rbbsdto.setRid("test4@test.com");
		rbbsdto.setRnickname("테스터_14");
		rbbsdto.setRcontent("댓글");
		cnt = rbbsDAO.write(rbbsdto);
		if(cnt > 0) {
			logger.info("등록건수 : " + cnt);

		}
	}
	@Test
	@Disabled
	void modify() {
		RbbsDTO rbbsdto = new RbbsDTO();
		int cnt = 0;
		int rnum = 11312;
		rbbsdto.setRnum(rnum);
		rbbsdto.setRcontent("수정된댓글1");
		cnt = rbbsDAO.modify(rbbsdto);
		if(cnt > 0) {
			logger.info("수정건수 : " + cnt);
			
		}
	}
	@Test
	
	void replyTotalRec() {
		int cnt = 0;

		cnt = rbbsDAO.replyTotalRec("11330");
		if(cnt > 0) {
		}
		logger.info("총계 : " + cnt);
	}
	
	@Test
	@Disabled
	void del() {
		RbbsDTO rbbsdto = new RbbsDTO();
		int cnt = 0;

		try {
			cnt = rbbsDAO.delete("11322");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cnt > 0) {
			logger.info("삭제건수 : " + cnt);
			
		}
	}

	
	@Test
	void isReply() {
		int cnt = 0;

		cnt = rbbsDAO.replyTotalRec("11330");
		if(cnt > 0) {
		}
		logger.info("총계 : " + cnt);
	}
//	
////	@Test
////	@Disabled
////	void view() throws Exception {
////		RbbsDTO rbbsdto = null;
////		int cnt = 0;
////		int rnum = 11246;
////		rbbsdto = rbbsdao.replyView(rnum);
////		
////		logger.info("view : " + rbbsdto);
////			
////		
////	}
////	
//	@Test
//	@Disabled
//	void rereply() throws Exception {
//		RbbsDTO rbbsdto = new RbbsDTO();
//		int cnt = 0;
//		int bnum = 11329;
//		int rnum = 11260;
//		
//		rbbsdto.setRrnum(rnum);
//		rbbsdto.setBnum(bnum);
//		rbbsdto.setRid("test3@test.com");
//		rbbsdto.setRnickname("테스터_3");
//		rbbsdto.setRcontent("대댓글");
//	
//		
//		cnt = rbbsdao.reply(rbbsdto);
//		
//		
//		logger.info("대댓글 :" + cnt);
//			
//		
//	}
//	@Test
//	@Disabled
//	void list12() {
//		List<RbbsDTO> list = null;
//		list = rbbsdao.list("11329");
//		logger.info(list.toString());
//	}
//	
//	//목록가져오기
//	@Test
//	@Disabled
//	void list() {
//
//		List<RbbsDTO> list = null;
//		try {
//			list = rbbsdao.list("11329");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		logger.info(list.toString());
//	}
//	
//	//목록가져오기
//	@Test
//	@Disabled
//	void list13() {
//
//		List<RbbsDTO> list = null;
//		try {
//			list = rbbsdao.list("11222",2,10);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		logger.info(list.toString());
//	}
	

}
