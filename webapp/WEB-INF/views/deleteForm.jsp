<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>방명록 삭제</h2>
	
	<p>삭제를 원하시면 비밀번호를 입력해 주세요.</p>

	<form action="/guestbook3/delete/${guestbookVo.no}" method="get">
		비밀번호  <input type="password" name="password" value="">
		<button type="submit">확인</button>
		<br><br>
		<a href="/guestbook3/list">메인으로 돌아가기</a>
	</form>

</body>
</html>