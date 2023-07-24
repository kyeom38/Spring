package net.softsociety.spring2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("ex")
@Slf4j
@Controller
public class CounterController 
{
	//방문횟수 확인 
	@GetMapping("cookie")
	public String cookiecheck(
			@CookieValue(name ="id", defaultValue ="없음") String id
			,@CookieValue(name="count", defaultValue ="0")int count 
			,Model model
			,HttpServletResponse res)
	{
		if(count==0)
		{
			return "getId";
		}
		else //쿠키 증가시킴 
		{
			count++; 
			Cookie countCookie = new Cookie("count",Integer.toString(count));
			res.addCookie(countCookie);
			countCookie.setMaxAge(60*60*24*365);
			Cookie idCookie = new Cookie("id",id);//입력받은 아이디 값을 쿠키에 저장
			res.addCookie(idCookie);
			countCookie.setMaxAge(60*60*24*365);
			
			model.addAttribute("id",id);
			model.addAttribute("count",count);
			
			return "cookie";
		}
	}

	//첫 방문 시 아이디 입력받기 
	@PostMapping("getId")
	public String cookie2(
			HttpServletResponse res
			,String id
			,Model model)	
	{
		if(id == null ||id.isEmpty())
		{
			return "getId";
		}
		Cookie idCookie = new Cookie("id",id); 
		Cookie countCookie= new Cookie("count","1");
		idCookie.setMaxAge(60*60*24*365);
		countCookie.setMaxAge(60*60*24*365);
		res.addCookie(idCookie);
		res.addCookie(countCookie);	
		
		model.addAttribute("id",id);
		model.addAttribute("count","1");
		return "cookie";
	}	
		
	/*
	 * @PostMapping("getId") 
	 * public String getId(
	 * 		@RequestParam (name="id") String id ,
	 * 		@CookieValue(name="count",
	 * 		required=false) String countStr //쿠키가 필수적이지 않음을 표시 ,
	 * 		Model model) {int count = 0; // 기본적으로 count 값을 0으로 설정
	 * 
	 * if (countStr != null) //쿠키생성 
	 * { 
	 * 		count = Integer.parseInt(countStr); // count 문자열을 정수로 변환 
	 * } 
	 * 
	 * model.addAttribute("id",id); 
	 * model.addAttribute("count",count);
	 * return "cookie"; }
	 */
}
