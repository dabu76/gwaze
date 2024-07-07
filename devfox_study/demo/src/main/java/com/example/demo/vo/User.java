package com.example.demo.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//スプリングセキュリティからユーザーの情報を取得する用途に使われるVo
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
	
	private Long userId;
	private String userName;
	private String password;
	
	//ユーザーディテールズというインターフェースで関数の再定義を強制するために宣言
	//ユーザーネームとパスワードをthisを通じて取得できるようにする
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
