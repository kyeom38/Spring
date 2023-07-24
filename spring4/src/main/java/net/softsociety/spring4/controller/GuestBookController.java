package net.softsociety.spring4.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring4.domain.GuestBook;
import net.softsociety.spring4.domain.Member;
import net.softsociety.spring4.service.GuestBookService;

@Slf4j
@Controller
public class GuestBookController 
{
	@Autowired
	GuestBookService service;
	
	//게시물 저장 폼
	@GetMapping("writeGB")
	public String writeGB()
	{
		return "writeGB";
	}
	
	//글 저장 
	@PostMapping("writeGB")
	public String writeGB(@AuthenticationPrincipal UserDetails user,GuestBook g)
	{
		
		if(g.getName()!=null)
		{
			log.debug("이프:{}",g);
			service.writeGB(g);
		}
		else
		{
			g.setName(user.getUsername());
			log.debug("else{}:",g);
			service.writeGB(g);
		}
		return "redirect:/";
	}
	
	//글 열람 
	@GetMapping("readGB")
	public String readGB(Model model)
	{
		 ArrayList<GuestBook> glist = service.readGB();
		 log.debug("별로래여{}:",glist);
		 model.addAttribute("readGB",glist);
		 return "readGB";
	}
	
	//글삭제 
	//전달받은 글 번호와 비밀번호를 전달하여 삭제하고 글목록으로 리다이렉트 
	@PostMapping("deleteGB")
	public String deleteGB(GuestBook g,Model model)
	{
		log.info("{}", g);
		int n = service.deleteGB(g);
		return "redirect:/readGB";
	}
	
	//글 열람 페이지. 삭제 버튼을 누를때 자바스크립트로 삭제 실행 
	@GetMapping("readGB2")
	public String readGB2(Model model)
	{
		ArrayList<GuestBook> glist = service.readGB();
		model.addAttribute("readGB",glist);
		return "readGB2";
	}
	
	//글 수정 폼으로 이동(값을 가지고 가기위해 하나 조회와 비슷한 개념으로 작성) 
	@PostMapping("reviseGB")
	public String reviseGB1(GuestBook g, Model model)
	//ModelAttribute : 메서드에서 폼으로 전달된 값을 가져오기 위함 . 
	{
		//수정폼을 출력하고 내용을 입력받는다. 
		log.debug("{}",g);
		GuestBook a = service.selectOne(g); //수정대상의 글 하나를 받아오기 위함
		log.debug("{}",a);
		if(a==null)
		{
			
			return "redirect:/readGB2";
		}
		model.addAttribute("a", a);
		return "reviseGB";
	}
	
	//글 수정 
	@PostMapping("reviseGB2")
	public String reviseGB2(GuestBook g)
	{
		log.debug("{}",g);
		int n = service.reviseGB(g);
		log.debug("{}",g);
		return "redirect:/readGB2";
	}
	
	//추천
	@PostMapping("upvote")
	public String upvote(@RequestParam int num)
	{
		int n= service.upvote(num);
		return "redirect:/readGB2";
	}
	
	//비추천
	@PostMapping("downvote")
	public String downvote(@RequestParam int num)
	{
		int n= service.downvote(num);
		return "redirect:/readGB2";
	}
}
