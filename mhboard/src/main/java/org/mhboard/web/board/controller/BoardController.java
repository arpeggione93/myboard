package org.mhboard.web.board.controller;

import javax.inject.Inject;

import org.mhboard.web.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value= "/board")
public class BoardController {

	@Inject
	private BoardService boardService;
	
	
	@RequestMapping(value = "/readList", method = RequestMethod.GET)
	public String readListGet(Model model)throws Exception{
		
		model.addAttribute("boardList", boardService.readList());
		return "board/main";
	}
	
	
	
	
}
