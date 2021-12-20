package org.mhboard.web.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.mhboard.web.board.service.BoardService;
import org.mhboard.web.board.vo.BoardVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value= "/board")
public class BoardController {

	@Inject
	private BoardService boardService;
	
	//목록 불러오기
	@RequestMapping(value = "/readList", method = RequestMethod.GET)
	public String readListGet(Model model)throws Exception{
		
		model.addAttribute("boardList", boardService.readList());
		return "board/main";
	}
	
	//글작성 폼
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeGET() {
		
		return "board/writeForm";
		
	}
	
	//글작성 요청
	//RedirectAttributes의 경우, 뒤로가기 버튼을 통해 같은 글을 도배하는 행위를 방지하기 위함
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePOST(BoardVO boardVO, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		boardService.write(boardVO);
	
		System.out.println("글 작성 완료 :) ");
		
		return "redirect:/board/readList";
	}
	
	
	
	
}
