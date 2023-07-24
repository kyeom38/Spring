package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class examController 
{
	@GetMapping("ex/calc1")
	public String cal()
	{		
		return "paramView/calculator";
	}
	
	@PostMapping("ex/answer")
	public String answer(
			@RequestParam(name="num1",defaultValue= "0") int num1
			,@RequestParam(name="num2", defaultValue ="0") int num2,Model m)
	{
		int a = num1+num2;
		m.addAttribute("answer",a);
		return "paramView/answer";
	}
	
	

}
