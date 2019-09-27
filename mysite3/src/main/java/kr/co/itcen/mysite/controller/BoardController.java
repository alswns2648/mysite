package kr.co.itcen.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.itcen.mysite.service.BoardService;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value={"", "/list"}, method = RequestMethod.GET)
	public String getList(Model model) {
		String kwd = "";
		List<BoardVo> list = boardService.getList(kwd);
		model.addAttribute("list",list);
		return "/board/list";
	}
	//글등록
	@RequestMapping(value ="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo vo, @RequestParam("kwd") String kwd ) {
		boardService.insert(vo);
		return "board/list";
	}
	
	//글쓰기 창
	@RequestMapping(value ="/write", method=RequestMethod.GET)
	public String write(HttpSession session, @RequestParam("kwd") String kwd ) {
		if (session == null) {
			return "redirect:/board";
		} 
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null)
			return "redirect:/board";
		return "board/write";
	}
	
	@RequestMapping("/view")
	public String view(Model model, @RequestParam("kwd") String kwd, @RequestParam("no") Long no) {
		BoardVo vo = boardService.get(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	

}
