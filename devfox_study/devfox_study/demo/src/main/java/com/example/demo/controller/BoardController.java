package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.SaveService;
import com.example.demo.vo.Comment;
import com.example.demo.vo.Save;

import lombok.RequiredArgsConstructor;
import page.Criteria;
import page.PageVo;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final SaveService se;
	  @GetMapping("/board")
	    public String board(Criteria cri, Model model) {
	        List<Save> boardList = se.getBoardList(cri);
	        int totalCount = se.getTotalCount(cri);
	        PageVo pageVo = new PageVo(cri, totalCount);
	        
	        model.addAttribute("boardList", boardList);
	        model.addAttribute("pageVo", pageVo);
	        
	        return "/board";
	    }
	@GetMapping("/writer")
	public String writer() {
		
		return "/writer";
	}
	@PostMapping("/write")
	public String write(Save sv) {
			se.boardsave(sv);
		return "redirect:/board";
	}
	@PostMapping("/board")
	public String boardSearch(@RequestParam int type, @RequestParam String keyword, Model model) {
	    Criteria cri = new Criteria();
	    cri.setKeyword(keyword);
	    System.out.println(type);
	   cri.setType(type);
	   
	    List<Save> searchList = se.searchBoard(cri);
	    int total = se.totalSearchCount(cri);
	    PageVo pv = new PageVo(cri, total);

	    model.addAttribute("boardList", searchList);
	    model.addAttribute("pageVo", pv);

	    return "/board"; // 검색 결과를 보여줄 뷰 페이지
	}
	@GetMapping("/detail")
	public String detail(@RequestParam("post_id") int post_id, Model model) {
		Save save =se.selectDetail(post_id);
        model.addAttribute("save", save);
        List<Comment> commentList = se.getComments(post_id);
        model.addAttribute("commentList", commentList);
	return "/detail";
	}
    @GetMapping("/modify")
    public String modifyForm(@RequestParam("post_id") int post_id, Model model) {
        Save save = se.selectDetail(post_id);
        model.addAttribute("save", save);
        return "/modify";
    }
	@PostMapping("/modify")
	public String modify(Save save) {
		se.modify(save);
        return "redirect:/detail?post_id=" + save.getPost_id();
	}
	@GetMapping("/delete")
	public String delete(@RequestParam("post_id") int post_id) {
		se.delete(post_id);
		return "redirect:/board";
	}
	@PostMapping("/addComment")
	public String addComment(@ModelAttribute Comment comment) {
	    	// 댓글 등록
	    	se.addComment(comment);
	 	    return "redirect:/detail?post_id=" + comment.getPost_id();
	    
	    
	}  
	 @PostMapping("/updateComment")
	 @ResponseBody 
	    public ResponseEntity<String> updateComment(@RequestParam int comment_id, @RequestParam String comment_content) {
	    	System.out.println(comment_id);
	    	System.out.println(comment_content);
	        try {
	            se.updateComment(comment_id, comment_content);
	            return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 중 오류가 발생했습니다.");
	        }
	    }

}
