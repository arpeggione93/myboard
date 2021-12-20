package org.mhboard.web.board.service;

import java.util.List;

import org.mhboard.web.board.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;

public interface BoardService {

	
	
	//목록 불러오기
	public List<BoardVO> readList() throws Exception;

	//글작성
	public void write(BoardVO boardVO) throws Exception;
	
	//게시글 상세 내용 출력
	public BoardVO readContent(int bid) throws Exception;
	
	//게시글 수정 
	public void update(BoardVO boardVO) throws Exception;
	
	//게시물 삭제
	public void delete(int bid) throws Exception;

}


