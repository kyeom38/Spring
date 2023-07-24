package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Spring2Controller 
{
	@GetMapping({"","/"})
	public String spring2() 
	{
		return "spring2";
	}
	
}
