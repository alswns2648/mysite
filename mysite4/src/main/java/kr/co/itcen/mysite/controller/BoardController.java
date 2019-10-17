package kr.co.itcen.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value={"", "/list"})
	public String getList(Model model, @RequestParam(value = "kwd", defaultValue="", required = false ) String kwd,
			@RequestParam(value = "page", defaultValue="1", required = false) int page) {
		List<BoardVo> list = boardService.getList(kwd, page);
		int count = boardService.getCount(kwd);
		System.out.println(count);
		model.addAttribute("list",list);
		model.addAttribute("kwd", kwd);
		model.addAttribute("page", page);
		model.addAttribute("count", count);
		model.addAttribute("page_count", (page-1)/5);
		
		return "/board/list";
	}
	
	//글쓰기 창
	@RequestMapping(value = "/write", method=RequestMethod.GET)
	public String write(HttpSession session, @RequestParam(value = "kwd", defaultValue="", required = false) String kwd, @RequestParam(value = "page", defaultValue="1", required = false) int page,
			@RequestParam(value = "no",required =false) Long no, Model model ) {//Model세팅
		if (session == null) {
			return "redirect:/board";
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/board";
		}
		if(no!=null) {
			BoardVo vo = boardService.get(no);
			vo.setNo(no);
			model.addAttribute("vo", vo);
			model.addAttribute("page",page);
			model.addAttribute("kwd",kwd);
			
		}
		return "board/write";		
	}

	//글등록
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String write(HttpSession session, BoardVo vo, @RequestParam("kwd") String kwd 
			) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		vo.setUser_no(authUser.getNo());
		if(vo.getG_no() == null || vo.getDepth()==null || vo.getDepth()==null) {
			boardService.insert(vo);
		}else {
			vo.setG_no(vo.getG_no());
			vo.setO_no(vo.getO_no()+1);
			vo.setDepth(vo.getDepth()+1);
			boardService.updateReply(vo);
			boardService.insertReply(vo);
		}
		
		return "redirect:/board/list";
	}
	
	//글 보여주기
	@RequestMapping(value ="/view/{no}", method=RequestMethod.GET)
	public String view(Model model, @RequestParam(value = "kwd",defaultValue = "", required = false) String kwd
			, @PathVariable("no") Long no,
			@RequestParam(value = "page", defaultValue="1", required = false) int page) {
		BoardVo vo = boardService.get(no);
		boardService.visit(no);
		vo.setNo(no);
		
		model.addAttribute("vo", vo);
		model.addAttribute("page",page);
		model.addAttribute("kwd",kwd);
		
		return "board/view";
	}
	
	//글 수정 화면 이동
	@RequestMapping(value = "/modify/{no}",  method=RequestMethod.GET)
	public String modify(HttpSession session,@PathVariable("no") Long no, Model model) {
		if (session == null) {
			return "redirect:/board";
		} 
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/board";
		}
		BoardVo vo = boardService.get(no);
		vo.setNo(no);
		model.addAttribute("vo", vo);
		return "/board/modify";
	}
	
	//글 수정
	@RequestMapping(value ="/modify", method=RequestMethod.POST)
	public String modify(HttpSession session, BoardVo vo, Model model, @RequestParam("kwd") String kwd) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		vo.setUser_no(authUser.getNo());
		model.addAttribute("vo", vo);
		boardService.modify(vo);
		return "redirect:/board/view/" + vo.getNo();
	}
	
	//글 삭제
	@RequestMapping(value = "/delete/{no}",  method=RequestMethod.GET)
	public String modify(HttpSession session,@PathVariable("no") Long no) {
		if (session == null) {
			return "redirect:/board";
		} 
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/board";
		}
		boardService.delete(no);
		return "redirect:/board/list";
	}
	
}
