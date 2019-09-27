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
	
	public List<BoardVo> getList(String kwd) {
		return boardDao.getList(kwd);
	}
	
	//선택 게시물 내용 보여주기
	public BoardVo get(Long no) {
		return boardDao.get(no);
	}
	//게시물 작성
	public void insert(BoardVo vo) {
		boardDao.insert(vo);
	}
}