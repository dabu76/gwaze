package com.example.demo.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	//ログインjspでIDパスワードをリクエストパラムで受け取り、ユーザーディテールズというセキュリティインターフェースに入れて
	// login.jspでアディイとパスワードをリクエストしてもらい、detailServiceのloadUserByUsernameという
	// 関数を呼び出す
	@PostMapping("/login") // @Request Param ではこれは要請されたパラメータ値を定義してusernameとpasswordを送ってくれる
	public String procLogin(@RequestParam String username, @RequestParam String password) {
		//User Details ServiceはSpring Securityでユーザー認証に使用情報をロードするインターフェースで、
		//それをディテールサービスにあるID検索するマッパーを通じてIDを検索してくれる
		UserDetails userDetails = detailService.loadUserByUsername(username);
		//テーブルの中にあるユーザー情報と自分が入力したユーザー情報が一致した場合、インデックスページにリダイレクトする
		//もし一致しないなら文法上あなたを返してくれる（stringでもらったので必ず返してくれなければならないので）
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
		// 受信したユーザー値を比較してできるように変数を作り
		 User ruser  =  loginservice.findUser(user);
		//もし受け取ったユーザー値がない場合はエラーページへ移動
		if(ruser != null) {

			return "/joinerror";
		}else {
			//ログインサービスにあるjoinメソッドを実行した後にloginページに戻らせる
		loginservice.join(user);
		return "redirect:/login";

		}
	

	}
	@GetMapping("/joinerror")
	public String jounErrir() {
		return "/joinerror";
	}

}
