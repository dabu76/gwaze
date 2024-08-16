<?php
    include "../include/header.php";
    include "../include/connect.php";
    $type = $_GET["type"];
    if(!$userid){
        echo "<script>
                alert('로그인후 사용하실수 있습니다');
                location.href='../login/selecttype.php?type=login_form'
              </script>";
    }
    if ($type == "_notice") {
       $title = '공지사항';
    } elseif ($type == "_qna") {
        $title = 'Q&A';
    } else {
        $title ='자유게시판';
    }
    
?>
<script>
  	function check_input() {	
      	if (!document.board.subject.value) {
          	alert("제목을 입력하세요!");
          	document.board.subject.focus();
          	return false;
		}
      	if (!document.board.content.value) {	
          	alert("내용을 입력하세요!");    
          	document.board.content.focus();
          	return false;
      	}
	  
      	return true;
   	}
</script>
<form name="board" method="post" action="insert.php?type=<?=$type?>" onsubmit = "return check_input();"
		enctype="multipart/form-data">
	<div class="board_form">
		<h2><?=$title?> > 글쓰기</h2>
		<ul>
			<li>
				<span class="col1">이름 : </span>
				<span class="col2"><?=$username?> 				
					<span class="is_html"><input type="checkbox" name="is_html" value="y"> HTML 쓰기</span>
				</span>
			</li>					
	    	<li>
	    		<span class="col1">제목 : </span>
	    		<span class="col2"><input name="subject" type="text"></span>
	    	</li>	    	
	    	<li class="area">	
	    		<span class="col1">내용 : </span>
	    		<span class="col2">
	    			<textarea name="content"></textarea>
	    		</span>
	    	</li>
			<li>
			    <span class="col1"> 첨부 파일</span>
			    <span class="col2"><input type="file" name="upfile"></span>
			</li>			
	    </ul>
	</div>

	<ul class="buttons">
   
		    <li><button type="submit">저장하기</button></li>
		<?php
		    
			$list_url = "selectboard.php?type=$type";
		?>
		    <li><button type ="button" onclick="location.href='<?=$list_url?>'">목록보기</button></li>
	</ul>		
</form>
