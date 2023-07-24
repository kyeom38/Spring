package net.softsociety.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller 
{
	@GetMapping({"","/"})
	public String home() 
	{
		return "guestbook";
	}
}
