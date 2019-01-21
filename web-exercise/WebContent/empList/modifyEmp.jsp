<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改滑稽</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/all.css">
</head>
<body align="center" bgcolor="black">
	<h1 class="title"><img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png">滑稽信息修改<img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png"></h1>
	<hr width="80%">
	<div class="modifybimg">
		<form action="${pageContext.request.contextPath }/AllServlet?id=${requestScope.emp.id }&hiredate=${requestScope.emp.hiredate }&cmd=udateEmp" method="post">
			<table border="1" align="center">
					<tr>
						<td>id:</td>
						<td>${requestScope.emp.id }</td>
					</tr>
					<tr>
						<td>姓名:</td>
						<td><input type="text" name="name" value="${requestScope.emp.name }"></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input type="text" name="password" value="${requestScope.emp.password }"></td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<label><input type="radio" name="gender" value="男" checked="checked"  /><font color="darkgray">男</font></label>
							<label><input type="radio" name="gender" value="女" checked="checked" /><font color="darkgray">女</font></label>
						</td>
					</tr>
					<tr>
						<td>年龄:</td>
						<td><input type="text" name="age" value="${requestScope.emp.age }"></td>
					</tr>
					<tr>
						<td>入职日期:</td>
						<td>${requestScope.emp.hiredate }</td>
					</tr>
					<tr>
						<td>薪资:</td>
						<td><input type="text" name="salary" value="${requestScope.emp.salary }"></td>
					</tr>
					<tr>
						<td>联系电话:</td>
						<td><input type="text" name="phone" value="${requestScope.emp.phone }"></td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td><input type="text" name="email" value="${requestScope.emp.email}"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="确认修改"></td>
					</tr>
			</table>
			
		</form>
	</div>
	<hr width="80%">
	<div class="bot" ><jsp:include page="/homePage/bot.jsp"></jsp:include></div>
</body>
</html>