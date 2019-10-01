package kr.co.itcen.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList(String kwd, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", (page-1)*5);
		List<BoardVo> result = null;
		
		if(kwd!=null && kwd.length() != 0) {
			map.put("kwd", "%"+kwd+"%");
			result = sqlSession.selectList("board.getkwd", map);
		}else {
			result = sqlSession.selectList("board.getList", map);
		}
		
		return result;
	}
	
	//선택한 게시물 내용 보여주기
	public BoardVo get(Long no) {
		return sqlSession.selectOne("board.getByNo", no);
	}
	
	//게시물 작성
	public boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;		
	}
	
	public boolean modify(BoardVo vo) {
		int count = sqlSession.update("board.update", vo);
		return count == 1;
	}
	
	public boolean delete(Long no) {
		int count = sqlSession.update("board.delete", no);
		return count == 1;
	}	
	
	//리플남기는 메소드 추가 reply
	public boolean updateReply(BoardVo vo) {
		int count = sqlSession.update("board.updatereply", vo);
		return count >= 0;
	}

	public boolean insertReply(BoardVo vo) {
		int count = sqlSession.insert("board.insertreply", vo);
		return count >= 0;
	}
	
	//조회수
	public void visit(Long no) {
		sqlSession.update("board.visit", no);	
	}
	
	public int getCount(String kwd) {
		int count = 0;
		if(kwd!=null && kwd.length() != 0) {
			kwd = "%"+kwd+"%";
			count = sqlSession.selectOne("board.getCountkwd",kwd);
		}else {
			count = sqlSession.selectOne("board.getCount");
		}
		return count;
	}
}