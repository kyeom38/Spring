package net.softsociety.spring3.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Person 
{
	String idnum;
	String name;
	int age;
}
