package net.softsociety.test3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController 
{
	@GetMapping({"","/"})
	public String address()
	{
		return "address";
	}
}
