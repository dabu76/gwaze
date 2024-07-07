<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
    <!-- 게시판에서 게시글을 클릭했을때 나오는 게시글과 댓글 창 페이지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 페이지</title>
<script>
//ここにcommentidが来たら、ここでcommentidを新たに変数宣言し、commentContent_commentIdを空白を除去して保存します
function showEditForm(commentId) {
    var commentContent = document.getElementById('commentContent_' + commentId).innerText.trim();
  //その後、新しいフォームを作成し、そこにIDを保存してスタイルを指定して保存をクリックすると、update commentに移動します そして。
  //update Commentを完了した後は、修正されたものをコメント領域に挿入します
    var editForm = '<form id="editForm_' + commentId + '" onsubmit="return updateComment(' + commentId + ');">';
    editForm += '<input type="hidden" name="comment_id" value="' + commentId + '">';
    editForm += '<textarea name="comment_content" rows="2">' + commentContent + '</textarea>';
    editForm += '<button type="submit">저장</button>';
    editForm += '<button type="button" onclick="cancelEdit(' + commentId + ');">취소</button>';
    editForm += '</form>';

    document.getElementById('commentContent_' + commentId).innerHTML = editForm;
}
//新しくアップデートされたものをもらって IDとコンテンツに保存してくれて
function updateComment(commentId) {
    var updatedContent = document.forms['editForm_' + commentId]['comment_content'].value;
 // Ajax形式でコメント修正要請XMLHttpRequestとは、ウェブブラジャーで相互作用できるようにしたJavaScript語で
 // リロードすることなくデータをやり取りできるオブジェクト
    var xhr = new XMLHttpRequest();
  //post形式で与えて/updateCommentに非同期的(true)で送ると
    xhr.open('POST', '/updateComment', true);
  //コンテンツタイプのヘッダーを設定することで、その値でapplication/x-www-form-urlencodedを指定することで
  //その意味は、キーとバリューがペアでエンコードされて伝送されるということ
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  //onreadystatechangは、オブジェクトの状態が変わると
    xhr.onreadystatechange = function() {
    	//もし、現在のオブジェクトの状態が完了したオブジェクトの状態と同じなら
        if (xhr.readyState === XMLHttpRequest.DONE) {
        	//この時、xhrの値を示し
            //그값이 200이라면 댓글을 수정해준다
            if (xhr.status === 200) {
            	//その値が200ならコメントを修正してくれる
                document.getElementById('commentContent_' + commentId).innerHTML = updatedContent;
            } else {
            	//200でなければ失敗アラーム
                alert('댓글 수정에 실패했습니다.');
            }
        }
    };
  //そのように送るIDとコンテンツをurlにエンコードして伝送するようにしてくれる
    xhr.send('comment_id=' + commentId + '&comment_content=' + encodeURIComponent(updatedContent));
    return false; // 폼 제출 방지
}

function cancelEdit(commentId) {
	// 元のコメントを変数として保存し、キャンセルを押したときに中に入る内容を再び読み込む（originameContent）
    var originalContent = document.getElementById('commentContent_' + commentId).getAttribute('data-original');
    document.getElementById('commentContent_' + commentId).innerText = originalContent;
}

function check() {
	// ログイン状態を確認
    var username = document.forms['insert'].username.value;
    var commentContent = document.forms['insert'].comment_content.value;

    if (username === '') {
        alert('로그인 후 댓글을 입력할 수 있습니다.');
        window.location.href = '<c:url value="/login" />'; // ログインページへ移動
        return false; // 폼 제출 방지
    } else if (commentContent.trim() === "") {
        alert('댓글을 입력해주세요');
        document.forms['insert'].comment_content.focus();
        return false; // 폼 제출 방지
    }

    return true; // 폼 제출 허용
}
</script>
</head>
<body>
	<!-- 掲示文とユーザーネーム、そして日付などが出てくるスクリプト --->
<div class="container">
    <div class="detail">
        <h2>제목 = ${save.title}</h2>
        <p class="info">
            <span class="user">아이디 = ${save.username}</span> 
            	<!-- fmt形式でフォーマットさせて出力させる例(2024-07-07)-->
            날짜 = <fmt:formatDate value="${save.created_at}" pattern="yyyy-MM-dd" />
        </p>
        <div class="body">
            게시글 = ${save.content }
        </div>
        
        <!-- コメントが出るスクリプト-->
        <c:forEach var="comment" items="${commentList}">
            <div class="comment">
                <p>아이디: ${comment.comment_username} | 날짜: ${comment.created_at}</p>
                <p id="commentContent_${comment.comment_id}" data-original="${comment.comment_content}">
                    ${comment.comment_content}
                </p>
                
                <!-- コメントを修正や削除する時に一旦ログインされているか確認し、その後ログインしたユーザーネームとコメントを書いたユーザーネームが同じか確認して
				もし同じであれば、修正や削除ができるようにする。ここで修正機能は、そのページで修正して登録をすぐにできるようにするために
				JavaScript関数を呼び出してデータを送る方式を使用する(javascript:void(0)を活用してページ移動を防ぐ-->
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.username" var="currentUsername" />
                    <c:if test="${comment.comment_username == currentUsername}">
                        <a href="javascript:void(0);" onclick="showEditForm(${comment.comment_id});">수정</a>
						<a href="/deleteComment?comment_id=${comment.comment_id}&post_id=${comment.post_id}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
                    </c:if>
                </sec:authorize>
            </div>
        </c:forEach>
        
        <!-- コメント入力スクリプト
		ヒドゥンタイプでpost_idとusernameを与え、内容を入力してsubmitにするが、この時にコメント内容がなければアラームが鳴るためのonsubmit-->
        <form name="insert" action="/addComment" method="post" onsubmit="return check()">
            <input type="hidden" name="post_id" value="${save.post_id}">
            <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}">
            <textarea name="comment_content" placeholder="댓글을 입력하세요"></textarea>
            <button type="submit">댓글 등록</button>
        </form>
        	<!-- 掲示板に戻るようにするリストリンクと現在ログインしたIDと掲示文を作成したIDが同じなら修正と削除が表示されるように実装 -->
        <div class="btn">
            <a href="/board">목록</a> 
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.username" var="currentUsername" />
                <c:if test="${save.username == currentUsername}">
                    <a href="/modify?post_id=${save.post_id}">수정</a>
                    <a href="/delete?post_id=${save.post_id}" onclick="return confirm('삭제하시겠어요?')">삭제</a>
                </c:if>
            </sec:authorize>
        </div>
    </div>
</div>
</body>
</html>
