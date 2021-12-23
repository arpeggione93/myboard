package org.mhboard.web.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.mhboard.web.board.service.BoardService;
import org.mhboard.web.board.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */



@Controller
public class LoginController {
	
	
	@Inject
	BCryptPasswordEncoder pwEncoder;
	
	@Inject
	private BoardService boardService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginGET() {
		
		return "board/login";
	}
	
	//료그인 기능 구현중(임시)
		@RequestMapping(value = "/", method = RequestMethod.POST)
		public String loginPOST(MemberVO memberVO, RedirectAttributes rttr, HttpSession session) throws Exception {
			
			System.out.println("지금 로그인하려고 입력한 정보들 : " + memberVO);
			
			int idChk = boardService.idChk(memberVO); //아이디가 존재하는지 부터 판별하기
			System.out.println("아이디 있는지 확인하는 값 : " + idChk);
			String url = "";
			
			//일치하는 아이디가 있을경우
			if(idChk != 0) {
				
				
			MemberVO login = boardService.selectMember(memberVO.getMemberId()); //등록된 아이디
			boolean pwchk =	pwEncoder.matches(memberVO.getMemberPw(), login.getMemberPw());
				
			//암호까지 일치할경우
			if(pwchk == true) {
				
				//세션에 로그인값을 저장
				session.setAttribute("loginMember", login);
				url =  "redirect:/board/readList";
				
			}else {
				
				System.out.println("암호 틀렸음 ");
				url =  "redirect:/";
				
			}
			
				
			}else {
				
				url = "redirect:/";
				
			}
			
			System.out.println("22로그인한 정보입니다." + session.getAttribute("loginMember"));
			System.out.println("이동경로이다 : " + url);
			return url;
		
			
		}
		
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logoutGET(HttpSession session) {
			
			
			 session.invalidate();
	    
			
			return "redirect:/";
			
		}
		
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	
	
}
