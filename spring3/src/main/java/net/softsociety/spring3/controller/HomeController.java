package net.softsociety.spring3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{
	@GetMapping({"","/"}) //중괄호는 여러개 묶음 
	public String home()
	{
		return "home";
	}
}
