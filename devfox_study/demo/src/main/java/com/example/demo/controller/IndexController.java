package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	// 인덱스 화면
	@GetMapping("/")
	public String index () {
		return "/index";
	}
	//로그인 에러시 화면
	@GetMapping("/errorPage")
	public String error() {
		return "/error";
	}
	
	@ResponseBody
	@GetMapping("/test")
	public String test() {
		return "testtestest";
	}
}
