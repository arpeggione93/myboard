package org.mhboard.web.board.service;

import java.util.List;

import javax.inject.Inject;

import org.mhboard.web.board.dao.BoardDAO;
import org.mhboard.web.board.vo.BoardVO;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;

		//목록 불러오기
		public List<BoardVO> readList() throws Exception{
			return boardDAO.readList();
		}

		//글작성
		@Override
		public void write(BoardVO boardVO) throws Exception {
			boardDAO.write(boardVO);

		}



	
	
}
