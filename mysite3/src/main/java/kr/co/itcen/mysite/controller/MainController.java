package kr.co.itcen.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@RequestMapping({"", "/main"})
	public String index() {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "안녕하세요~";
	}
	//한글이 깨지면 기본 설정이 안되어있는것
	
	@ResponseBody
	@RequestMapping("/hello2")
	public UserVo hello2() {
		UserVo vo = new UserVo();
		vo.setNo(7L);
		vo.setName("김민준");
		vo.setEmail("alswns2648@naver.com");
		return vo;
	}
}
