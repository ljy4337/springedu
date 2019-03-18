package com.kh.myapp.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.myapp.login.LoginCmd;
import com.kh.myapp.login.LoginSvc;
import com.kh.myapp.member.dto.MemberDTO;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Inject
	LoginSvc loginSvc;

	@RequestMapping("/loginForm")
	public void loginForm(Model model) {
		logger.info("loginForm() 호출");
		model.addAttribute("login", new LoginCmd());
	}

	@RequestMapping("/login")
	public String login() {

		return "/login/login";
	}

	// 로그인
	@RequestMapping(value = "/loginOk") // ,method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute("login") LoginCmd login, BindingResult result, HttpSession session, HttpServletResponse response) {

		logger.info("String login 호출됨!");

		MemberDTO mdto = null;

		if (result.hasErrors()) {
			logger.info("login 실패ㅋ");

			logger.info(result.toString());
			return "/login/loginForm";
		}
		

		// 1)회원 유무체크
		if (loginSvc.isMember(login.getId(), login.getPw())) {
			// 2)로그인 처리
			mdto = loginSvc.login(login.getId(), login.getPw());
//			if(login.getRememberme() != null) {
//				logger.info("rememberme");
//				// 쿠키생성
//				Cookie cookie = new Cookie("khssid", session.getId());
//
//				// 컨텍스트경로 이하 요청시 쿠키정보를 함께 보내도록 지정
//				cookie.setPath("/");
//				// 쿠키 유효기간을 7일로 설정
//				int amount = 60 * 60 * 24 * 7;
//				cookie.setMaxAge(amount);
//				// 생성된 쿠키를 클라이언트에 전송
//				response.addCookie(cookie);
//
//				// 현재시간 + 7일 계산하여 Timestamp 타입으로 변환
//				LocalDateTime localdateTime = LocalDateTime.now().plusSeconds(amount);
//				Timestamp datetime = Timestamp.valueOf(localdateTime);
//
//				// 현재 세션id와 유효시간을 사용자 테이블에 저장
//				loginSvc.keepLogin(mdto.getId(), session.getId(), datetime);
//			}
			session.setAttribute("user", mdto);
			logger.info("로그인 처리됨:" + login.getId());
		} else {
			logger.info("login 실패");
			return "forward:/login/loginForm";
		}
		return "redirect:/";
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/login/loginForm";
	}
	
	// 아이디 찾기
	@RequestMapping("/findID")
	public void findid(Model model) {
		logger.info("findid() 호출");
		model.addAttribute("login", new LoginCmd());
	}
	//Model model -> response
	//@ModelAttribute -> request

	// 아이디 찾기 실행
	@RequestMapping(value = "/findidok", method=RequestMethod.POST) // ,method=RequestMethod.POST)
	public String findidok(@Valid @ModelAttribute("login") LoginCmd login,BindingResult result, Model model) {

		logger.info("findid 호출됨!");
		
		String id = null;
		LoginCmd lcmd = null;
		if(result.hasErrors()) {
			
			logger.info(result.toString());
			logger.info("아이디찾기 오류발생!!");
			return "/login/findID";
		}

		logger.info(login.getNickname() + login.getTel());
		// 1)회원 유무체크
		if (loginSvc.isidMember(login.getNickname(), login.getTel())) {
			// 2)로그인 처리
			id = loginSvc.findID(login.getNickname(), login.getTel());
			
		} else {
			return "/login/findID";
		}
		logger.info("아이디찾기 처리됨:" + id);
		model.addAttribute("id", id);
		return "/login/findidlog";
	}
	
	// 아이디보여주기
	@RequestMapping(value = "/findidlog") // ,method=RequestMethod.POST)
	public String findidview(@ModelAttribute("login") LoginCmd login) {

		logger.info("findidlog 호출됨!");

	
		return "/login/findidlog";
	}
	
	// 비밀번 찾기
		@RequestMapping("/findPW")
		public void findpw(Model model) {
			logger.info("findpw() 호출");
			model.addAttribute("login", new LoginCmd());
		}

		// 비밀번 찾기 실행
		@RequestMapping(value = "/findpwok", method=RequestMethod.POST) // ,method=RequestMethod.POST)
		public String findpwok(@Valid @ModelAttribute("login") LoginCmd login,BindingResult result, Model model) {

			logger.info("findpw 호출됨!");
			
			String pw = null;
			if(result.hasErrors()) {
				
				logger.info(result.toString());
				logger.info("pw찾기 오류발생!!");
				return "/login/findPW";
			}

			logger.info(login.getId() + login.getNickname() + login.getTel());
			// 1)회원 유무체크
			if (loginSvc.ispwMember(login.getId(), login.getNickname(), login.getTel())) {
				// 2)로그인 처리
				pw = loginSvc.findPW(login.getId(), login.getNickname(), login.getTel());
				
			} else {
				return "/login/findPw";
			}
			logger.info("pw 처리됨:" + pw);
			model.addAttribute("pw", pw);
			return "/login/findpwlog";
		}
		
		// 비밀번보여주기
		@RequestMapping(value = "/findpwlog") //,method=RequestMethod.POST)
		public String findpwview(@ModelAttribute("login") LoginCmd login) {

			logger.info("findpwlog 호출됨!");

		
			return "/login/findpwlog";
		}

}
