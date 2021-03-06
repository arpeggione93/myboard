package org.mhboard.web.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.mhboard.web.board.vo.BoardVO;
import org.mhboard.web.board.vo.CommentVO;
import org.mhboard.web.board.vo.MemberVO;
import org.mhboard.web.paging.Paging;
import org.mhboard.web.paging.Search;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{

	private static final String NAMESPACE = "org.mhboard.web.mappers.boardMapper";
	private static final String NAMESPACE2 = "org.mhboard.web.mappers.commentMapper";
	private static final String NAMESPACE3 = "org.mhboard.web.mappers.memberMapper";
	
	
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
    @Override
  	public CommentVO readUpdateComment(int cid) throws Exception{
  		
  		return sqlSession.selectOne(NAMESPACE2 + ".readUpdateComment",cid);
  		
  	}
	
  	//파일 업로드 파트
    @Override
  	 public void insertFile(Map<String, Object> map) throws Exception{
  		 sqlSession.insert(NAMESPACE + ".insertFile", map);
  	 }
  	
  	//파일 조회 구현중
    @Override
  	public List<Map<String, Object>> selectFile(int bid) throws Exception{
  		 
  		return sqlSession.selectList(NAMESPACE + ".selectFile", bid);
  		 
  	 }
  	
  	//파일 다운로드 구현중
    @Override
  	 public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception{
  		 
  		 return sqlSession.selectOne(NAMESPACE + ".selectFileInfo", map);
  		 
  	 }

  	//파일 수정 기능 구현중
    @Override
  		public void updateFile(Map<String, Object> map) throws Exception{
  			 
  			 sqlSession.update(NAMESPACE + ".updateFile", map);
  			 
  		 }
  		 
    
  
    
  //파일 삭제 기능 구현중
    @Override
  	 public int deleteFile(int bid) throws Exception{
  		 
  		return sqlSession.delete(NAMESPACE + ".deleteFile" , bid);
  		 
  	 }
  	
    
    //회원가입 구현중
    @Override
	public int regist(MemberVO memberVO) throws Exception{
    	
    	return sqlSession.insert(NAMESPACE3 + ".regist", memberVO);
    	
    }
	 
    //회원가입 중복 아이디 확인
    @Override
  	 public int idChk(MemberVO memberVO) throws Exception{
    	
    	return sqlSession.selectOne(NAMESPACE3 + ".idChk", memberVO);
    	
    }
  	 
  //회원가입 중복 닉네임 확인
   @Override 
  	public int nickChk(MemberVO memberVO) throws Exception{
	   
	   return sqlSession.selectOne(NAMESPACE3 + ".nickChk", memberVO); 
   }
  
   @Override
 //회원가입 중복 이메일 확인
	public int emailChk(MemberVO memberVO) throws Exception{
	   
	   return sqlSession.selectOne(NAMESPACE3 + ".emailChk", memberVO); 
	   
   }
	
  @Override
     //로그인을 위한 회원정보 조회
  public MemberVO selectMember(String memberId) throws Exception{
	
	  return sqlSession.selectOne(NAMESPACE3 + ".selectMember", memberId);
  }
	 
  @Override
  //회원가입 방지 기능 추가중
  public void updateAdmin(String admin) throws Exception{
	  
	  sqlSession.selectOne(NAMESPACE3 + ".updateAdmin", admin); 
  }
	 
  @Override
  public int registCancel(){
	  
	  return sqlSession.selectOne(NAMESPACE3 + ".registcancel");
	  
  }
   
}