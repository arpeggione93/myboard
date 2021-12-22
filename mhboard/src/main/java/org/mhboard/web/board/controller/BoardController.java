package org.mhboard.web.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mhboard.web.board.service.BoardService;
import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.board.vo.CommentVO;
import org.mhboard.web.paging.Paging;
import org.mhboard.web.paging.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value= "/board")
public class BoardController {

	@Inject
	private BoardService boardService;
	
	//목록 불러오기 + 페이징처리 진행중
	@RequestMapping(value = "/readList", method = RequestMethod.GET)
	public String readListGet(Model model, @RequestParam(required = false, defaultValue = "1") int page

			, @RequestParam(required = false, defaultValue = "1") int range , @RequestParam(required = false, defaultValue = "title") String searchType

			, @RequestParam(required = false) String keyword)throws Exception{
		
		
		
		Search search = new Search();

		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		
		
		//총 게시물 갯수
		int listCnt = boardService.readListCnt(search);
		
		search.pageInfo(page, range, listCnt);
		
		/*//페이징처리 객체
		Paging paging = new Paging();
		paging.pageInfo(page, range, listCnt);//현재 페이지, 현재 페이지의 갯수, 총 게시물의 갯수 입력받기
		*/
		
		
		model.addAttribute("paging", search);
		
		System.out.println("page, range, listCnt, 마지막 페이지, 그리고 총 게시물 수 " +"  " + search.getPage() +"  " + search.getRange() +"  " + search.getListCnt() +"  " + search.getEndPage() +"  " + search.getListCnt());
		
		model.addAttribute("boardList", boardService.readList(search));
		
		System.out.println("boardList값은 바로 이것이다. : "+ boardService.readList(search));
		
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
	public String writePOST(BoardVO boardVO, RedirectAttributes rttr, MultipartHttpServletRequest mpReq) throws Exception {
		
		//파일 업로드 추가
		boardService.write(boardVO, mpReq);
	
		System.out.println("글 작성 완료 :) ");
		
		return "redirect:/board/readList";
	}
	
	//게시글 상세내용 불러오기
	@RequestMapping(value = "/readContent", method = RequestMethod.GET)
	public String readContentGET(int bid, HttpSession session) throws Exception {
		
		 session.setAttribute("Content", boardService.readContent(bid));
		
		 //상세 내용 페이지로 이동할 때, 댓글을 입력할 수 있는 폼이 생성되어야 하기 때문
		 List<CommentVO> commentVO = boardService.readComment(bid);
		 session.setAttribute("commentVO", commentVO);
		 
		 //파일 조회 구현중
		 List<Map<String, Object>> fileList = boardService.selectFile(bid);
		session.setAttribute("file", fileList);
		
		 
		 System.out.println("값이 왜 안나오냐? " +session.getAttribute("file") + session.getAttribute("Content") + session.getAttribute("commentVO"));
		 				return "board/Content";

	}
	
	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse res) throws Exception{
		Map<String, Object> resultMap = boardService.selectFileInfo(map);
		String str_file_name = (String) resultMap.get("str_file_name");
		String org_file_name = (String) resultMap.get("org_file_name");
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\mp\\file\\"+str_file_name));
		
		res.setContentType("application/octet-stream");
		res.setContentLength(fileByte.length);
		res.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(org_file_name, "UTF-8")+"\";");
		res.getOutputStream().write(fileByte);
		res.getOutputStream().flush();
		res.getOutputStream().close();
		
	}
	
	
	
	//게시글 내용 수정폼 불러오기
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGET(int bid, HttpSession session) throws Exception{
		
		session.setAttribute("Content", boardService.readContent(bid));
		session.setAttribute("boardVO", new BoardVO());
		return "board/editForm";
	}
	
	//게시글 내용 수정
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String updatePOST(BoardVO boardVO, int bid, HttpSession session) throws Exception{
			
			boardService.update(boardVO);
			
			System.out.println("제대로 글이 수정 되었나??" + boardService.readContent(bid));
			
			return "redirect:/board/readList";

		}
		
	
	//게시글 삭제
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String deleteGET(RedirectAttributes rttr, int bid) throws Exception{
			
			boardService.delete(bid);
			return "redirect:/board/readList";
		}
	
	
		
		
		@ExceptionHandler(RuntimeException.class)

		public String exceptionHandler(Model model, Exception e){

		//logger.info("exception : " + e.getMessage());

		model.addAttribute("exception", e);

		return "error/exception";

		}
		
		
		
		//댓글 기능 구현중
		
		//댓글 내용 수정 폼 호출
		@RequestMapping(value = "/updateComment", method = RequestMethod.GET)
		public String updateCommentGET(CommentVO commentVO, HttpSession session) throws Exception{
			
			System.out.println("과연 무슨 댓글이 넘어왔을까? " + commentVO);
			session.setAttribute("updateComment", boardService.readUpdateComment(commentVO.getCid()));
			
			return "board/updateComment";
			
		}

		//댓글 내용 수정
		@RequestMapping(value = "/updateComment", method = RequestMethod.POST)
		public String updateCommentPOST(CommentVO commentVO, RedirectAttributes rttr) throws Exception{
					
			boardService.updateComment(commentVO);
			
				String url = "redirect:/board/readContent?bid=";
				
				url = url + commentVO.getBid();
			
				return url ;
					
				}
		
		//댓글내용 삭제 폼
		@RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
		public String deleteCommentGET(CommentVO commentVO, HttpSession session) throws Exception{
			
			session.setAttribute("deleteComment", boardService.readUpdateComment(commentVO.getCid()));
			
			return "board/deleteComment";
			
		}
		
		//댓글내용 삭제
		@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
		public String deleteCommentPOST(CommentVO commentVO, RedirectAttributes rttr) throws Exception{
			
			
			System.out.println("삭제될 댓글 내용은 뭐지? " +commentVO);
			
			//댓글 삭제 이전에 미리 bid를 이용하여 url를 저장한 후, 삭제작업을 진행하는것;
			String url = "redirect:/board/readContent?bid=";
			
			url = url + commentVO.getBid();
			
			boardService.deleteComment(commentVO.getCid());
		
			return url ;
			
		}
		
		//댓글 작성
		@RequestMapping(value = "/writeComment", method = RequestMethod.POST)
		public String writeCommentPOST(CommentVO commentVO, RedirectAttributes rttr) throws Exception{
			
			System.out.println("댓글 작성 직전 확인 중 : " + commentVO);
			
			boardService.writeComment(commentVO);
			
			String url = "redirect:/board/readContent?bid=";
			
			url = url + commentVO.getBid();
			
			return url;
				
		}
		
		
}
