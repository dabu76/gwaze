package com.example.demo.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

//스프링 시큐리티에서 유저의 정보들을 가져오는 용도로 쓰이는 Vo

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
	
	private Long userId;
	private String userName;
	private String password;
	
	//유저디테일스라는 인터페이스에서 함수의 재정의를 강제하기위해 선언 
	//유저네임과 패스워드를 this를 통해서 가져올수 있게함
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
	
}
