package net.softsociety.spring5.domain;

public class Reply 
{
	int reply;
	int boardnum;
	String memberid;
	String replytext;
	String inputdate;
	
	public int getReply() 
	{
		return reply;
	}
	
	public void setReply(int reply) 
	{
		this.reply = reply;
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
