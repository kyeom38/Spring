package net.softsociety.exam.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.softsociety.exam.dao.BoardDAO;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

@Service
public class BoardServiceimpl implements BoardService 
{
	@Autowired
	BoardDAO dao;
	 
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public int write(Board b) 
	{
		int n = dao.write(b);
	    return n;
	}

	@Override
	public int update(Board b) 
	{
		int n = dao.update(b);
		return n;
	}

	@Override
	public Board read(int boardnum) 
	{
		//조회수 1증가 
		int n = dao.uphits(boardnum);
		Board b = dao.read(boardnum);
		
		return b;
	}

	@Override
	public int delete(Board board) 
	{
		int n = dao.delete(board);
        return n;
	}

	@Override
	public void insert(Reply r) 
	{
		dao.insert(r);
	}

	@Override
	public void deleteReply(int num) 
	{
		dao.deleteReply(num);
	}

	@Override
	public ArrayList<Reply> rlist() 
	{
		return dao.rlist();
	}

	@Override
	public ArrayList<Board> list() 
	{
		 ArrayList<Board> list = dao.list();
	     return list;
	}

	@Override
	public ArrayList<Board> select(Board b) 
	{
		ArrayList<Board> list = dao.list();
	     return list;
	}

	@Override
	public ArrayList<Board> search(Board b) 
	{
		ArrayList<Board> list = dao.list();
	     return list;
	}
}
