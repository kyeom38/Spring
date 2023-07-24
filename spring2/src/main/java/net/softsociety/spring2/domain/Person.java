package net.softsociety.spring2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //toString ,Getter ,Setter 등 자동으로 만들어줌
@AllArgsConstructor //모든 멤버변수의 값을 전달받아 초기화하는 생성자의 정의
@NoArgsConstructor // 기본 생성자 정의 
public class Person 
{
	String name;
	int age;
	String phone;
}
