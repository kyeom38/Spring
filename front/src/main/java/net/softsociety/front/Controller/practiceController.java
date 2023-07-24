package net.softsociety.front.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.front.dao.AjaxDAO;
import net.softsociety.front.service.AjaxService;

@Slf4j
@Controller
public class practiceController 
{
	@Autowired
	AjaxDAO dao;
	
	@Autowired
	AjaxService service;
	
	@GetMapping({"practice"})
	public String practice()
	{
		return "practice";
	}
	
	/*
	 * 추천 페이지로 이동
	 */
	@GetMapping({"recommend"})
	public String recommend()
	{
		return "/recommend";
	}
	
	@ResponseBody
	@PostMapping({"recommend"})
	public String recommend(int boardnum)
	{
		int cnt=0;
		dao.updateCnt(boardnum);
		cnt=dao.selectCnt(boardnum);
		return "/recommend";
	}
	
	
	@GetMapping({"idcheck"})
	public String idcheck()
	{
		return "idcheck";
	}
	
	@ResponseBody
	@PostMapping({"idcheck2"})
	public String idcheck2(String memberid) 
	{
		int n = service.idcheck2(memberid);
		return "idcheck2";
	}
	
}
