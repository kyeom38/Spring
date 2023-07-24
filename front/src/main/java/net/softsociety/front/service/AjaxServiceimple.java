package net.softsociety.front.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.front.dao.AjaxDAO;
import net.softsociety.front.service.AjaxServiceimple;

@Transactional //항상 세트로 실행이되도록 . 
@Slf4j
@Service
public class AjaxServiceimple implements AjaxService
{
	@Autowired
	private AjaxDAO dao;

	@Override
	public int updateCnt(int boardnum) 
	{
		return   dao.updateCnt(boardnum);
	}

	@Override
	public int selectCnt(int boardnum) 
	{
	    return dao.selectCnt(boardnum);
	}
	   
	@Override
	public int idcheck2(String memberid) 
	{
		int n = dao.idcheck2(memberid);
		return n;
	}
	
	

}
