//package com.kh.myapp.board;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.kh.myapp.bbs.dto.BbsDTO;
//import com.kh.myapp.bbs.service.BbsSvc;
//
//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
//class BoardTest {
//
//	private static final Logger logger = LoggerFactory.getLogger(BoardTest.class);
//	@Inject
//	BbsSvc bbssvc;
//	
//	
//	BbsDTO bbsdto;
//	int cnt;
//	List<BbsDTO> list;
//	
//	@BeforeEach
//	void beforeEach() {
//		bbsdto = new BbsDTO();
//		cnt = 0;
//	}
//	@Test
//	@Disabled
//	void write() {
//		logger.info("write test");
//		BbsDTO bbsdto = new BbsDTO();
//		
//		bbsdto.setBtitle("반갑습니다1");
//		bbsdto.setBid("test3@test.com");
//		bbsdto.setBnickname("test3");
//		bbsdto.setBcontent("첫 게시글글글글");
//		
//		try {
//			cnt = bbssvc.write(bbsdto);
//			logger.info("레코드 생성결과 : " + cnt);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	@Disabled
//	void list() {
//		logger.info("list Test");
//		
//		try {
//			int a = 1;
//			int b = 10;
//			list = bbssvc.list(1,10);
//			logger.info("목록건수 : " + list.size());
//			for(BbsDTO bbsdto : list) {
//				
//				logger.info("목록 : " + bbsdto.toString());
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	@Test
//	@Disabled
//	void view() {
//		logger.info("view Test");
//		try {
//			bbsdto = bbssvc.view("11222");
//			logger.info("글읽기 : " + bbsdto.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	@Disabled
//	void modify() {
//		int bnum = 11221;
//		logger.info("modify Test");
//		bbsdto.setBtitle("수정수정");
//		bbsdto.setBcontent("수정수");
//		bbsdto.setBnum(bnum);
//		try {
//			cnt = bbssvc.modify(bbsdto);
//			logger.info("글수정건수 : " + cnt);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	@Disabled
//	void delete() {
//		logger.info("delete test");
//		String bnum = "11221";
//		try {
//			cnt = bbssvc.delete(bnum);
//			logger.info("삭제건수 : " + cnt);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	@Disabled
//	void replyview() {
//		String bnum = "10063";
//		logger.info("replyview test");
//		try {
//			bbsdto = bbssvc.replyView(bnum);
//			logger.info("원글 번호 : " + bbsdto.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	@Disabled
//	void reply() {
//		int bnum = 11247;
//		logger.info("reply test");
//	 bbsdto.setBtitle("reply");
//	 bbsdto.setBid("test3-1@test.com");
//	 bbsdto.setBnickname("test3-1");
//	 bbsdto.setBcontent("reply");
//	 bbsdto.setBgroup(bnum);
//	 
//	 try {
//		cnt = bbssvc.reply(bbsdto);
//		logger.info("reply : " + cnt);
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	 
//	}
//	
//	@Test
//	@Disabled
//	void totalRec() {
//		try {
//			cnt = bbssvc.totalRec();
//			logger.info("게시글 총계 : " +cnt);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//	@Test
//	
//	void search() {
//		logger.info("search test");
//		int startRecord = 1;
//		int endRecord = 20;
//		String searchType = "N";
//		String keyword = "9500";
//		
//		try {
//			list = bbssvc.list( startRecord, endRecord, searchType, keyword);
//			for(BbsDTO bbsdto : list) {
//				logger.info("검색목록 : " + bbsdto.toString());
////				logger.info("검색목록 : " + list.toString());
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
