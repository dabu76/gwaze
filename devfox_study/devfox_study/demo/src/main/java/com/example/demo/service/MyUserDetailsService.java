package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{
	
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	
	//유저디테일이라는 스프링 문법을 통해 재정의 해서 user에 아이디랑 비밀번호를 불러와서
	//usermapper를 통해 아이디를 셀렉트문으로 가져오고 id pw를 다시 반환
	// UserDetailService라는 인터페이스에서 이 loadUserByUsername이란느 메서드를 
	// 재정의 해주었는데, 여기서 userMapper.findByUserName() 이라는 메서드를 통해
	// username일 입력받고, id와 pw를 반환함.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userMapper.findByUserName(username);
		
		
		
	}
	//위의 재정의된 유저디테일메서드에서 반환된 유저의정보와 내가입력한 패스워드가 일치하는지 확인하는 함수
	public boolean checkPassword(UserDetails user, String password) {
		return passwordEncoder.matches(password, user.getPassword());
	}
	
}
