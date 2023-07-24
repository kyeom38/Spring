package net.softsociety.spring3.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.spring3.dao.PersonDAO;
import net.softsociety.spring3.domain.Person;

@Service //없으면 컨트롤러에서 객체를 받아올 수 없음.
public class PersonServiceImple implements PersonService {

	@Autowired
	PersonDAO dao;
	
	@Override
	public int insertPerson(Person p) 
	{
		int n = dao.insert(p);
		return 0;
	}

	@Override
	public int deletePerson(String idnum) 
	{
		int n = dao.delete(idnum);
		return n;
	}

	@Override
	public int delete2Person(String name) 
	{
		int n = dao.delete2(name);
		return n;
	}

	@Override
	public int updatePerson(Person p) {
		int n = dao.update(p);
		return n;
	}

	@Override
	public Person selectOne(String idnum) {
		Person p = dao.selectOne(idnum);
		return p;
	}

	@Override
	public ArrayList<Person> selectAll() 
	{
		ArrayList<Person> list = dao.selectAll();
		return list;
	}
	
	

}
