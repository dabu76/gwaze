package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;

import lombok.RequiredArgsConstructor;

//로그인 컨트롤러에서 포스트매핑을 통해 입력받은 유저정보를 통해 회원가입입력시켜주는 해주는서비스
@Service
//파이널이 붙은 필드에 의존성을 주입해서 사용중인 함수들이 아래에있는 필드들이 없으면
//동작되지 않게끔 설계하는 용도
@RequiredArgsConstructor
public class LoginService {

	//패스워드 암호화 필드
	private final PasswordEncoder pe;
	//유저정보를 Usermapper에 넘겨주는 필드
	private final UserMapper userMapper;
	
	//유저정보를 받아서 유저는 그대로 입력하고 패스워드는 페스워드 인코드를 통해 암호화 시켜주고
	//이것들을 저장해서 테이블에 저장시켜줌
	public void join(User user) {
		String encodedPassword =  pe.encode(user.getPassword());

		user.setPassword(encodedPassword);
		userMapper.insertUser(user);
		
	}
	
}
