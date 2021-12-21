package org.mhboard.web.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.board.vo.CommentVO;
import org.mhboard.web.paging.Paging;
import org.mhboard.web.paging.Search;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{

	private static final String NAMESPACE = "org.mhboard.web.mappers.boardMapper";
	private static final String NAMESPACE2 = "org.mhboard.web.mappers.commentMapper";
	
	
	
	private SqlSession sqlSession;

    @Inject
    public BoardDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    //게시물 목록
    @Override
	public List<BoardVO> readList(Search search) throws Exception{
		return sqlSession.selectList(NAMESPACE + ".readList", search);
	}

	//게시물 내용
    @Override
	public BoardVO readContent(int bid) throws Exception{
		return sqlSession.selectOne(NAMESPACE + ".readContent", bid);
	}
	
	//게시글 작성
    @Override
	public int write(BoardVO boardVO) throws Exception{
		return sqlSession.insert(NAMESPACE + ".write", boardVO);
	}

	//게시글 수정
    @Override
	public int update(BoardVO boardVO) throws Exception{
		return sqlSession.update(NAMESPACE + ".update", boardVO);
	}

	
	//게시글 삭제
    @Override
	public int delete(int bid) throws Exception{
		return sqlSession.delete(NAMESPACE + ".delete", bid);
	}

	//조회수 갱신
    @Override
	public int updateViewCnt(int bid) throws Exception{
		return sqlSession.update(NAMESPACE + ".updateViewCnt", bid);
	}
	
    //총 게시글 갯수
    @Override
    public int readListCnt(Search search) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".readListCnt", search);
    }
	 
    
    
    //댓글 불러오기
    @Override
  	 public List<CommentVO> readComment(int bid) throws Exception{
  		 
  		return sqlSession.selectList(NAMESPACE2 + ".readComment", bid);
  		 
  	 }
  	 
  	 //댓글 작성
    @Override
  	 public int writeComment(CommentVO commentVO) throws Exception{
  		 
  		 return sqlSession.insert(NAMESPACE2 + ".writeComment", commentVO);
  		 
  	 }
  	 
  	 //댓글 수정
    @Override
  	 public int updateComment(CommentVO commentVO) throws Exception{
  		 
  		 return sqlSession.update(NAMESPACE2 + ".updateComment", commentVO);
  		 
  	 }
  	 
  	 //댓글 삭제
    @Override
  	 public int deleteComment(int cid) throws Exception{
  		 
  		 return sqlSession.delete(NAMESPACE2 + ".deleteComment", cid); 
  		 
  	 }
    
    //수정할 댓글 불러오기
  	public CommentVO readUpdateComment(int cid) throws Exception{
  		
  		return sqlSession.selectOne(NAMESPACE2 + ".readUpdateComment",cid);
  		
  	}
	
}