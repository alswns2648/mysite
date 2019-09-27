package kr.co.itcen.mysite.exception;

public class BoardDaoException extends RuntimeException {
	//예외 전환
	public BoardDaoException() {
		super("BoardDaoException Occurs");
	}

	public BoardDaoException(String message) {
		super(message);
	}
}
