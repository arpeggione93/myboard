package org.mhboard.web.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.mhboard.web.board.dao.BoardDAO;
import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.board.vo.CommentVO;
import org.mhboard.web.board.vo.MemberVO;
import org.mhboard.web.paging.Paging;
import org.mhboard.web.paging.Search;
import org.mhboard.web.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class BoardServiceImpl implements BoardService {

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Inject
	private BoardDAO boardDAO;

		//목록 불러오기
		public List<BoardVO> readList(Search search) throws Exception{
			return boardDAO.readList(search);
		}

		//글작성
		@Override
		public void write(BoardVO boardVO, MultipartHttpServletRequest mpReq) throws Exception {
			boardDAO.write(boardVO);
			
			List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(boardVO, mpReq);
			int size = list.size();
			for(int i = 0; i<size; i++) {
				boardDAO.insertFile(list.get(i));
			}
		}

		//파일 조회 구현중
		public List<Map<String, Object>> selectFile(int bid) throws Exception{
			
			return boardDAO.selectFile(bid);
			
		}
		
		//파일 다운로드 구현중
		public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception{
			
			
			return boardDAO.selectFileInfo(map);
			
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
		public void update(BoardVO boardVO, String[] files, String[] fileNames, MultipartHttpServletRequest mpReq) throws Exception{
			
			boardDAO.update(boardVO);
			
			
			List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(boardVO, files, fileNames, mpReq);
			Map<String, Object> tempMap = null;
			int size = list.size();
			for(int i = 0; i < size; i++) {
				tempMap = list.get(i);
				if(tempMap.get("IS_NEW").equals("Y")) {
					boardDAO.insertFile(tempMap);
				}else {
					boardDAO.updateFile(tempMap);
				}
			
		}
		}
		
		@Override
		//게시물 삭제
		public void delete(int bid) throws Exception{
			
			boardDAO.delete(bid);
			
		}
		
		
		//파일 삭제 기능 구현중
		@Override
		public void deleteFile(int bid) throws Exception{
			 
			 boardDAO.deleteFile(bid);
		 }
		


		//페이징처리(총 게시물 갯수)
		@Override
		public int readListCnt(Search search) throws Exception{
			
			return boardDAO.readListCnt(search);
			
		}
		
	
		
		 //댓글 기능들
		 
		 //댓글 불러오기
		@Override
		public List<CommentVO> readComment(int bid) throws Exception{
			
			return boardDAO.readComment(bid);
			
		}
		 
		 //댓글 작성
		@Override
		public int writeComment(CommentVO commentVO) throws Exception{
			
			return boardDAO.writeComment(commentVO);
			
		}
		 
		 //댓글 수정
		@Override
		public int updateComment(CommentVO commentVO) throws Exception{
			
			return boardDAO.updateComment(commentVO);
			
		}
		 
		 //댓글 삭제
		@Override
		public int deleteComment(int cid) throws Exception{
			
			return boardDAO.deleteComment(cid);
			
		}
		
		
		//수정할 댓글 불러오기
		public CommentVO readUpdateComment(int cid) throws Exception{
			
				return boardDAO.readUpdateComment(cid);
			
		}
		
		
		//회원가입 구현중
		public void regist(MemberVO memberVO) throws Exception{
			
			boardDAO.regist(memberVO);
			
		}
		
		
		
		
		
}
