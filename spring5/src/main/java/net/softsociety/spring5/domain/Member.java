package net.softsociety.spring5.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member implements UserDetails
{
	String memberid;
	String memberpw;
	String membername;
	String email;
	String phone;
	String address;
	boolean enabled;
	String rolename;
	
	//회원의 권한. role이 여러개인경우
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	//비밀번호가 필요할 떄 
	@Override
	public String getPassword() 
	{
		return this.memberpw;
	}
	
	//회원정보가 필요할 때 
	@Override
	public String getUsername() 
	{
		return this.memberid;
	}
	
	@Override
	public boolean isAccountNonExpired() 
	{
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() 
	{
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() 
	{
		// TODO Auto-generated method stub
		return true;
	}
}
