package net.softsociety.spring4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring4.dao.MemberDAO;
import net.softsociety.spring4.domain.Member;

@Slf4j
@Service
public class MemberServiceimple implements MemberService 
{
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int joinMember(Member m) 
	{
		String pw=passwordEncoder.encode(m.getMemberpw());
		log.debug("암호화 전 : {}",m.getMemberpw());
		log.debug("암호화 후 : {}",pw);
		
		m.setMemberpw(pw);
		
		int n = dao.joinMember(m);
		return n;
	}

	@Override
	public boolean idcheck(String searchid) 
	{
		return dao.selectOne(searchid)==null;
	}

	@Override
	public Member select(String userInfo) 
	{
		return dao.selectOne(userInfo);
	}

	@Override
	public int update(Member m) 
	{
		if(!m.getMemberpw().isEmpty()) 
		 {
		      String pw = passwordEncoder.encode(m.getMemberpw());
		      m.setMemberpw(pw);
		 }
		 int n = dao.update(m);
		return n;
	}


}
