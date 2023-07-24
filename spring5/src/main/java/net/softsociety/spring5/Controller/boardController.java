package net.softsociety.spring5.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Provider.Service;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring5.domain.Board;
import net.softsociety.spring5.domain.Member;
import net.softsociety.spring5.domain.Reply;
import net.softsociety.spring5.service.BoardService;
import net.softsociety.spring5.service.MemberService;
import net.softsociety.spring5.util.FileService;
import net.softsociety.spring5.util.PageNavigator;


@Slf4j
@RequestMapping("board")
@Controller
public class boardController 
{
	@Autowired
	BoardService bservice;
	
	@Autowired
	MemberService mservice;
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	//게시판 목록의 페이지당 글 수 
	@Value("${user.board.page}")
	int countPerPage;
	
	//게시판 목록의 페이지 이동 링크 수 
	@Value("${user.board.group}")
	int pagePerGroup;
	
	//홈에서 게시판(글목록)이동
	@GetMapping("board")
	public String blist(
			Model model
			, String type
			, String searchWord
			,@RequestParam(name="page",defaultValue="1") int page
			) 
	{	
		log.debug("검색대상:{},검색어:{},페이지:{}",type, searchWord,page);
		
		PageNavigator navi = bservice.getPageNavigator(pagePerGroup,countPerPage,page,type,searchWord);
		ArrayList<Board> blist = bservice.blist(navi,type, searchWord);
		model.addAttribute("board", blist);
		model.addAttribute("navi", navi);
		model.addAttribute("type", type);
		model.addAttribute("searchWord", searchWord);
	
		return "boardView/board";
	}
	
	//글쓰기로 이동
	@GetMapping ("write")
	public String write(@AuthenticationPrincipal UserDetails user,Model model)
	{
		//로그인한 아이디로 회원정보 검색
		String userInfo = user.getUsername();
		Member m = mservice.select(userInfo);
		log.debug("아이디:{}",user.getUsername());
		//검색 결과를 Model에 저장 
		model.addAttribute("m", m);
		
		return "boardView/write";
	}
	
	//글쓰기
	@PostMapping("write")
	public String write(@AuthenticationPrincipal UserDetails user,
						Board b, MultipartFile upload)
	{
		log.debug("저장할 글 정보 : {}",b);
		log.debug("파일정보 :{}",upload);
		if(upload != null && !upload.isEmpty())
		{
			String savedfile=FileService.saveFile(upload,uploadPath);
			b.setOriginalfile(upload.getOriginalFilename());
			b.setSavedfile(savedfile);
		}
		//로그인 한 아이디를 board 객체에 추가
		b.setMemberid(user.getUsername());
		//board객체를 서비스로 전달하여 DB에 추가
		int result = bservice.write(b);  //삭제 수정은 되도록 int로 만들 것 . 
		return "redirect:/board/board";
	}
	
	//글읽기 
	@GetMapping("read")
	public String read(@RequestParam(name="boardnum", defaultValue="0") int boardnum, Model model)
	{
		//게시글 내용
		Boar d b = bservice.read(boardnum);
		log.debug("{}",b);
		if(b==null)
		{
			return "redirect:/board/board";// 글이없으면 목록으로
		}
		model.addAttribute("b", b);
		
		//리플 내용 출력
		ArrayList<Reply>rlist=bservice.rlist(boardnum);
		model.addAttribute("reply",rlist);
		
		return "boardView/read";
	}
	
	//글수정 폼으로 이동 HTML에서 이미 글번호가 전달되어서 따로 글하나의 정보를 가져올 필요가 없음? 
	@GetMapping("update")
	public String update(@RequestParam(name="boardnum",defaultValue="0")int boardnum,Model model)
	{
		Board b = bservice.read(boardnum);
		log.debug("뭐얏:{}",b);
		model.addAttribute("board", b);
		return "boardView/updateForm";
	}
	
