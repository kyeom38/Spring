package net.softsociety.test3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.test3.domain.AddInfo;
import net.softsociety.test3.service.AddInfoService;



@Slf4j
@Controller
public class AddInfoController 
{
	@Autowired
	AddInfoService address;
	
	@GetMapping ("addInfo")
	public String addInfo()
	{
		AddInfo a = new AddInfo(1,"김여경","경기도 양주시","111-1111","010-5589-9238");
		address.insertAddInfo(a);
		
		return "redirect:/";
	}
}
