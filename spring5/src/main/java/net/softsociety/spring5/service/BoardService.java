package net.softsociety.spring5.service;

import java.util.ArrayList;

import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.util.PageNavigator;

public interface BoardService 
{

	public int write(Board b);
	
	//글목록 
	public ArrayList<Board> blist(PageNavigator navi, String type, String searchWord);
	
	//페이지 정보 
	public PageNavigator getPageNavigator(int pagePerGroup, int countPerpage, int page, String type, String searchWord);

	public Board read(int boardnum);

	public int delete(Board b);

	public int update(Board b);

	public int writeReply(Reply reply);

	public ArrayList<Reply> rlist(int boardnum);

	public Reply reply1(int reply);

	public int deleteReply(Reply r);

}
