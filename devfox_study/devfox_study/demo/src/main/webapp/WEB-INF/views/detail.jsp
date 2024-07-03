<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세 페이지</title>
<script>
function showEditForm(commentId) {
    var commentContent = document.getElementById('commentContent_' + commentId).innerText.trim();

    var editForm = '<form id="editForm_' + commentId + '" onsubmit="return updateComment(' + commentId + ');">';
    editForm += '<input type="hidden" name="comment_id" value="' + commentId + '">';
    editForm += '<textarea name="comment_content" rows="2">' + commentContent + '</textarea>';
    editForm += '<button type="submit">저장</button>';
    editForm += '<button type="button" onclick="cancelEdit(' + commentId + ');">취소</button>';
    editForm += '</form>';

    // 수정 폼을 댓글 영역에 삽입
    document.getElementById('commentContent_' + commentId).innerHTML = editForm;
}

function updateComment(commentId) {
    var updatedContent = document.forms['editForm_' + commentId]['comment_content'].value;

    // Ajax로 댓글 수정 요청
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/updateComment', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            console.log("AJAX request status:", xhr.status); // 디버깅 로그
            if (xhr.status === 200) {
                // 수정된 내용을 화면에 반영
                document.getElementById('commentContent_' + commentId).innerHTML = updatedContent;
            } else {
                alert('댓글 수정에 실패했습니다.');
            }
        }
    };
    xhr.send('comment_id=' + commentId + '&comment_content=' + encodeURIComponent(updatedContent));
    return false; // 폼 제출 방지
}

function cancelEdit(commentId) {
    // 원래 댓글 내용으로 복구
    var originalContent = document.getElementById('commentContent_' + commentId).getAttribute('data-original');
    document.getElementById('commentContent_' + commentId).innerText = originalContent;
}

function check() {
    // 로그인 상태를 확인
    var username = document.forms['insert'].username.value;
    var commentContent = document.forms['insert'].comment_content.value;

    if (username === '') {
        alert('로그인 후 댓글을 입력할 수 있습니다.');
        window.location.href = '<c:url value="/login" />'; // 로그인 페이지로 이동
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
<div class="container">
    <div class="detail">
        <h2>제목 = ${save.title}</h2>
        <p class="info">
            <span class="user">아이디 = ${save.username}</span> 
            날짜 = <fmt:formatDate value="${save.created_at}" pattern="yyyy-MM-dd" />
        </p>
        <div class="body">
            게시글 = ${save.content }
        </div>
        
        <!-- 댓글 목록 -->
        <c:forEach var="comment" items="${commentList}">
            <div class="comment">
                <p>아이디: ${comment.comment_username} | 날짜: ${comment.created_at}</p>
                <p id="commentContent_${comment.comment_id}" data-original="${comment.comment_content}">
                    ${comment.comment_content}
                </p>
                
                <!-- 수정 링크 -->
                <sec:authorize access="isAuthenticated()">
                    <sec:authentication property="principal.username" var="currentUsername" />
                    <c:if test="${comment.comment_username == currentUsername}">
                        <a href="javascript:void(0);" onclick="showEditForm(${comment.comment_id});">수정</a>
                        <a href="/deleteComment?comment_id=${comment.comment_id}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
                    </c:if>
                </sec:authorize>
            </div>
        </c:forEach>
        
        <!-- 댓글 입력 폼 -->
        <form name="insert" action="/addComment" method="post" onsubmit="return check()">
            <input type="hidden" name="post_id" value="${save.post_id}">
            <input type="hidden" name="username" value="${pageContext.request.userPrincipal.name}">
            <textarea name="comment_content" placeholder="댓글을 입력하세요"></textarea>
            <button type="submit">댓글 등록</button>
        </form>
        
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
    <!-- reply -->
</div>
</body>
</html>
