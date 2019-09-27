package kr.co.itcen.mysite.repository;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList(String kwd) {
		List<BoardVo> result = sqlSession.selectList("board.getList", kwd);
		return result;
	}
	
	//선택한 게시물 내용 보여주기
	public BoardVo get(Long no) {
		return sqlSession.selectOne("board.getByNo", no);
	}
	
	//게시물 작성
	public Boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;		
	}
	
	
	
//	public boolean modify(BoardVo vo) {
//		Boolean result = false;
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		try {
//			connection = getConnection();
//
//			String sql = "update board set title = ?, contents = ? where no = ?";
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setLong(3, vo.getNo());
//			
//			int count = pstmt.executeUpdate();
//			result = (count == 1);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//	
//	public void delete(Long no) {
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			connection = getConnection();
//
//			String sql =" update board set status=false where no = ?";
//
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setLong(1, no);
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}		
//	}	
	
	//리플남기는 메소드 추가 reply
//	public boolean reply(BoardVo vo) {
//		Boolean result = false;
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			connection = getConnection();
//
//			String sql = "update board set o_no = o_no+1 where g_no = ? and o_no >= ?";
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setInt(1, vo.getG_no());
//			pstmt.setInt(2, vo.getO_no());
//			pstmt.executeUpdate();
//
//			String sql1 = "insert into board values(null, ?, ?, 0, now(), ?, ?, ?, ?, true)";
//			pstmt = connection.prepareStatement(sql1);
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setInt(3, vo.getG_no());	
//			pstmt.setInt(4, vo.getO_no());
//			pstmt.setInt(5, vo.getDepth());
//			pstmt.setLong(6, vo.getUser_no());
//
//
//			int count = pstmt.executeUpdate();
//			result = (count == 1);
//
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//
//				if(pstmt != null) {
//					pstmt.close();
//				}
//
//				if(connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return result;	
//
//	}
//	
//	public boolean visit(Long no) {
//		Boolean result = false;
//
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			connection = getConnection();
//
//			String sql = "update board set hit = hit+1 where no=?";
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setLong(1, no);
//			int count = pstmt.executeUpdate();
//
//			result = (count == 1);
//
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//
//				if(pstmt != null) {
//					pstmt.close();
//				}
//
//				if(connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return result;	
//
//	}

}