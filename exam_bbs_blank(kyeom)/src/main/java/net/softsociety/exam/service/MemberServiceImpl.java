package net.softsociety.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.dao.MemberDAO;
import net.softsociety.exam.domain.Member;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService 
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
}
