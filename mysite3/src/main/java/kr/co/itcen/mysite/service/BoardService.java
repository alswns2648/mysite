package kr.co.itcen.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.mysite.repository.BoardDao;
import kr.co.itcen.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList(String kwd, int page) {
		return boardDao.getList(kwd, page);
	}
	
	//선택 게시물 내용 보여주기
	public BoardVo get(Long no) {
		return boardDao.get(no);
	}
	//게시물 작성
	public void insert(BoardVo vo) {
		boardDao.insert(vo);
	}

	//게시물 수정
	public void modify(BoardVo vo) {
		boardDao.modify(vo);
	}
	
	//게시물 삭제
	public void delete(Long no) {
		boardDao.delete(no);
	}
	
	//답글 달기
	public void updateReply(BoardVo vo) {
		boardDao.updateReply(vo);
	}

	public void insertReply(BoardVo vo) {
		boardDao.insertReply(vo);
	}
	
	//조회수
	public void visit(Long no) {
		boardDao.visit(no);
	}
	
	public int getCount(String kwd) {
		return boardDao.getCount(kwd);
	}
	
}