	//글수정 
	@PostMapping("update")
	public String update2(Board b
			,@AuthenticationPrincipal UserDetails user
			,@RequestParam("upload") MultipartFile upload)
	{
		log.debug("수정할 글 정보 :{}",b);
		//작성자 아이디 추가 (수정정보를 지닌 변수에)
		b.setMemberid(user.getUsername());
		//업데이트 이전 기존 게시판 정보 호출
		Board oldb = bservice.read(b.getBoardnum());
		log.debug("보드확인 :{}",upload);
		
		//첨부파일 수정시 
		if (upload !=null && !upload.isEmpty())
		{
			if(oldb.getOriginalfile()!=null && oldb.getSavedfile()!=null)
			{
				FileService.deleteFile(uploadPath+"/"+ oldb.getSavedfile()); // 폴더경로 + 파일의 이름 
			}
			String savedfile = FileService.saveFile(upload, uploadPath);
			b.setOriginalfile(upload.getOriginalFilename());
			b.setSavedfile(savedfile);
		}
		else 
		{
			b.setOriginalfile(oldb.getOriginalfile());
			b.setSavedfile(oldb.getSavedfile());
		}
		
		//글 수정
		int result = bservice.update(b);
	
		return "redirect:/board/read?boardnum="+b.getBoardnum(); 
	}
	
	//글삭제
	@GetMapping("delete")
	public String delete(int boardnum,@AuthenticationPrincipal UserDetails user, MultipartFile upload)
	{
		//해당 번호의 글 정보 조회
		Board b =bservice.read(boardnum);
		
		log.debug("로그: {}",b);
		if (upload !=null && !upload.isEmpty())
		{
			if(b.getOriginalfile()!=null && b.getSavedfile()!=null)
			{
				FileService.deleteFile(uploadPath+"/"+ b.getSavedfile()); // 폴더경로 + 파일의 이름 
			}
		}
		//로그인 아이디를 board객체에 저장
		b.setMemberid(user.getUsername());
		
		//글삭제 
		int result = bservice.delete(b);
		return "redirect:board"; //넘어가기 전 페이지의 경로와 같은 위치(?)일 경우 
	}

	//파일 다운로드
	@GetMapping("download")
	public void download(int boardnum
			,HttpServletRequest request
			,HttpServletResponse response) 
	{
		log.debug("{}",request.getRemoteAddr()); //요청을 보낸쪽의 ip. 문자열 
		//해당 글의 첨부 파일명 확인 
		Board board = bservice.read(boardnum);
		//파일의 경로를 이용해서 FileInputStream 객체를 생성 
		String fullPath = uploadPath + "/" + board.getSavedfile();
		//response를 통해 파일 전송 
		try 
		{
	         response.setHeader("Content-Disposition", " attachment;filename="+ URLEncoder.encode(board.getOriginalfile(), "UTF-8"));
	    } 
		catch (UnsupportedEncodingException e) 
		{
	         e.printStackTrace();
	    }
		try 
		{
			FileInputStream in = new FileInputStream(fullPath);
			  ServletOutputStream out = response.getOutputStream();
			  
			  FileCopyUtils.copy(in, out);
			  
			  in.close();
			  out.close();
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//리플 저장
	@PostMapping("writeReply")
	public String writeReply(
			@AuthenticationPrincipal UserDetails user
			,Reply reply)
	{
		//전달된 본물글 번호, 리플내용에 로그인아이디 추가
		reply.setMemberid(user.getUsername());
		//DB를 전달하여 저장
		int result = bservice.writeReply(reply);
		//읽던 글로 이동 
		return "redirect:/board/read?boardnum=" + reply.getBoardnum();
	}
	
	//리플 삭제
	@GetMapping("deleteReply")
	public String deleteReply(
			@AuthenticationPrincipal UserDetails user
			,int reply,int boardnum
			)
	{
		//해당 댓글번호로 댓글정보가져오기
		Reply r = new Reply();
		//로그인아이디를 reply객체에 저장 
		r.setMemberid(user.getUsername());
		//댓글 번호 집어넣기
		r.setReply(reply);
		//댓글 삭제 
		int n = bservice.deleteReply(r);
		
		return "redirect:/board/read?boardnum=" + boardnum;
	}
	
	
	//리플 수정
	@PostMapping("updateR")
	public String updateR(
			@AuthenticationPrincipal UserDetails user
			,Reply r , int boardnum
			)
	{
		//해당 댓글번호로 댓글정보 가져오기
		r = new Reply();
		//로그인 아이디를 reply객체에 저장 
		r.setMemberid(user.getUsername());
		//댓글 번호 집어넣기
		r.setReply(r.getReply());
		//댓글 수정 
		
		return "redirect:/board/read?boardnum=" + r.getBoardnum();
	}
	
}
