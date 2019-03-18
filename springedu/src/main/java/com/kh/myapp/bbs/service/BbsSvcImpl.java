package com.kh.myapp.bbs.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kh.myapp.bbs.dao.BbsDAO;
import com.kh.myapp.bbs.dto.BbsDTO;
import com.kh.myapp.util.FindCriteria;
import com.kh.myapp.util.PageCriteria;
import com.kh.myapp.util.RecordCriteria;



@Service
public class BbsSvcImpl implements BbsSvc {

	private static final Logger logger = LoggerFactory.getLogger(BbsSvcImpl.class);
	
	@Inject
	@Qualifier("bbsDAOImplXML")
	BbsDAO bbsdao;
	@Override
	public int write(BbsDTO bbsDTO) throws Exception {
		int cnt = 0;
		cnt = bbsdao.write(bbsDTO);
		return cnt;
	}

	@Override
	public List<BbsDTO> list() throws Exception {
		List<BbsDTO> list= null;
		list = bbsdao.list();
		return list;
	}

	@Override
	public List<BbsDTO> list(int starRec, int endRec) throws Exception {
		List<BbsDTO> list= null;
		list = bbsdao.list(starRec, endRec);
		return list;
	}

	@Override
	public BbsDTO view(String bnum) throws Exception {
		BbsDTO bbsdto = null;
		bbsdto = bbsdao.view(bnum);
		return bbsdto;
	}

	@Override
	public int modify(BbsDTO boardDTO) throws Exception {
		int cnt = 0;
		cnt = bbsdao.modify(boardDTO);
		return cnt;
	}

	@Override
	public int delete(String bnum) throws Exception {
		int cnt = 0;
		cnt = bbsdao.delete(bnum);
		return cnt;
	}

	@Override
	public BbsDTO replyView(String bnum) throws Exception {
		BbsDTO bbsdto = null;
		bbsdto = bbsdao.replyView(bnum);
		return bbsdto;
	}

	@Override
	public int reply(BbsDTO bbsDTO) throws Exception {
		int cnt = 0;
		cnt = bbsdao.reply(bbsDTO);
		return cnt;
	}

	@Override
	public int totalRec() throws Exception {
		int cnt = 0;
		cnt = bbsdao.totalRec();
		return cnt;
	}

	@Override
	public List<BbsDTO> list(int startRecord, int endRecord, String searchType, String keyword) throws Exception {
		List<BbsDTO> list = null;
		list = bbsdao.list(startRecord, endRecord, searchType, keyword);
		return list;
	}
	@Override
	public void list(HttpServletRequest request, Model model) throws Exception{
		int NUM_PER_PAGE = 10;	//한 페이지에 보여줄 레코드 수
		int PAGE_NUM_PAGE = 10;	//한 페이지에 보여줄 페이지 수
		
		int reqPage = 0;		// 요청 페이지
		int totalRec = 0;		// 전체 레코드 수
		
		String searchType = null;	// 검색어 타입
		String keyword = null;		// 검색어

		PageCriteria pc = null;
		RecordCriteria rc = null;	// 검색조건이 없는 경우의 레코드 시작, 종료페이지 연산
		FindCriteria fc = null;		// 검색조건이 있는 경우의 레코드 시작, 종료페이지 연산
		
		List<BbsDTO> alist = null;
		
		// 요청 페이지가 없는 경우 1페이지로 이동
		if(request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		}else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}
		
		
		logger.info("rereqpage : "+reqPage);
		
		// 검색 매개값 체크(searchType, keyword)
		searchType = request.getParameter("searchType");
		keyword = request.getParameter("keyword");
		logger.info(searchType);
		logger.info(keyword);
		
		if(keyword == null || keyword.trim().isEmpty()) {
			
			rc = new RecordCriteria(reqPage, NUM_PER_PAGE);
			totalRec = bbsdao.totalRec();
		
			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PAGE);
			logger.info("pc = "+pc);
			
			alist = (ArrayList<BbsDTO>) bbsdao.list(rc.getStartRecord(), rc.getEndRecord());
			
			model.addAttribute("list", alist);
			model.addAttribute("pc", pc);
		}
		
		else {
			logger.info("검색조건이 있는 경우");
			// 검색조건이 있는 경우
			
			rc = new FindCriteria(reqPage, NUM_PER_PAGE, searchType, keyword);
			
			// 검색목록 총 레코드 수 변환
			totalRec = bbsdao.SearchTotalRec(((FindCriteria)rc).getSearchType(), ((FindCriteria)rc).getKeyword());
		
			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PAGE);
			logger.info("pc = "+pc);
			
			alist = 
					bbsdao.list(
					rc.getStartRecord(), rc.getEndRecord(),
					((FindCriteria)rc).getSearchType(), ((FindCriteria)rc).getKeyword()
					);
			logger.info("검색레코드 수 : " +totalRec);
			logger.info("검색목록 : ");
			logger.info(alist+"\n");
			model.addAttribute("list", alist);
			model.addAttribute("pc", pc);
		}
		
	}

	@Override
	public int SearchTotalRec(String searchType, String keyword) throws Exception {
		int cnt = 0;
		cnt = bbsdao.SearchTotalRec(searchType, keyword);
		return cnt;
	}

	@Override
	public void execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}



}
