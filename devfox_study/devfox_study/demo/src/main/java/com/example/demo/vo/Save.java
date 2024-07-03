package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Save {
	int post_id;
	String title;
	String content;
	int user_id;
	String username;
	Date created_at;
	Date updated_at;
}
