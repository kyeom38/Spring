package net.softsociety.spring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring2.domain.Person;

@Slf4j //getter setter 따로 안만들어도 되게 해줌 
@Controller
public class ParameterController 
{
	@GetMapping("param/input1")
	public String input1()
	{
		return "paramView/view1";
	}
	
	@PostMapping("param/input1")
	public String save1(String name, int age , String phone)
	{
		log.debug("{},{},{}",name,age,phone); //log.debug는 개발자가 확인하려고 적은 태그 
		return "redirect:/"; //요청정보가 초기화(?)
	}
	
	@GetMapping("param/input2")
	public String input2()
	{
		return "paramView/view2";
	}
	
	@PostMapping("param/input2")
	public String save2(Person p) //변수명은 상관없음. 
	{
		log.debug("전달된 값:{}",p);
		return "redirect:/";
	}
		
	@GetMapping("param/input3")
	public String save3(
			@RequestParam(name="name",defaultValue= "기본값") String name
			,@RequestParam(name="age", defaultValue ="0") int age)
	{
		log.debug("{},{}",name,age);
		return "redirect:/";
	}
	
	@GetMapping("param/model")
	public String model(Model m)
	{
		String s ="abcd";
		int n = 1000;
		Person p = new Person("홍길동",10,"01011111111");
		
		m.addAttribute("string",s);
		m.addAttribute("number",n);
		m.addAttribute("person",p);
		
		return "paramView/model"; //model의 값을 가지고 뷰로 이동(?)
	}
}
