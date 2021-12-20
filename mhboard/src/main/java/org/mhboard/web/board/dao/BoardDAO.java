package org.mhboard.web.board.dao;

import java.util.List;

import org.mhboard.web.board.vo.BoardVO;

public interface BoardDAO {

	 List<BoardVO> readList() throws Exception;

	 BoardVO readContent(int bid) throws Exception;
	
	 int write(BoardVO boardVO) throws Exception;

	 int update(BoardVO boardVO) throws Exception;

	 int delete(int bid) throws Exception;

	 int updateViewCnt(int bid) throws Exception;
	
	
}
