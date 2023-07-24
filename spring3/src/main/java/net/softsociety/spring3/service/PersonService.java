package net.softsociety.spring3.service;

import java.util.ArrayList;

import net.softsociety.spring3.domain.Person;

/*
 * 회원 정보 처리 서비스
 */
public interface PersonService 
{
	/*
	 * 회원정보 저장기능
	 * @param p 저장할 회원 정보
	 * @return 저장한 행 개수
	 */
	public int insertPerson(Person p);
	
	/*
	 * 회원정보 삭제 기능 - 주민번호로 삭제 
	 * @param idnum 삭제할 회원의 주민등록번호
	 * @return 삭제된 행 개수
	 */
	public int deletePerson(String idnum);
	/*
	 * 회원정보 삭제 기능 --이름으로 삭제
	 * @param idnum 삭제할 이름
	 * @return 삭제된 행 개수
	 */
	public int delete2Person(String name);

	/*
	 * 주민등록번호로 회원정보 조회
	 * @param idnum 조회할 주민등록번호
	 * @return 회원정보 검색결과. 없으면 null
	 */
	public int updatePerson(Person p);

	public Person selectOne(String idnum);
	
	public ArrayList<Person> selectAll();
}
