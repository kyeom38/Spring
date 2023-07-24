package net.softsociety.spring4.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring4.domain.GuestBook;
import net.softsociety.spring4.domain.Member;

@Mapper
public interface GuestBookDAO 
{
	public int writeGB(GuestBook g);

	public ArrayList<GuestBook> readGB();

	public int deleteGB(GuestBook g);

	public int reviseGB(GuestBook g);

	public GuestBook selectOne(GuestBook g);

	public int upvote(int num);

	public int downvote(int num);
}
