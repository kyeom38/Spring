package net.softsociety.spring5.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Reply;

@Mapper
public interface BoardDAO 
{
	public int write(Board b);

	public Board selectM(String userInfo);

	public ArrayList<Board> blist(HashMap<String, String> map, RowBounds rb);

	public Board read(int boardnum);

	int uphits(int boardnum);

	public int update(Board b);

	public int delete(Board b);

	public int getTotal(HashMap<String,String>map);

	public int writeReply(Reply reply);

	public ArrayList<Reply> rlist(int boardnum);

	public Reply reply1(int reply);

	public int deleteReply(Reply r);
}
