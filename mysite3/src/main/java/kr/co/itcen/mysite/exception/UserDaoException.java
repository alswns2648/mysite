package kr.co.itcen.mysite.exception;

public class UserDaoException extends RuntimeException { // 예외를 상속 받으면 반드시 try,catch
	//예외 전환
	public UserDaoException() {
		super("UserDaoException Occurs");
	}
	
	public UserDaoException(String message) {
		super(message);
	}
}
