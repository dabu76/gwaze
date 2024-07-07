package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.User;

//ユーザーメッパーxmlへ行く途中脚
@Mapper
public interface UserMapper {
	//参照型オブジェクトにファインドバイユーザーネーム内のユーザー情報を送信し、
	//マッパーからセレクト文で検索
	User findByUserName(String userName);
	
	//会員登録時にユーザー情報を送ってマッパーからインサート
	void insertUser (User user);
	
	
}
