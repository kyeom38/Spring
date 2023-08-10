package net.softsociety.exam.controller;

import java.lang.reflect.Member;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.dao.BoardDAO;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;
import net.softsociety.exam.service.BoardService;
import net.softsociety.exam.service.MemberService;




@Slf4j
@RequestMapping("board")
@Controller
public class BoardController 
{
	@Autowired
	BoardService service;
	
	@Autowired
	BoardDAO dao;
	
	//게시판 리스트  
	@ResponseBody
	@GetMapping("board")
	public ArrayList<Board> list() 
	{
        ArrayList<Board> list = service.list();
        return list;
    }
	
	//글쓰기로 이동
	@GetMapping ("write")
	public String write()
	{	
		return "board/write";
	}
	
	//글쓰기
	@PostMapping("write")
	public String write(@AuthenticationPrincipal UserDetails user,Board b)
	{
		b.setMemberid(user.getUsername());

		int result = service.write(b);
		return "redirect:/board/board";
	}
	
	//카테고리별로 보기 (잡담/취미/공지+ )
    @GetMapping("select")
    public String select(@RequestParam(value="category") String category) 
    {
  
        Board b = new Board();
       
    	ArrayList<Board> list = service.select(b);
        return "board/board"; 
    }
	
	//게시판 검색 제목/내용/제목+내용
	@ResponseBody
	@PostMapping("search")
	public ArrayList<Board> search(@RequestParam(value = "filter") String filter)
	{
		Board b = new Board();
		
		ArrayList<Board> list = service.search(b);
		return list;
	}
	
	
	//글읽기
	@GetMapping("read")
	public String read(
			@RequestParam(name="boardnum", defaultValue = "0") int boardnum
			, Model model
			, Board b) {
		b = service.read(boardnum);
		model.addAttribute("list",b);
		return "board/read";
	}
	
	//추천수올리기 
	@ResponseBody
	@PostMapping({"recommend"})
	public int recommend(int boardnum)
	{
		int cnt=0;
		dao.updateCnt(boardnum);
		return cnt;
	}
	
	//리플 달기
	@PostMapping("insert")
	public void insert(Reply r)
	{
		service.insert(r);
	}
	//리플 리스트
	@GetMapping("list")
	public ArrayList <Reply> rlist()
	{
		ArrayList<Reply> rlist = service.rlist();
		return rlist;
	}
	//리클 삭제 
	@PostMapping("deleteReply")
	public void deleteReply(int num)
	{
		service.deleteReply(num);
	}
	
	//글수정 폼으로 이동
	@GetMapping("update")
	public String update(@RequestParam(name="boardnum",defaultValue="0")int boardnum,Model model)
	{
		Board b = service.read(boardnum);
		log.debug("수정폼에서:{}",b);
		model.addAttribute("b", b);
		return "board/update";
	}
	
	//글수정 
	@PostMapping("update")
	public String update2(Board b
			,@AuthenticationPrincipal UserDetails user)
	{
		log.debug("수정할 글 정보 :{}",b);
		//작성자 아이디 추가 (수정정보를 지닌 변수에)
		b.setMemberid(user.getUsername());
		//업데이트 이전 기존 게시판 정보 호출
		Board oldb = service.read(b.getBoardnum());
		
		//글 수정
		int result = service.update(b);
	
		return "redirect:/board/read?boardnum="+b.getBoardnum(); 
	}
	
	//글삭제 
	@GetMapping ("delete")
	public String delete(int boardnum, @AuthenticationPrincipal UserDetails user,Board board) {
		board.setBoardnum(boardnum);
		board.setMemberid(user.getUsername());

		int result = service.delete(board);
		return "redirect:/board/board";
	}
}
