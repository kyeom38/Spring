package net.softsociety.spring4.service;

import net.softsociety.spring4.domain.Member;

public interface MemberService 
{

	public int joinMember(Member m);

	public boolean idcheck(String searchid);

	public Member select(String userInfo);

	int update(Member m);
	
}
