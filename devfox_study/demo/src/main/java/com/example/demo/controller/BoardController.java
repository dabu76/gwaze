package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	//saveservice를 사용할수 있게 se로 정의 해주고
	private final SaveService se;
	//board를 get방식으로 받았을때에 게시글의 총량과 게시글들을 검색해서 가지고오고 
	//model로 컨트롤러에서 뷰로 데이터를 전달할수있게하는데 이떄에 addAttribute로 전달할 데이터값을 추가해준다
	  @GetMapping("/board")
	    public String board(Criteria cri, Model model) {
	        List<Save> boardList = se.getBoardList(cri);
	        int totalCount = se.getTotalCount(cri);
	        PageVo pageVo = new PageVo(cri, totalCount);
	        
	        model.addAttribute("boardList", boardList);
	        model.addAttribute("pageVo", pageVo);
	        
	        return "/board";
	    }
	  //글을 작성할 페이지로 이동
	@GetMapping("/writer")
	public String writer() {
		
		return "/writer";
	}
	//작성한 글을 post방식으로 받아서 mapper에 입력하여 작성을 완료
	@PostMapping("/write")
	public String write(Save sv) {
			se.boardsave(sv);
		return "redirect:/board";
	}
	//post방식으로 board를 받으면 검색기능을 실행하는데 이때에 받은 type(제목,작성자 등)과 키워드 드리고 데이터를 전달할model로
	//값을 받아서 검색해주고
	//그거에 맞는 페이지를 표시해주게 구현하였음
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
	//수정페이지를 이동하는데 이때에 미리 적혀있던 글을 가져오기위해 post_id로 검색해와서 글을 전부보이게 해줌
    @GetMapping("/modify")
    public String modifyForm(@RequestParam("post_id") int post_id, Model model) {
        Save save = se.selectDetail(post_id);
        model.addAttribute("save", save);
        return "/modify";
    }
    //수정한후에 post방식으로 글을 받으면 그걸 modify 매퍼에 보내주어 수정을 완료하고 수정된 페이지로 이동
	@PostMapping("/modify")
	public String modify(Save save) {
		se.modify(save);
        return "redirect:/detail?post_id=" + save.getPost_id();
	}
	//삭제페이지로 이동하여 삭제할 post_id 를 받아서 그걸 delete매퍼에 입력하여 삭제를 실행
	@GetMapping("/delete")
	public String delete(@RequestParam("post_id") int post_id) {
		se.delete(post_id);
		return "redirect:/board";
	}
	//댓글을 등록할때 post방식으로 받아와 post_id 내용등을 받아와comment의 post_id가 
	//board의 post_idrk 일치하게 입력하여서 post_id가 일치하는곳에만 댓글이 보일수 있게 입력을 함
	@PostMapping("/addComment")
	public String addComment(@ModelAttribute Comment comment) {
	    	se.addComment(comment);
	 	    return "redirect:/detail?post_id=" + comment.getPost_id();
	    
	    
	}  
	//수정된 댓글을 포스트 방식으로 받아서 updatecomment매퍼에 입력하여 수정함
	 @PostMapping("/updateComment")
	 @ResponseBody 
	    public ResponseEntity<String> updateComment(@RequestParam int comment_id, @RequestParam String comment_content) {
	        try {
	            se.updateComment(comment_id, comment_content);
	        	//댓글을 수정하였을때에는 성공적으로 수정이 되었음을 알리는 알람이 띄워짐
	            return ResponseEntity.ok("댓글이 성공적으로 수정되었습니다.");
	        } catch (Exception e) {
	        	//만약 댓글을 수정할때에 오류가 방색하면 오류가났을때의 예외를 미리 입력하여 띄워줄수있게함
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 중 오류가 발생했습니다.");
	        }
	    }
	 // 삭제할 댓글을 겟방식으로 가져와서 comment_id 를 받아와서 삭제해주고 comment안에 있는 post_id를 받아와서 삭제한후에 post_id를받아와 전에 있던 게시글 페이지로 이동함
	 @GetMapping("/deleteComment")
	 public String deleteComment(@RequestParam("comment_id") int comment_id,@ModelAttribute Comment comment) {
			se.deleteComment(comment_id);
	        return "redirect:/detail?post_id=" + comment.getPost_id();

		}

}
