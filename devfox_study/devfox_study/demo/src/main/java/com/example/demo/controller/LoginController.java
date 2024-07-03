package com.example.demo.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.LoginService;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.vo.User;

import lombok.RequiredArgsConstructor;

//jspの名前と住所が一致する時、その画面に移るようにしてくれるコントローラー
@Controller
@RequiredArgsConstructor
public class LoginController {
	
	//シングルターンパターンを利用して接続するために、箱と依存性を注入して使用しなければならないため、privateとfinalを使用して再定義する
	private final LoginService loginservice;
	private final MyUserDetailsService detailService;
	
	//ログイン画面
	@GetMapping("/login")
	public String login() {
		
		return "/login";
	}
	//로그인 jsp에서 아이디 비밀번호를 리퀘스트 파람으로 받아서 유저디테일스라는 시큐리티 인터페이스에 담아서 
	// login.jsp에서 아디이와 비밀번호를 리퀘스트파람으로 받아서, detailService의 loadUserByUsername이라는 
	// 함수를 호출
	@PostMapping("/login")
	public String procLogin(@RequestParam String username, @RequestParam String password) {
		UserDetails userDetails = detailService.loadUserByUsername(username);
		//테이블 안에 있는 유저 정보와 내가 입력한 유저정보가 일치할 경우 인덱스 페이지로 리다이렉트 한다
		//만약 일치하지 않다면 문법상 널을 반환해준다(string 로 받았으므로 반드시 반환해줘야하므로)
		if (userDetails != null && detailService.checkPassword(userDetails, password)) {
			return "redirect:/";
		} else {
			return null;
		}
	}
	//会員登録画面
	@GetMapping("/join")
	public String join() {
		
		return "/join";
	}
	//会員登録に情報を入力した時にIDとパスワードの情報を受け取りログインサービスに送る
	@PostMapping("/join")
	public String procJoin(User user) {
		
		loginservice.join(user);
		
		return "redirect:/login";

	}

}
