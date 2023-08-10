package net.softsociety.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 리플 정보
 */
public class Reply 
{
	int replynum;
	int boardnum;
	String memberid;
	String replytext;
	String inputdate;
	
	public int getReply() 
	{
		return replynum;
	}
	
	public void setReply(int reply) 
	{
		this.replynum = reply;
	}
	
	public int getBoardnum() 
	{
		return boardnum;
	}
	
	public void setBoardnum(int boardnum) 
	{
		this.boardnum = boardnum;
	}
	
	public String getMemberid() 
	{
		return memberid;
	}
	
	public void setMemberid(String memberid) 
	{
		this.memberid = memberid;
	}
	
	public String getReplytext() 
	{
		return replytext;
	}
	
	public void setReplytext(String replytext) 
	{
		this.replytext = replytext;
	}

	public String getInputdate() 
	{
		return inputdate;
	}

	public void setInputdate(String inputdate) 
	{
		this.inputdate = inputdate;
	}
	
}
