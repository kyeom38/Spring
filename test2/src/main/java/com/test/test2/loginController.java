package com.test.test2;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("test2")
@Controller
public class loginController 
{
	@GetMapping ("login")
	public String login()
	{
		return "login";
	}
	
	@PostMapping("login")
	public String login(String id, String pw, HttpSession session)
	{
		String dbid ="ykkim9238";
		String dbpw ="0000";
		String name ="김여경";
		
		if(id!=null && pw!=null && id.equals(dbid) && pw.equals(dbpw))
		{
			session.setAttribute("loginId",id);
			session.setAttribute("loginName",name);
			return "redirect:/";
		}
		else
		{
			return "login";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("loginId");
		return "redirect:/";
	}
}
