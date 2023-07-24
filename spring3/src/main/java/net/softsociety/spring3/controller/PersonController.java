package net.softsociety.spring3.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring3.domain.Person;
import net.softsociety.spring3.service.PersonService;
import oracle.jdbc.proxy.annotation.Post;

/*
 * 회원정보를 처리하는 콘트롤러
 */

@Slf4j
@Controller
public class PersonController 
{
	@Autowired //메모리에 저장된 객체를 있는지 확인하고 대입함 (?)Person service 의 객체를 가져옴  
	PersonService service;
	/*
	 * DB에 정보 저장 기능 
	 */
	
	//회원정보 저장 
	@GetMapping("insert")
	public String insert()
	{
		return "insertForm";
	}
	
	@PostMapping("insert")
	public String insert(Person p)
	{
		service.insertPerson(p);
		
		return "redirect:/";
	}
	
	//회원정보 삭제(주민등록번호 기준)
	@GetMapping("delete")
	public String delete()
	{
		return "deleteForm";
	}
	
	@PostMapping("delete")                               
	public String delete(String idnum,Model model)
	{
		//전달받은 주민등록번호로 회원정보 삭제
		int n = service.deletePerson(idnum);
		if(n==0)
		{
			log.debug("삭제 실패");
			model.addAttribute("error","삭제 실패");
		}
		return "redirect:/";
	}
	
	@GetMapping("delete2")
	public String delete2()
	{
		return "deleteForm2";
	}
	
	@PostMapping("delete2")
	public String delete2(String name, Model model)
	{
		int n = service.delete2Person(name);
		if(n==0)
		{
			log.debug("삭제 실패");
			model.addAttribute("error","삭제 실패");
		}
		return "redirect:/";
	}
	
	@GetMapping("deletePerson")
	public String deletePerson(String idnum, Model model)
	{
		//전달받은 주민등록번호로 회원정보 삭제 
		int n = service.deletePerson(idnum);
		if (n==0)
		{
			log.debug("삭제 실패");
			model.addAttribute("error","삭제 실패");
			return "deleteForm";
		}
		return "redirect:/selectAll"; //데이터를 가져간 상태로 selectAll을 호출 . selectAll만 적을시 되돌아갈떄 null인 상태로 돌아감 
	}
	
	//회원정보 수정 
	@GetMapping("update")
	public String update()
	{
		//수정 폼을 출력하고 주민번호, 이름 , 나이를 입력받는다.
		return "updateForm";
	}
	@GetMapping("updatePerson")
	public String updatePerson(String idnum ,Model model)
	{
		model.addAttribute("idnum", idnum);
		log.debug(idnum);
		return "updateForm";
	}
	
	@PostMapping("update")
	public String update(Person p)
	{
		//주민등록번호 기준으로 찾아서 이름과 나이 수정
		log.debug("조회:{}",p);
		service.updatePerson(p);
		
		if(p.getIdnum()==null) // 홈에서 수정을 눌렀을때 
		{
			return "redirect:/";
		}	
		else if (p.getIdnum()!=null) //회원목록에서 수정을 눌렀을 때 
		{
			return "redirect:/selectAll";
		}
		return "updateForm";
	}
	

	
	//한명검색
	@GetMapping("selectOne")
	public String selectOne(String idnum,Model model)
	{
		log.debug("전달된 주민등록번호 : {},idnum");
		Person p = service.selectOne(idnum);
		log.debug("조회결과 :{}",p);
		model.addAttribute("person",p);
		return "selectOne";
	}
	
	//모든 회원 조회 
	@GetMapping("selectAll")
	public String selectAll(Model model)
	{
		ArrayList<Person>list = service.selectAll();
		log.debug("조회 결과 : {}",list);
		model.addAttribute("list",list);
		return "selectAll";
	}
	
}
