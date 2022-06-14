<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/guestbook3/add" method="get">
	<!-- form 메소드에 post로 작성하면 한글 깨짐 -->
		<table border="1">
		<colgroup>
				<col style="width: 50px;">
				<col style="width: 170px;">
				<col style="width: 80px;">
				<col style="width: 170px;">
		</colgroup>
			<tr>
				<td>이름</td>
				<td ><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4">
				<textarea cols="70" rows="10" name="content" value=""></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<button type="submit">확인</button>
				</td>
			</tr>
		</table>
	</form>
	
	<br>
	
	<c:forEach items="${requestScope.guestList}" var="guestbookVo">
		<table border="1">
		<colgroup>
			<col style="width: 50px;">
			<col style="width: 120px;">
			<col style="width: 250px;">
			<col style="width: 65px;">
		</colgroup>
			<tr>
				<td>[ ${guestbookVo.no}번 ]</td>
				<td> 이름: ${guestbookVo.name} </td>
				<td>[ 등록날짜: ${guestbookVo.regDate} ]</td>
				<td><a href="deleteForm/${guestbookVo.no}">[삭제]</a></td>
			</tr>
			<tr>
				<td colspan="4">
				${guestbookVo.no}번째 방명록 내용<br>
				${guestbookVo.content}</td>
			</tr>
		</table>
		<br>
	</c:forEach>

</body>
</html>