package net.softsociety.spring4.service;

import java.util.ArrayList;

import net.softsociety.spring4.domain.GuestBook;
import net.softsociety.spring4.domain.Member;

public interface GuestBookService 
{ 
	public int writeGB(GuestBook g);

	public ArrayList<GuestBook> readGB();

	public int deleteGB(GuestBook g);

	public int reviseGB(GuestBook g);

	public GuestBook selectOne(GuestBook g);

	public int upvote(int num);

	public int downvote(int num);

}
