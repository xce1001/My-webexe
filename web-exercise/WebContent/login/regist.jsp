<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/all.css"></head>
<body bgcolor="black">
	<h1 class="title" align="center"><img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png">欢迎注册滑稽员工<img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png"></h1>
	<hr width="80%">
	<div class="registbimg">
		<form action="${pageContext.request.contextPath }/AllServlet?cmd=regist" method="post">
			<table class="registtable">
				<tr>
					<td>滑稽名:</td>
					<td><input type="text" name="name" id="id"></td>
				<tr>
				<tr>
					<td>滑稽密码:</td>
					<td><input type="password" name="password" id="password"></td>
				<tr>
				<tr>
					<td align="center" colspan="2">
						<label><input type="radio" name="gender" value="nan" checked="checked"  /><font color="darkgray">男</font></label>
						<label><input type="radio" name="gender" value="女" checked="checked" /><font color="darkgray">女</font></label>
					</td>
				<tr>
				<tr>
					<td>年龄:</td>
					<td><input type="text" name="age" id="age"></td>
				<tr>
				<tr>
					<td>入职日期:</td>
					<td><input type="date" name="hiredate" value="hiredate"></td>
				<tr>
				<tr>
					<td>滑稽电话号码:</td>
					<td><input type="text" name="phone" id="phonenumber"></td>
				<tr>
				<tr>
					<td>邮箱地址:</td>
					<td><input type="email" name="email" id="email"></td>
				<tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" value="注册"></td>
				<tr>
			</table>
			<a href="${pageContext.request.contextPath }/homePage/homepage.jsp">返回主页</a>
		</form>
	</div>
	<div class="bot" ><jsp:include page="/homePage/bot.jsp"></jsp:include></div>
</body>
</html>