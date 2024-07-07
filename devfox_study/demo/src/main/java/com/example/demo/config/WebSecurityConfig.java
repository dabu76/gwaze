package com.example.demo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.MyUserDetailsService;

import lombok.RequiredArgsConstructor;
//会員登録時にパスワードを暗号化するための設定
@Configuration
public class WebSecurityConfig {

	//Spring Securityでフィルターチェーンを構成するのに使用し。 このフィルタチェーンは着信するHTTP要求に対してセキュリティフィルタを適用する
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 

		
		http
		.csrf((csrf) -> csrf 
		.disable()) // フォーム値にクッキーとcsrfを同時に渡して値を確認しなければならないのにdisableでcsrf値は受けないことにする
				// csrfはウェブセキュリティ操作された情報でウェブサイトが実行されるように騙す攻撃技術
				// スプリングセキュリティは、このような攻撃を防止するため、CSRFトークンを発行し、フォーム転送時に当該トークンを一緒に転送しなければならない
				.authorizeHttpRequests((auth) -> auth
						.requestMatchers("/**")
						.permitAll() //全て許可
						.anyRequest().authenticated()//他のリクエストは認証された人のみ入れることができる
						);
				// 認証されていないすべてのページのリクエストを許可する
				// セキュリティを使用すると、すべてのページに認証をしなければならないが、/**でルート下のフォルダファイルを認証なしでアクセスできるように許可する
				
				http.formLogin((formLogin) -> formLogin
						.usernameParameter("userName") 
						.passwordParameter("password") 
						.loginPage("/login") // ポストマッピングのログインで渡す
						.failureUrl("/errorPage")//失敗時エラーページへ
						.defaultSuccessUrl("/")); // ログインに成功すると인덱스に移動
				
				// ログイン要請URLとログイン成功時にメインに移動する
				
				// ログアウト処理 ログアウト関数によってログアウトurlに渡し、成功するとインデックス
				http.logout((logout) -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true));
				// ログアウトユーザURIアドレスとログアウト成功時にメインに移動し、すべてのセッションを削除する。
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() { //パスワードを暗号化
		return new BCryptPasswordEncoder();
	}

	
	
	

}
