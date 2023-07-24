package net.softsociety.test3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class AddInfo 
{
	int num;
	String name;
	String address;
	String post;
	String phone;
}
