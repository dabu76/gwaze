package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	// インデックス画面
	@GetMapping("/")
	public String index () {
		return "/index";
	}
	// ログインエラー時の画面
	@GetMapping("/errorPage")
	public String error() {
		return "/error";
	}
	// テスト時に使ったページ
	@ResponseBody
	@GetMapping("/test")
	public String test() {
		// /testページにアクセスすると
		return "testtestest";  // この文字が出力される
	}
}
