package net.softsociety.spring4.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.spring4.dao.GuestBookDAO;
import net.softsociety.spring4.domain.GuestBook;
import net.softsociety.spring4.domain.Member;

@Service
public class GuestBookServiceimple implements GuestBookService 
{

	@Autowired
	GuestBookDAO dao;
	
	
	@Override
	public int writeGB(GuestBook g) 
	{
		int n = dao.writeGB(g);
		return n;
	}
	
	@Override
	public ArrayList<GuestBook> readGB() 
	{
		ArrayList<GuestBook> readGB = dao.readGB();
		return readGB;
	}

	@Override
	public int deleteGB(GuestBook g) 
	{
		int n = dao.deleteGB(g);
		return n;
	}

	@Override
	public int reviseGB(GuestBook g) 
	{
		int n = dao.reviseGB(g);
		return n;
	}

	@Override
	public GuestBook selectOne(GuestBook g) 
	{
		GuestBook a = dao.selectOne(g);
		return a;
	}

	@Override
	public int upvote(int num) 
	{
		int n = dao.upvote(num);
		return n;
	}

	@Override
	public int downvote(int num) 
	{
		int n = dao.downvote(num);
		return n;
	}


}
