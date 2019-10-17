package kr.co.itcen.mysite.exception;

public class GuestbookDaoException extends RuntimeException {
	//예외 전환
	public GuestbookDaoException() {
		super("GuestbookDaoException Occurs");
	}

	public GuestbookDaoException(String message) {
		super(message);
	}
}
