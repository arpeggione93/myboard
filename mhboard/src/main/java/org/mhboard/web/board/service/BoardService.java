package org.mhboard.web.board.service;

import java.util.List;

import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.paging.Paging;
import org.mhboard.web.paging.Search;
import org.springframework.beans.factory.annotation.Autowired;

public interface BoardService {

	
	
	//목록 불러오기 + 페이징처리
	public List<BoardVO> readList(Search search) throws Exception;

	//글작성
	public void write(BoardVO boardVO) throws Exception;
	
	//게시글 상세 내용 출력
	public BoardVO readContent(int bid) throws Exception;
	
	//게시글 수정 
	public void update(BoardVO boardVO) throws Exception;
	
	//게시물 삭제
	public void delete(int bid) throws Exception;

	//페이징처리(총 게시물 갯수)
	public int readListCnt(Search search) throws Exception;
	
}


