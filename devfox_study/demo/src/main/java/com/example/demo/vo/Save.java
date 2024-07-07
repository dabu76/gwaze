package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ゲッターとセッターなどをする時にデータで一度に保存してくれるlombokのアノテーション
@Data
//媒介変数のない基本生成子を自動で生成してくれるアノテーション
@NoArgsConstructor
//すべてのフィールドを媒介変数として受け取る生成者を自動的に生成するアノタイション
@AllArgsConstructor
public class Save { //投稿に必要な変数を保存するクラス
	int post_id;
	String title;
	String content;
	int user_id;
	String username;
	Date created_at;
	Date updated_at;
}
