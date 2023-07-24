package net.softsociety.front.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseBody
@RequestMapping("aj")
@Controller
public class CommentRestController 
{
	@PostMapping("insert")
	public void insert() 
	{
	
	}
}
