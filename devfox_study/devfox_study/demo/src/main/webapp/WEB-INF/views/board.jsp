<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <h1>게시판</h1>
    
    <!-- 게시글 목록 출력 -->
  <c:forEach var="post" items="${boardList}">
    <div class="post">
        <!-- 제목을 클릭하면 해당 게시글의 상세 페이지로 이동 -->
        <h2><a href="/detail?post_id=${post.post_id}">${post.title}</a></h2>
        <p>${post.username} | ${post.created_at}</p>
    </div>
</c:forEach>

    
    <!-- 페이지 네이션 출력 -->
    <div class="pagination">
        <c:if test="${pageVo.prev}">
            <a href="/board?pageNum=${pageVo.cri.pageNum - 1}&amount=${pageVo.cri.amount}">이전</a>
        </c:if>
        
        <c:forEach begin="${pageVo.startPage}" end="${pageVo.endPage}" var="i">
            <c:choose>
                <c:when test="${i eq pageVo.cri.pageNum}">
                    <strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    <a href="/board?pageNum=${i}&amount=${pageVo.cri.amount}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        
        <c:if test="${pageVo.next}">
            <a href="/board?pageNum=${pageVo.cri.pageNum + 1}&amount=${pageVo.cri.amount}">다음</a>
        </c:if>
    </div>
 	 <form action="/board" method="post">
 	 <select name="type" id="sel">
    <option value="1" ${cri.type == 1 ? 'selected' : ''}>제목</option>
    <option value="2" ${cri.type == 2 ? 'selected' : ''}>글쓴이</option>
    <option value="3" ${cri.type == 3 ? 'selected' : ''}>내용</option>
</select>
    <input type="text" name="keyword" placeholder="검색어를 입력하세요">
    <button type="submit">검색</button>
	</form>

    <a href="/writer">글쓰기</a>
</body>
</html>
