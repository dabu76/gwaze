package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Comment;
import com.example.demo.vo.Save;

import page.Criteria;

//ユーザーメッパーxmlへ行く途中脚
@Mapper
public interface SaveMapper {
	void SaveBoard (Save save);
	public List<Save> getListWithpage(Criteria cri);
    int getTotalCount(Criteria cri);
    List<Save> searchBoard(Criteria cri);
    int totalSearchCount(Criteria cri);	
    public Save selectDetail(int post_id);
    public void modify(Save save);
    public void delete(int post_id);
    List<Comment> getComments(int post_id);
    public void addComment(Comment comment);
    public void updateComment(int comment_id,String comment_content);
    public void deleteComment(int comment_id);
}
