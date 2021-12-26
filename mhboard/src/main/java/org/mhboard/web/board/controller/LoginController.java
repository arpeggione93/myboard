package org.mhboard.web.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String updateAdminGET(MemberVO memberVO, HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		
		System.out.println("회원정지 요청이 들어옴");
		String url = "";
		
		System.out.println("이게 로그인 회원 정보 : " + session.getAttribute("admin"));
		
		if(session.getAttribute("admin").equals("jmh8649")) {
			
			boardService.updateAdmin("jmh8649");
			
			url = "redirect:/";
			
			
		}else {
			
			
			url =  "redirect:/";
			
		}
		
		
		return url;
		
		
	}
	
	
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loginGET(HttpSession session) throws Exception {
		
		String url = "";
		
		session.setAttribute("chk", boardService.registCancel());
		
		System.out.println("회원가입 기능 막혔나? : " +session.getAttribute("chk"));
		
		if(session.getAttribute("loginMember") == null) {
			
			
			url = "board/login";
			
		}else {
			

			url = "board/readList";
			
		}
		
		return url;
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
				rttr.addFlashAttribute("msg1", "로그인 되었습니다.");
				session.setAttribute("loginMember", login);
				session.setAttribute("admin", login.getMemberId());
				url =  "redirect:/board/readList";
				
			}else {
				
				rttr.addFlashAttribute("msg1", "암호가 틀렸습니다..");
				url =  "redirect:/";
				
			}
			
				
			}else {
				
				rttr.addFlashAttribute("msg1", "가입되지 않은 회원입니다..");
				url = "redirect:/";
				
			}
			
			System.out.println("22로그인한 정보입니다." + session.getAttribute("loginMember"));

			return url;
		
			
		}
		
		@RequestMapping(value = "/logout", method = RequestMethod.GET)
		public String logoutGET(HttpSession session) {
			
			
			 session.invalidate();
	    
			
			return "redirect:/";
			
		}
		
	

	
	
	
}
