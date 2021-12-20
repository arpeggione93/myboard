package org.mhboard.web.board.service;

import java.util.List;

import javax.inject.Inject;

import org.mhboard.web.board.dao.BoardDAO;
import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.paging.Paging;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO boardDAO;

		//목록 불러오기
		public List<BoardVO> readList(Paging paging) throws Exception{
			return boardDAO.readList(paging);
		}

		//글작성
		@Override
		public void write(BoardVO boardVO) throws Exception {
			boardDAO.write(boardVO);

		}


		//게시글 상세내용 출력
		@Override
		public BoardVO readContent(int bid) throws Exception{
			boardDAO.updateViewCnt(bid); //게시글 조회 시, 해당 게시글의 조회수를 +1 해주는 역할
			System.out.println("상세내용 출력 하기전 : " + boardDAO.readContent(bid));
			return boardDAO.readContent(bid);
		}

		//게시글 수정
		@Override
		public void update(BoardVO boardVO) throws Exception{
			
			boardDAO.update(boardVO);
			
		}
		
		@Override
		//게시물 삭제
		public void delete(int bid) throws Exception{
			
			boardDAO.delete(bid);
			
		}


		//페이징처리(총 게시물 갯수)
		@Override
		public int readListCnt() throws Exception{
			
			return boardDAO.readListCnt();
			
		}
		
	
}
