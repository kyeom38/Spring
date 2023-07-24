package net.softsociety.spring5.service;

import net.softsociety.spring5.domain.Member;

public interface MemberService 
{

	public int joinMember(Member m);

	public boolean idcheck(String searchid);

	public Member select(String userInfo);

	public int update(Member m);
	
}
