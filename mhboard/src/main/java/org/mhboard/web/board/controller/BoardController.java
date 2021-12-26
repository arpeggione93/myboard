package org.mhboard.web.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mhboard.web.board.service.BoardService;
import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.board.vo.CommentVO;
import org.mhboard.web.board.vo.MemberVO;
import org.mhboard.web.paging.Paging;
import org.mhboard.web.paging.Search;
import org.mhboard.web.util.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;


@Controller
@RequestMapping(value= "/board/*")
public class BoardController {

/*	
	 //파일 디렉토리 사용시
    @Resource(name="uploadPath")
    private String uploadPath;
	
*/	
	
	@Inject
	BCryptPasswordEncoder pwEncoder;
	
	@Inject
	private BoardService boardService;
	
	

	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() {
		
		return "board/login";
	}
	
	

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		
		
		 session.invalidate();
    
		
		return "redirect:/";
		
	}
	
	
	//여기까지
	
	//회원가입 기능 구현중(임시)
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String registGET(HttpSession session) {
		
		String url = "";
		
		if(session.getAttribute("loginMember") == null) {
		
		url = "board/regist";
		
		}else {
			
			url = "redirect:/";
			
		}
		
		return url;
		
	}
	
	//회원가입 요청(임시)
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registPOST(MemberVO memberVO, RedirectAttributes rttr, HttpSession session) throws Exception {
		
		String url = "";
		session.setAttribute("memberVO", memberVO);
		
		int idChk = boardService.idChk(memberVO); // 아이디 중복확인 1값이면 중복, 0이면 중복x
		int nickChk = boardService.nickChk(memberVO); // 닉네임 중복확인
		int emailChk = boardService.emailChk(memberVO); // 이메일 중복확인
		
		System.out.println(idChk + "   "+nickChk +"값 두개 확인좀");
		
		//회원가입 이전 아이디 중복확인
		if(idChk == 0) {
		
			//닉네임 중복확인
			if(nickChk == 0) {
			
				
				if(emailChk == 0) {
				
				String pw = memberVO.getMemberPw();
				String chPw = pwEncoder.encode(pw);
				
				memberVO.setMemberPw(chPw);
				
				System.out.println("최종 회원가입 정보 : " + memberVO);
				boardService.regist(memberVO);
				rttr.addFlashAttribute("msg1", "회원가입을 축하합니다.");
				
				url = "redirect:/";
				}else {
					
					url = "redirect:/board/regist";
					
				}
				
				
			}else {
			
				rttr.addFlashAttribute("msg2", "닉네임이 중복되었습니다..");
				url = "redirect:/board/regist";
				
			}
			
			
			
		}else {
			
			rttr.addFlashAttribute("msg2", "아이디가 중복되었습니다..");
			url = "redirect:/board/regist";
			
			
		}
			

			
		
		return url;
		
		
	}
	
	
	//추가해야 할 기능 : 회원가입 시 비밀번호 암호화 하기
	
	
	
	
	
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
	public String writeGET(HttpSession session) {
		
		String url = "";
		
		if(session.getAttribute("loginMember") == null) {
			
			url = "redirect:/board/readList";
			
		}else {
		
			url = "board/writeForm";
			
		}
		
		return url;
		
		
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
	
	
	
	
	
	@RequestMapping(value="/imgUpload", method=RequestMethod.POST)
	@ResponseBody
	public String fileUpload(HttpServletRequest req, HttpServletResponse resp, 
                 MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload) throws Exception {
		JsonObject json = new JsonObject();
		PrintWriter printWriter = null;
		OutputStream out = null;
		MultipartFile file = multiFile.getFile("upload");
		if(file != null){
			if(file.getSize() > 0 && StringUtils.isNotBlank(file.getName())){
				if(file.getContentType().toLowerCase().startsWith("image/")){
					try{
						
						System.out.println("이것은 실제 프로젝트 로컬 위치임 : " + req.getRealPath("/"));
						System.out.println("이것은 프로젝트 절대경로 확인 :" + req.getRealPath(""));
						String fileName = file.getName();
						System.out.println("이것이 파일명 : " + fileName);
						byte[] bytes = file.getBytes();
						//String uploadPath = req.getServletContext().getRealPath("/img");
						//String uploadPath = "C:\\mp\\img\\";
						//String uploadPath ="/Users/mhc/Documents/mp/img";
						String uploadPath = "//usr//local//tomcat//webapps//ROOT//file//";
						
						File uploadFile = new File(uploadPath);
						
						
						if(!uploadFile.exists()){
							uploadFile.mkdirs();
						}
						fileName = upload.getOriginalFilename();
						fileName = UUID.randomUUID().toString() + "_" +fileName;
						
						uploadPath = uploadPath + "/" + fileName;
						out = new FileOutputStream(new File(uploadPath));
                        out.write(bytes); //여기까지가 서버에 직접 이미지 저장하는 흐름
                        System.out.println("이것이 업로드파일 (uploadFIle):" +uploadFile );
						
                        printWriter = resp.getWriter();
                        //resp.setContentType("text/html;charset=utf-8");
                        resp.setCharacterEncoding("utf-8"); 
                        resp.setContentType("application/json");
                       // String fileUrl = req.getContextPath() + "/img/" + fileName;
                        //String fileUrl = "C:\\mp\\img\\" + fileName;
                        String fileUrl = "//usr//local//tomcat//webapps//ROOT//file//" + fileName;
                        // json 데이터로 등록
                        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
                        // 이런 형태로 리턴이 나가야함.
                        json.addProperty("uploaded", 1);
                        json.addProperty("fileName", fileName);
                        json.addProperty("url", fileUrl);
                        
                        
                        printWriter.println(json);
                        
                    }catch(IOException e){
                        e.printStackTrace();
                    }finally{
                        if(out != null){
                            out.close();
                        }
                        if(printWriter != null){
                            printWriter.close();
                        }		
					}
				}
			}
		}
		return null;
	}	
	
	
	
	
	
	
	
	
/*
	// ck 에디터에서 파일 업로드
	@RequestMapping(value = "/imgUpload", method = RequestMethod.POST)
	public void postCKEditorImgUpload(HttpServletRequest req,
	          HttpServletResponse res,
	          @RequestParam MultipartFile upload) throws Exception {

	
	
	 // 랜덤 문자 생성
	 UUID uid = UUID.randomUUID();
	 
	 OutputStream out = null;
	 PrintWriter printWriter = null;
	   
	 // 인코딩
	 //res.setCharacterEncoding("utf-8");
	 //res.setContentType("text/html;charset=utf-8");
	
	 
	 try {
	  
	  String fileName = upload.getOriginalFilename();  // 파일 이름 가져오기
	  System.out.println("제대로 이미지 들어가는지:" + fileName);
	  byte[] bytes = upload.getBytes();
	  
	  System.out.println("이게 뭔말?? (byte) : " + bytes.toString());
	  
	  String uploadPath="C:\\" + File.separator + "mp\\" + File.separator+"img\\" + File.separator;
	  // 업로드 경로
	  //String ckUploadPath = uploadPath + uid + "_" + fileName;
	  
	  String ckUploadPath = uploadPath + uid + "_" + fileName;
	  
	  
	  System.out.println("이번에는 경로까지 수정했을때 (uploadPath): " + uploadPath);
	  System.out.println("이번에는 경로까지 수정했을때 (ckuploadPath): " + ckUploadPath);
	  
	  out = new FileOutputStream(new File(ckUploadPath));
	  System.out.println("이건 뭐임(out): " +out);
	  out.write(bytes);
	  out.flush();  // out에 저장된 데이터를 전송하고 초기화
	  
	  String callback = req.getParameter("CKEditorFuncNum");
	  System.out.println("콜백 몇번인지 확인 :" + callback);
	  printWriter = res.getWriter();
	  String fileUrl =  uploadPath + uid + "_" + fileName;  // 작성화면
	  
	  System.out.println("이건 작성화면에서 나오는 파일 경로 : " + fileUrl);
	  
	  // 업로드시 메시지 출력
	  printWriter.println("<script type='text/javascript'>"
	     + "window.parent.CKEDITOR.tools.callFunction("
	     + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
	     +"</script>");
	  
	  printWriter.flush();
	  
	 } catch (IOException e) { e.printStackTrace();
	 } finally {
	  try {
	   if(out != null) { out.close(); }
	   if(printWriter != null) { printWriter.close(); }
	  } catch(IOException e) { e.printStackTrace(); }
	 }
	 
	 
	 
	
	}
	
	
	*/
	
	/*
	//글작성시 이미지 첨부 기능
	@RequestMapping(value="/imgUpload", method=RequestMethod.POST)
	public void imgUploadPOST(HttpServletRequest req,HttpServletResponse resp, @RequestParam MultipartFile mtp) throws Exception {

		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		
		 try {
	  
		
		 //파일 이름 가져오기
        String fileName=mtp.getOriginalFilename();
        System.out.println("이게 바로 본문 이미지 삽입 :" + fileName);
 
        //파일을 바이트 배열로 변환
        byte[] bytes=mtp.getBytes();

        //이미지를 업로드할 디렉토리를 정해준다
        String uploadPath="C:\\mp\\img\\";
        OutputStream out=new FileOutputStream(new File(uploadPath+fileName));
 
        //서버에 write
        out.write(bytes);
        
        //성공여부 가져오기
        String callback=req.getParameter("CKEditorFuncNum");
        
        //클라이언트에 이벤트 추가 (자바스크립트 실행)
        PrintWriter printWriter=resp.getWriter(); //자바스크립트 쓰기위한 도구
 
        String fileUrl= req.getContextPath()+uploadPath+fileName;
       
        if(!callback.equals("1")) { // callback이 1일 경우만 성공한 것
        	 printWriter.println("<script>alert('이미지 업로드에 실패했습니다.');"+"</script>");

        }else {
        	 System.out.println("upload img 들어온다! "+fileUrl);
             
             printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction("+callback+",'"+fileUrl+"','이미지가 업로드되었습니다.')"+"</script>");
             
        }
        
        printWriter.flush();
        
         } catch (IOException e) { e.printStackTrace();
	 } finally {
	 
		 
		 
		 
		 
	 }
        
        
   
	}

	
*/
	
	
	
	
	//첨부파일 다운로드 기능
	@RequestMapping(value="/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse res) throws Exception{
		Map<String, Object> resultMap = boardService.selectFileInfo(map);
		String str_file_name = (String) resultMap.get("str_file_name");
		String org_file_name = (String) resultMap.get("org_file_name");
		
		String filePath = null;
		
		//filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치(로컬)
		
		filePath = 	"//usr//local//tomcat//webapps//ROOT//file//"; 	//파일 저장 위치(서버)	
				
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath + str_file_name));
		
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
		
		String url = "";
		
		if(session.getAttribute("loginMember") == null) {
			
			url = "redirect:/board/readList";
			
		}else {
			
			session.setAttribute("Content", boardService.readContent(bid));
			session.setAttribute("boardVO", new BoardVO());
			
			List<Map<String, Object>> fileList = boardService.selectFile(bid);
			session.setAttribute("file", fileList);
			
			url = "board/editForm";
			
		}
		
	
		return url;
	}
	
	
	
	//게시글 내용 수정
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String updatePOST(BoardVO boardVO, int bid, HttpSession session,  @RequestParam(value="fileNoDel[]") String[] files,
				 @RequestParam(value="fileNameDel[]") String[] fileNames,
				 MultipartHttpServletRequest mpReq) throws Exception{
			
			System.out.println("이 값 확인좀" + " " + files +" " + boardVO +" " + bid +" " +fileNames);
			boardService.update(boardVO, files, fileNames, mpReq);
			
			System.out.println("제대로 글이 수정 되었나??" + boardService.readContent(bid));
			
			return "redirect:/board/readList";

		}
		
	
	//게시글 삭제
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public String deleteGET(RedirectAttributes rttr, int bid, HttpSession session) throws Exception{
			
			
			String url = "";
			
			if(session.getAttribute("loginMember") == null) {
				
				url = "redirect:/board/readList";
				
			}else {
				
				System.out.println("파일있는지 확인좀" + boardService.selectFile(bid));
				
				if(boardService.selectFile(bid) != null) {
					
					boardService.deleteFile(bid);
					boardService.delete(bid);
				}else {
				
					
					boardService.delete(bid);
					
					
				}
				
				url = "redirect:/board/readList";
			}
			
		
			
			
			return url;
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
