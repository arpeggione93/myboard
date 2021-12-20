package org.mhboard.web.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mhboard.web.board.vo.BoardVO;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{

	private static final String NAMESPACE = "org.mhboard.web.mappers.boardMapper";
	
	private SqlSession sqlSession;

    @Inject
    public BoardDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
    
    //게시물 목록
    @Override
	public List<BoardVO> readList() throws Exception{
		return sqlSession.selectList(NAMESPACE + ".readList");
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
    public int readListCnt() throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".readListCnt");
    }
	 
	
	
}