package net.softsociety.spring3.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring3.domain.Person;

@Mapper 
public interface PersonDAO 
{
	//수정 삭제 입력은 무조건 int . 처리된 행의 갯수를 저장. 
	public int insert(Person p);

	public int delete(String idnum);

	public int delete2(String name);

	public int update(Person p);

	public Person selectOne(String idnum);
	
	public Person selectAll(Person p);
	
	public ArrayList<Person> selectAll();
}
