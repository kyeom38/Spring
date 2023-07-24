package net.softsociety.front.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("jq")
@Controller
public class jQueryController {

	
	@GetMapping({"jq1"})
	public String jq1()
	{
		return "jq1";
	}
	
	@GetMapping({"jq2"})
	public String jq2()
	{
		return "jq2";
	}
	
	@GetMapping({"jq3"})
	public String jq3()
	{
		return "jq3";
	}
	
	@GetMapping({"jq4"})
	public String jq4()
	{
		return "jq4";
	}
	
	@GetMapping({"jq5"})
	public String jq5()
	{
		return "jq5";
	}
}
