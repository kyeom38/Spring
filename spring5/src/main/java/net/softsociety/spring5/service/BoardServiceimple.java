package net.softsociety.spring5.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.dao.BoardDAO;
import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.util.PageNavigator;

@Transactional //항상 세트로 실행이되도록 . 
@Slf4j
@Service
public class BoardServiceimple implements BoardService 
{
	@Autowired
	BoardDAO dao;
	
	@Override
	public int write(Board b) 
	{
		int n = dao.write(b);
		return n;
	}

	//페이지 글목록 
	@Override
	public ArrayList<Board> blist(PageNavigator navi,String type, String searchWord) 
	{
		HashMap <String,String> map=  new HashMap<>(); 
		map.put("type",type);
		map.put("searchWord",searchWord);
		
		RowBounds rb = new RowBounds(navi.getCurrentPage(),navi.getCountPerPage()); 
		ArrayList<Board> b =dao.blist(map,rb);
		
		return b;
	}
	
	//페이지 정보 
	@Override
	public PageNavigator getPageNavigator(int pagePerGroup, int countPerpage, int page, String type,
			String searchWord) 
	{
		//검색할 정보
		HashMap <String,String> map=  new HashMap<>(); 
		map.put("type",type);
		map.put("searchWord",searchWord);
		
		int total = dao.getTotal(map);
		
		PageNavigator navi = new PageNavigator(pagePerGroup,countPerpage,page,total);
		
		return navi;
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
	public int update(Board b) 
	{
		int n = dao.update(b);
		return n;
	}

	@Override
	public int delete(Board b) 
	{
		int n = dao.delete(b);
		return n;
	}

	@Override
	public int writeReply(Reply reply) 
	{
		int n = dao.writeReply(reply);
		return n;
	}

	@Override
	public ArrayList<Reply> rlist(int boardnum) 
	{
		ArrayList<Reply> rlist = dao.rlist(boardnum);
		return rlist;
	}

	@Override
	public Reply reply1(int reply)
{
		Reply r = dao.reply1(reply);
		return r;
	}

	@Override
	public int deleteReply(Reply r) 
	{
		int n = dao.deleteReply(r);
		return n;
	}



}
