package net.softsociety.spring5.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class homeController 
{
	@GetMapping({"","/"})
	public String home()
	{
		return "home";
	}
	
	@GetMapping({"thymeleaf"})
	public String thymeleaf()
	{
		return "thymeleaf";
	}
	
	@GetMapping({"admin"})
	public String admin()
	{
		return "admin";
	}
	
	@GetMapping({"errortest"})
	public String errortest()
	{
		return "errortest";
	}
}
