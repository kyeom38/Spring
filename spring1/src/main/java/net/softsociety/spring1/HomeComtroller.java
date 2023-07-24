package net.softsociety.spring1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeComtroller 
{
	@GetMapping({"","/"})
	public String home() 
	{
		return "home";
	}
	
	@GetMapping("imgtest")
	public String test()
	{
		System.out.println("test1() 실행");
		return "img";
	}
	
	@GetMapping("jstest")
	public String test2()
	{
		System.out.println("test2() 실행");
		return "js";
	}
	
	@GetMapping("js1")
	public String js1()
	{
		return "js1";
	}
	
	@GetMapping("js2")
	public String js2()
	{
		return "js2";
	}
	
	@GetMapping("js3")
	public String js3()
	{
		return "js3";
	}
	
	@GetMapping("js4")
	public String js4()
	{
		return "js4";
	}
	
	@GetMapping("js5")
	public String js5()
	{
		return "js5";
	}
	
	@GetMapping("js6")
	public String js6()
	{
		return "js6";
	}
}
	
