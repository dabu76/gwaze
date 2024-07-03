package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.User;

//유저 메퍼xml로 가는 중간다리
@Mapper
public interface UserMapper {
	//참조형객체에 파인드바이 유저네임안의 유저정보를 보내서 매퍼에서 셀렉트 문으로 검색
	User findByUserName(String userName);
	
	//회원가입할때 유저 정보를 보내서 매퍼에서 인서트
	void insertUser (User user);
	
	
}
