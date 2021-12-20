package org.mhboard.web.board.dao;

import java.util.List;


import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.paging.Paging;

public interface BoardDAO {

	 List<BoardVO> readList(Paging paging) throws Exception;

	 BoardVO readContent(int bid) throws Exception;
	
	 int write(BoardVO boardVO) throws Exception;

	 int update(BoardVO boardVO) throws Exception;

	 int delete(int bid) throws Exception;

	 int updateViewCnt(int bid) throws Exception;
	
	//페이징처리 (총 게시글 갯수)
	 int readListCnt() throws Exception;
	 
	 
}
