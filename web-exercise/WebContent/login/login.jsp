<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录滑稽</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/all.css">
<script type="text/javascript">

	function changeImg() {
		//获取Img控件对象
		var val = document.getElementById("validate");
		//每次重新获取访问地址(换图片路径)
		val.src="${pageContext.request.contextPath }/ValidateServlet?cmd=validate&date=" + new Date();
	}
</script>
</head>
<body bgcolor="black">

		<h1 class="title" align="center"><img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png" width="50px" height="50px">登录滑稽网<img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png"></h1>
		<hr width="80%">
		<div class="loginbimg" align="center">
			<form action="${pageContext.request.contextPath }/AllServlet?cmd=login"
				method="post">
				<table class="logintable" border="1" >
					<tr>
						<td>姓名:</td>
						<td><input type="text" name="name" id="id"><span id="naemspan"></span></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input type="password" name="password" id="password"></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><font color="red">${requestScope.error_msg }</font></td>
					</tr>
					<tr>
						<td>验证码:</td>
						<td><input type="text" name="validate">
							<img alt="验证码" id="validate" src="${pageContext.request.contextPath }/AllServlet?cmd=validate"
							style="cursor:pointer;" onclick="changeImg()"/>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2"><font color="red">${val_msg }</font></td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<label><input type="checkbox" checked="checked">记住用户</label>
							<label><input type="checkbox" checked="checked">记住密码</label>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input type="reset" value="重置"> 
							<input type="submit" value="登录">
						</td>
					</tr>
				</table>
				<div align="center">
			<a href="${pageContext.request.contextPath }/homePage/homepage.jsp">返回主页</a>
			<a href="${pageContext.request.contextPath }/login/regist.jsp">注册</a>
			</div>
			</form>
		</div>
		<div class="bot" ><jsp:include page="/homePage/bot.jsp"></jsp:include></div>
</body>
</html>