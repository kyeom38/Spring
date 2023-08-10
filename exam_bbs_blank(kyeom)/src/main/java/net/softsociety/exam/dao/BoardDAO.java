package net.softsociety.exam.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

@Mapper
public interface BoardDAO 
{

	int write(Board b);

	int update(Board b);

	Board read(int boardnum);

	int uphits(int boardnum);

	int delete(Board board);

	void updateCnt(int boardnum);

	void insert(Reply r);

	void deleteReply(int num);

	ArrayList<Reply> rlist();

	ArrayList<Board> list();

}
