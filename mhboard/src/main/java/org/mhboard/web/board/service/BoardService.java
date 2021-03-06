package org.mhboard.web.board.service;

import java.util.List;
import java.util.Map;

import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.board.vo.CommentVO;
import org.mhboard.web.board.vo.MemberVO;
import org.mhboard.web.paging.Paging;
import org.mhboard.web.paging.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {

	
	
	//목록 불러오기 + 페이징처리
	public List<BoardVO> readList(Search search) throws Exception;

	//글작성 + 파일 업로드 구현중
	public void write(BoardVO boardVO, MultipartHttpServletRequest mpReq) throws Exception;
	
	//파일 조회 구현중
	public List<Map<String, Object>> selectFile(int bid) throws Exception;
	
	//파일 다운로드 구현중
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;

	//게시글 상세 내용 출력
	public BoardVO readContent(int bid) throws Exception;
	
	//게시글 수정 + 첨부파일 수정
	public void update(BoardVO boardVO, String[] files, String[] fileNames, MultipartHttpServletRequest mpReq) throws Exception;
	
	//게시물 삭제
	public void delete(int bid) throws Exception;

	//페이징처리(총 게시물 갯수)
	public int readListCnt(Search search) throws Exception;
	
	//파일 삭제 기능 구현중
	public	void deleteFile(int bid) throws Exception;
	
	
	
	 //댓글 기능들
	 
	 //댓글 불러오기
	public List<CommentVO> readComment(int bid) throws Exception;
	 
	 //댓글 작성
	public int writeComment(CommentVO commentVO) throws Exception;
	 
	 //댓글 수정
	public int updateComment(CommentVO commentVO) throws Exception;
	 
	 //댓글 삭제
	public int deleteComment(int cid) throws Exception;
	
	//수정할 댓글 불러오기
	public CommentVO readUpdateComment(int cid) throws Exception;
	
	
	//회원가입 구현중
	public void regist(MemberVO memberVO) throws Exception;
	
	//회원가입 중복 아이디 확인
	public int idChk(MemberVO memberVO) throws Exception;
		 
	//회원가입 중복 닉네임 확인
	public int nickChk(MemberVO memberVO) throws Exception;
			 
	
	//회원가입 중복 이메일 확인
	public int emailChk(MemberVO memberVO) throws Exception;
	
	//로그인을 위한 회원정보 조회
	public MemberVO selectMember(String memberId) throws Exception;
	
	
	 //회원가입 방지 기능 추가중
	public void updateAdmin(String admin) throws Exception;
	 
	public  int registCancel() throws Exception;
	 
	
}


