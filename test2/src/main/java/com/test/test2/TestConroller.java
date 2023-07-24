package com.test.test2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestConroller {

	@GetMapping({"","/"})
	public String home() 
	{
		return "test2";
	}
}
