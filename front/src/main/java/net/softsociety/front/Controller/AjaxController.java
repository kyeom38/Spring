package net.softsociety.front.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.front.dao.AjaxDAO;
import net.softsociety.front.domain.Person;

@Slf4j
@RequestMapping("aj")
@Controller
public class AjaxController 
{
	
	
	@GetMapping({"aj1"})
	public String aj1()
	{
		return "aj1";
	}
	
	@ResponseBody
	@GetMapping({"test1"})
	public void test1()
	{
		log.debug("test1 실행");
	}
	
	@ResponseBody
	@PostMapping({"test2"})
	public void test2(String str)
	{
		log.debug("test2로 전달된 값:{}",str);
	}
	
	@ResponseBody
	@GetMapping({"test3"})
	public String test3()
	{
		log.debug("test3 실행");
		return "서버문자열";
	}
	
	@ResponseBody
	@PostMapping({"test4"})
	public String test4(String str)
	{
		log.debug("test4 실행");
		String string2=str.toUpperCase();
		return string2;
	}
	
	@GetMapping({"aj2"})
	public String aj2()
	{
		return "aj2";
	}
	
	@ResponseBody
	@PostMapping("insert1")
	public void insert1(String name, int age, String phone)
	{
		log.debug("이름:{},나이:{},전화:{}",name,age,phone);
	}
	
	@ResponseBody
	@PostMapping("insert2")
	public void insert2(Person p)
	{
		log.debug("전달된 값:{}",p);
	}
	
	@ResponseBody
	@PostMapping("insert3")
	public void insert3(Person p)
	{
		log.debug("전달된 값:{}",p);
	}
	
	@ResponseBody
	@GetMapping("getObject1")
	public Person getObject1()
	{
		Person p =new Person("홍길동",22,"010-1111-1111");
		return p;
	}
	
	@ResponseBody
	@GetMapping("getObject2")
	public Person getObject2()
	{
		Person p =new Person("홍길동",22,"010-1111-1111");
		return p;
	}
	
	@ResponseBody
	@GetMapping("getObject3")
	public ArrayList<Person> getList()
	{
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person("aaa",10,"010-1111"));
		list.add(new Person("aaa",20,"010-2222"));
		list.add(new Person("aaa",10,"010-3333"));
		return list;
	}
	
	@ResponseBody
	@PostMapping("sendArray")
	public void sendArray(String []ar)
	{
		if(ar==null)
		{
			log.debug("ar:null");
		}
		else 
		{
			for (String s : ar)
			{
				log.debug("배열요소:{}",s);
			}
		}
	}
	
	@ResponseBody
	@PostMapping("sendObjectArray")
	public void sendObjectArray(String ar) throws Exception 
	{
		if(ar == null) 
		{
			log.debug("ar:null");
			return;
		}
		log.debug("배열 요소: {}", ar);
		//[{"name":"aaa","age":11,"phone":"1111"},{"name":"bbb","age":22,"phone":"2222"}]
		
		
		//파싱 JSON에서 받아온 값을 자바의 객체로 변환?
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Person> list = objectMapper.readValue(ar, new TypeReference<ArrayList<Person>>() {});
		log.debug("변환결과 리스트: {}",list);
		
		for (Object ob: list) 
		{
			log.debug("요소타입: {}", ob.getClass());
			log.debug("요소값: {}", ob);
		}
			
	}
}

