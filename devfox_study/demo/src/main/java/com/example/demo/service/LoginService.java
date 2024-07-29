package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.User;

import lombok.RequiredArgsConstructor;

//ログインコントローラでポストマッピングを通じて入力されたユーザー情報を通じて会員登録を入力させるサービス@Service
//ファイナルが付いたフィールドに依存性を注入して使用中の関数が下にあるフィールドがなければ
//動作しないように設計する用途
@Service
@RequiredArgsConstructor
public class LoginService {

	//パスワード暗号化フィールド
	private final PasswordEncoder pe;
	//ユーザー情報をUsermapperに渡すフィールド
	private final UserMapper userMapper;
	//유저를 찾아줄 매퍼를 설정해줌
	public User findUser (User user) {
		//ユーザーの中にあるユーザーIDを持ってきて、ユーザーメーパーを通じてセレクト文を通じてユーザーを検索していないと、そのままnullを受け取ることになる
		String ruser = user.getUsername();
		
	    return userMapper.findByUserName(ruser);
	}
	
	//ユーザー情報を受け取り、ユーザーはそのまま入力し、パスワードはパスワードインコードで暗号化させ
	//これらを保存してテーブルに保存させる
	public void join(User user) {
		String encodedPassword =  pe.encode(user.getPassword());

		user.setPassword(encodedPassword);
		userMapper.insertUser(user);
		
	}
	
}
