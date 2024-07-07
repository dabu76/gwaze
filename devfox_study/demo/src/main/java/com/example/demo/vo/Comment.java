package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {//コメントに必要な変数を保存するクラス
	int comment_id;
	String comment_content;
	Date created_at;
	int post_id;
	int user_id;
	String comment_username;
	String username;
}
