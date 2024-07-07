<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
</head>
<body>
    <h1>게시판</h1>
    
	<!-- 掲示文リストを出力
	c:foreachを通じてboardlistにある全てを反目文に持ってくる
	varはpostで定義してくれてpost.**でポストの中にあるIDタイトルなどを持ってくることができるようにした-->
  <c:forEach var="post" items="${boardList}">
    <div class="post">
        <!-- タイトルをクリックするとその投稿の詳細ページに移動 -->
        <h2><a href="/detail?post_id=${post.post_id}">${post.title}</a></h2>
        <p>${post.username} | ${post.created_at}</p>
    </div>
</c:forEach>

    
   <!--ページ出力-->
    <div class="pagination">
    	<!-- 繰り返し文を通じてpageVo.prevがあれば最初のページから最後のページまで表示してくれて-->
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
        
    </div>
    	<!-- 検索機能でvalueに1、2、3を与え、分類によってタイトル、書いた人の内容を検索できる機能を作り
セレクトされたことによって/boardからポストで受け、それに関する機能をマッパーで実行するようにした-->
 	 <form action="/board" method="post">
 	 <select name="type" id="sel">
    <option value="1" ${cri.type == 1 ? 'selected' : ''}>제목</option>
    <option value="2" ${cri.type == 2 ? 'selected' : ''}>글쓴이</option>
    <option value="3" ${cri.type == 3 ? 'selected' : ''}>내용</option>
</select>
	<!--検索するテキストウィンドウ-->
    <input type="text" name="keyword" placeholder="검색어를 입력하세요">
    <button type="submit">검색</button>
	</form>
	<!--書き込みウィンドウに移動させてくれるリンク-->
    <a href="/writer">글쓰기</a>
</body>
</html>
