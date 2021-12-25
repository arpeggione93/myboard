package org.mhboard.web.board.dao;

import java.util.List;
import java.util.Map;

import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.board.vo.CommentVO;
import org.mhboard.web.board.vo.MemberVO;
import org.mhboard.web.paging.Paging;
import org.mhboard.web.paging.Search;

public interface BoardDAO {

	 List<BoardVO> readList(Search search) throws Exception;

	 BoardVO readContent(int bid) throws Exception;
	
	 int write(BoardVO boardVO) throws Exception;

	 int update(BoardVO boardVO) throws Exception;

	 int delete(int bid) throws Exception;

	 int updateViewCnt(int bid) throws Exception;
	
	//페이징처리 (총 게시글 갯수)
	 int readListCnt(Search search) throws Exception;
	 
	 
	 //댓글 기능들
	 
	 //댓글 불러오기
	 List<CommentVO> readComment(int bid) throws Exception;
	 
	 //댓글 작성
	 int writeComment(CommentVO commentVO) throws Exception;
	 
	 //댓글 수정
	 int updateComment(CommentVO commentVO) throws Exception;
	 
	 //댓글 삭제
	 int deleteComment(int cid) throws Exception;
	 
	//수정할 댓글 불러오기
	 CommentVO readUpdateComment(int cid) throws Exception;
	 
	 
	 //파일 업로드 파트
	 void insertFile(Map<String, Object> map) throws Exception;
	 
	 //파일 조회 구현중
	 List<Map<String, Object>> selectFile(int bid) throws Exception;

	 //파일 다운로드 구현중
	 Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;

	 //파일 수정 기능 구현중
	 void updateFile(Map<String, Object> map) throws Exception;
	 
	 //파일 삭제 기능 구현중
	 int deleteFile(int bid) throws Exception;

	 
	 //회원가입 구현중
	 int regist(MemberVO memberVO) throws Exception;
	 
	 //회원가입 중복 아이디 확인
	 int idChk(MemberVO memberVO) throws Exception;
	 
	//회원가입 중복 닉네임 확인
	 int nickChk(MemberVO memberVO) throws Exception;
		 
	//회원가입 중복 이메일 확인
	 int emailChk(MemberVO memberVO) throws Exception;
		 
    //로그인을 위한 회원정보 조회
	 MemberVO selectMember(String memberId) throws Exception;
	 
}
