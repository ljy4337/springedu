package com.kh.myapp.controller;



import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.myapp.bbs.dto.BbsDTO;
import com.kh.myapp.bbs.service.BbsSvc;
import com.kh.myapp.member.dto.MemberDTO;
import com.kh.myapp.util.RecordCriteria;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);
	
	@Inject
	BbsSvc bbssvc;
	// 게시글 등록 양식 - wirte_form.jsp
	@RequestMapping("/write")
	public String write(Model model) {
		BbsDTO bbsDTO = new BbsDTO();
//		bbsDTO.setBtitle("초기값");
		model.addAttribute("bbsDTO", new BbsDTO());
		return "/bbs/write_form";
	}
	
	// 게시글 등록처리
	@RequestMapping(value="/writeok", method=RequestMethod.POST)
	public String writeok(@Valid @ModelAttribute("bbsDTO") BbsDTO bbsDTO, BindingResult result, Model model, HttpSession session) {
		int cnt = 0;
		logger.info("writeok() 호출");
		if(result.hasErrors()) {
			logger.info("유효성 오류 발생");
			logger.info(result.toString());
//			model.addAttribute("result", result);
			return "/bbs/write_form";
		}
		MemberDTO mdto = (MemberDTO)session.getAttribute("user");
		
		//세션정보가 없는경우
		if(mdto == null) {
			logger.info("세션정보없음");
			return "/bbs/write_form";
		}
		try {
			bbsDTO.setBid(mdto.getId());
			bbsDTO.setBnickname(mdto.getNickName());
			cnt = bbssvc.write(bbsDTO);
			logger.info("게시글 등록 건수 : " + cnt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/bbs/list";
	}
	// 게시글 수정
	@RequestMapping("/modifyok")
	public String modify(@Valid @ModelAttribute BbsDTO bbsDTO, BindingResult result) {
		
		if(result.hasErrors()) {
			return "/";
		}
		try {
			bbssvc.modify(bbsDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "forward:/bbs/view";
	}
	// 게시글 삭제
	@RequestMapping("/delete")
	public String delete(@RequestParam("bnum") String bnum, @RequestParam("reqPage") String reqPage) {
		logger.info("bnum"+bnum);
	
		try {
			bbssvc.delete(bnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/bbs/list?reqPage="+reqPage;
	}

	// 게시글 보기 - read.jsp
	@RequestMapping("/view")
	public String view(@ModelAttribute("bbsDTO") BbsDTO bbsDTO, Model model, HttpServletRequest request) {
		RecordCriteria rc = null;
		try {
			rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
			request.getParameter("reqPage");
			bbsDTO = bbssvc.view(request.getParameter("bnum"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("bbsDTO", bbsDTO);
		model.addAttribute("rc", rc);
		
		return "/bbs/read";
	}
	
	// 게시글 목록보기 - list.jsp
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, HttpSession session) {
//		model.addAttribute("bdto", new BbsDTO());
		
		try {
			bbssvc.list(request, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/bbs/list";
	}
	
	// 게시글 답글 양식 - reply_form.jsp
	@RequestMapping("/replyForm/{bnum}/{reqPage}")
	public String replyform(@PathVariable("bnum") String bnum, @PathVariable("reqPage") String reqPage, Model model, HttpSession session) {
		BbsDTO bbsDTO = null;
		RecordCriteria rc = null;
		// 로그인 안되있을때 로그인 유도
		MemberDTO mdto = (MemberDTO)session.getAttribute("user");
		if(mdto == null) {
			logger.info("로그인유도");
			return "redirect:/login/loginForm";
		}
		
		try {
			rc = new RecordCriteria(Integer.parseInt(reqPage));
			bbsDTO = bbssvc.view(bnum);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("bbsDTO", bbsDTO);
		model.addAttribute("rc", rc);
		return "/bbs/reply_form";
	}
	
	// 게시글 답글 처리
	@RequestMapping("/replyok")
	public String reply(@Valid @ModelAttribute("bbsDTO") BbsDTO bbsDTO, BindingResult result, HttpSession session, @RequestParam("reqPage") String reqPage) {
		logger.info("reply 호출");
		if(result.hasErrors()) {
			
			return "/bbs/replyForm";
		}
		MemberDTO mdto = (MemberDTO)session.getAttribute("user");
	
		
		try {
			bbsDTO.setBid(mdto.getId());
			bbsDTO.setBnickname(mdto.getNickName());
			bbssvc.reply(bbsDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/bbs/list?reqPage="+reqPage;
	}
	
	@RequestMapping(value="/rereply")
	public String rereply() {
		
		return "/bbs/reReply";
	}
	
}
