package kr.co.itcen.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Target : annotation을 어디에 붙일지 설정
//ElementType.METHOD : class에 붙일 수 있게 해줌
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	
	public String value() default "USER";
	//public Role role() default Role.USER;
}