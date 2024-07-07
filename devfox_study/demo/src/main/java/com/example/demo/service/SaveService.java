package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.SaveMapper;
import com.example.demo.vo.Comment;
import com.example.demo.vo.Save;

import lombok.RequiredArgsConstructor;
import page.Criteria;

//댓글 게시판 에 관련된 모든 기능을 입력한 서비스클래스
@Service
@RequiredArgsConstructor
public class SaveService {
	//savemapper를 변수화 해서 가지고오고
	private final SaveMapper sm;
	
	//boardsave클래스가 실행되었을때 save에 있는 값들로 sm.saveboard에 입력해줌
	public void boardsave(Save save) {
		sm.SaveBoard(save);
	}
	//위와같이 클래스가 실행되었을때 페이징과 글들을 전부 셀렉트해서 가져옴
	   public List<Save> getBoardList(Criteria cri) {
	        return sm.getListWithpage(cri);
	    }
	    //위와 같이 클래스가 실행되었을때 게시글의 양을 전부 가져옴
	    public int getTotalCount(Criteria cri) {
	        return sm.getTotalCount(cri);
	    }
	    // 위와 같이 클래스가 실행되었을떄 검색된 게시글 목록 조회
	    public List<Save> searchBoard(Criteria cri) {
	        return sm.searchBoard(cri);
	    }
	    // 위와 같이 클래스가 실행되었을떄 검색된 게시글 수 조회
	    public int totalSearchCount(Criteria cri) {
	        return sm.totalSearchCount(cri);
	    }
	    //위와 같이 클래스가 실행되었을떄 게시글의 내용을 가져옴
	    public Save selectDetail(int post_id) {
	    	return sm.selectDetail(post_id);
	    }
	    //위와 같이 클래스가 실행되었을떄 수정된 내용을 가져와서 매퍼에 입력
	    public void modify(Save save) {
	    	sm.modify(save);
	    }
	    //위와 같이 클래스가 실행되었을떄 삭제할 post_id를 가져와 매퍼에 입력하여 삭제해줌
	    public void delete(int post_id) {
	    	sm.delete(post_id);;
	    }
	    //위와 같이 클래스가 실행되었을떄 post_id에 맞는 댓글들을 셀렉트해서 가져옴
	    public List<Comment> getComments(int post_id) {
	        return sm.getComments(post_id);
	    }
	    //위와 같이 클래스가 실행되었을떄 댓글을 입력하는 매퍼를 실행
	    public void addComment(Comment comment) {
	    	sm.addComment(comment);
	    }
	    //위와 같이 클래스가 실행되었을떄 수정된 댓글을 받아와서 입력해주는 매퍼를 실행
	    public void updateComment(int comment_id,String comment_content) {
	    	sm.updateComment(comment_id,comment_content);
	    }
	    //위와 같이 클래스가 실행되었을떄 삭제할 comment_id를 받아와서 매퍼를 실행
	    public void deleteComment(int comment_id) {
	    	sm.deleteComment(comment_id);
	    }


}
