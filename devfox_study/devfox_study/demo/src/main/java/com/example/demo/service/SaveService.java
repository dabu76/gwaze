package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.SaveMapper;
import com.example.demo.vo.Comment;
import com.example.demo.vo.Save;

import lombok.RequiredArgsConstructor;
import page.Criteria;

@Service
@RequiredArgsConstructor
public class SaveService {
	private final SaveMapper sm;
	
	public void boardsave(Save save) {
		sm.SaveBoard(save);
	}
	   public List<Save> getBoardList(Criteria cri) {
	        return sm.getListWithpage(cri);
	    }
	    
	    public int getTotalCount(Criteria cri) {
	        return sm.getTotalCount(cri);
	    }
	    // 검색된 게시글 목록 조회
	    public List<Save> searchBoard(Criteria cri) {
	        return sm.searchBoard(cri);
	    }
	    // 검색된 게시글 수 조회
	    public int totalSearchCount(Criteria cri) {
	        return sm.totalSearchCount(cri);
	    }
	    //게시글의 내용보기
	    public Save selectDetail(int post_id) {
	    	return sm.selectDetail(post_id);
	    }
	    public void modify(Save save) {
	    	sm.modify(save);
	    }
	    public void delete(int post_id) {
	    	sm.delete(post_id);;
	    }
	    public List<Comment> getComments(int post_id) {
	        return sm.getComments(post_id);
	    }
	    public void addComment(Comment comment) {
	    	sm.addComment(comment);
	    }
	    public void updateComment(int comment_id,String comment_content) {
	    	sm.updateComment(comment_id,comment_content);
	    }


}
