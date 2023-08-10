package net.softsociety.exam.service;

import java.util.ArrayList;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

/** 
 * 게시글 관련 서비스
 */
public interface BoardService 
{

	public int write(Board b);

	public int update(Board b);

	public Board read(int boardnum);

	public int delete(Board board);

	public void insert(Reply r);

	public void deleteReply(int num);

	public ArrayList<Reply> rlist();

	public ArrayList<Board> list();

	public ArrayList<Board> select(Board b);

	public ArrayList<Board> search(Board b);

	

}
