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
	
	//ユーザーディテールというスプリング文法を通して再定義してuserにIDとパスワードを読み込んで
	//usermapperを通じてIDをセレクト文にインポートし、idpwを再返却
	// UserDetailServiceというインターフェースで、このloadUserByUsernameというメソッドを
	// 再定義してくれたが、ここでuserMapper.findByUserName()というメソッドを通じて
	// username日の入力を受けて、idとpwを返す
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userMapper.findByUserName(username);
		
		
		
	}
	//上記の再定義されたユーザディテールメソッドから返還されたユーザの情報と自分が入力したパスワードが一致することを確認する関数
	public boolean checkPassword(UserDetails user, String password) {
		return passwordEncoder.matches(password, user.getPassword());
	}
	
}
