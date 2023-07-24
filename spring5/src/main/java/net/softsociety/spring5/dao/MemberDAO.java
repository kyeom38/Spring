package net.softsociety.spring5.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring5.domain.Member;

@Mapper
public interface MemberDAO 
{
	//회원정보 저장
	public int joinMember(Member m);
	//1명의 회원정보 조회
	public Member selectOne(String id);
	//개인정보 수정 
	public int update(Member m);
}
