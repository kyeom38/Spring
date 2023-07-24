package net.softsociety.spring5.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.domain.Member;
import net.softsociety.spring5.service.MemberService;

//회원정보 관련 콘트롤러 

@Slf4j
@RequestMapping("member")
@Controller
public class MemberController 
{
	@Autowired
	MemberService service;
	
	//회원가입 폼으로 이동 
	@GetMapping("join")
	public String join()
	{
		return "memberView/join";
	}

	//회원가입 처리 
	@PostMapping("join")
	public String join(Member m)
	{
		service.joinMember(m);
		log.debug(m.toString());
		return "redirect:/";
	}
	
	//ID중복확인 폼 
	@GetMapping("idcheck")
	public String idcheck()
	{
		return "memberView/idcheck";
	}
	
	//ID 중복확인 처리
	@PostMapping("idcheck")
	public String idcheck(String searchid, Model model)
	{
		//서비스 - ID를 전달받아서 DB에서 검색한 후 
		//결과가 null이면 true, 아니면 false 를 리턴 
		boolean result = service.idcheck(searchid);
		
		model.addAttribute("searchid",searchid);
		model.addAttribute("result",result);
		
		return "memberView/idcheck";
	}
	
	//로그인 폼으로 이동
	@GetMapping("loginForm")
	public String loginForm()
	{
		return "memberView/loginForm";
	}

	//수정폼으로 이동 
	@GetMapping("updateForm")
	public String updateForm(@AuthenticationPrincipal UserDetails user,Model model)
	{
		//로그인한 아이디로 회원정보 검색
		String userInfo = user.getUsername();
		Member m =  service.select(userInfo);
		//검색 결과를 Model에 저장
		model.addAttribute("m", m);
		
		log.debug("아이디:{}",user.getUsername());
		
		//HTML로 포워딩 
		return "memberView/updateForm";
	}
	
	//수정 처리 
	@PostMapping("update")
	public String update(@AuthenticationPrincipal UserDetails user, Member m)//memeber에 id pw는없음 null 
	{
		//로그인 한 아이디를 member객체에 추가 
		m.setMemberid(user.getUsername());
		//member객체를 서비스로 전달하여 DB수정
		service.update(m);
		//메인 화면으로 리다이렉트 
		return "redirect:/";
	}
}
