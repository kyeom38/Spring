package net.softsociety.spring4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestBook 
{
	int num;
	String name;
	String password;
	String message;
	String inputdate; 
	int upvote;
	int downvote;
}
