<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<%@ include file="header.jsp"%>

<html>
<head>
<script>
    function check() {
        if (save.title.value == "") {
            alert("제목을 입력");
            save.title.focus();
            return false;
        }
        if (save.content.value == "") {
            alert("내용을 입력");
            save.content.focus();
            return false;
        }
        return true;
    }
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 로그인이 안 되어 있는 경우 로그인 페이지로 리디렉션 -->
<sec:authorize access="!isAuthenticated()">
    <script>
        alert("로그인을 해주세요");
        location.href="/login";
    </script>
</sec:authorize>

<!-- 로그인이 되어 있는 경우 -->
<sec:authorize access="isAuthenticated()">
    <!-- 사용자 아이디(hidden 필드) 자동 저장 -->
    <form name="save" action="/write" method="post" onsubmit="return check()">
        <table>
            <tr>
                <th>제목</th>
                <td>
                    <input type="text" name="title">
                </td>
            </tr>
            <tr>
                <th>내용</th>
                <td>
                    <textarea name="content"></textarea>
                </td>
            </tr>
            <!-- hidden 필드에 사용자 아이디 저장 -->
            <input type="hidden" name="username" value="<sec:authentication property='principal.username' />">
        </table>
        <div>
            <input type="submit" value="저장">
        </div>
    </form>
</sec:authorize>

</body>
</html>
