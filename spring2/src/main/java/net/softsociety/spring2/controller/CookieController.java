package net.softsociety.spring2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("ck")
@Controller
public class CookieController 
{
	//상세내용을 저장하지말고 간단한것만 .
	@GetMapping("cookie1")
	public String cookie(HttpServletResponse res)
	{
		//쿠키생성 한글 ㄴㄴ 영문과 숫자로 되어있는 이름과 값
		Cookie c1 = new Cookie("str","abcde");
		Cookie c2 = new Cookie("num","123");
		//쿠키의 종료시점 지정. 초단위
		c1.setMaxAge(60*60*24*3);
		c2.setMaxAge(60*60*24*3);
		//클라이언트로 쿠키 보내기 
		res.addCookie(c1);
		res.addCookie(c2);
		
		return "redirect:/";
	}
	
	@GetMapping("cookie2")
	public String cookie2(HttpServletResponse res)
	{
		Cookie a = new Cookie("str",null);
		Cookie b = new Cookie("num",null);
		a.setMaxAge(0);
		b.setMaxAge(0);
		res.addCookie(a);
		res.addCookie(b);
		return "redirect:/";
	}
	
	@GetMapping("cookie3")
	public String cookie3(
				@CookieValue(name="str",defaultValue="없음") String s 
				//이름이 str인 쿠기가 있냐  없으면 디폴트값을 내주고 s라는 변수에 저장한다. if문 생략
				,@CookieValue(name="num",defaultValue="0") int num)
	{
		log.debug("str:{},num:{}",s,num);
		
		return "redirect:/";
	}
	
}
