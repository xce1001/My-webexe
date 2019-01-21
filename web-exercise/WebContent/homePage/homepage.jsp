<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>滑稽网</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/all.css">
<script type="text/javascript">
	function safequit(){
		if(confirm('您确定要退出吗') == true){
			return ture;
		}else {
			return false;
		}
	}
</script>
</head>
<body bgcolor="black">
	<div align="center">
		
		<h1 class="title"><img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png">欢迎进入滑稽网<img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png"></h1>
		<hr width="80%">
		<div class="homebimg" >
			<c:if test = "${sessionScope.emp == null }">
				<a id="log" href="${pageContext.request.contextPath }/login/login.jsp"><input type="button" name="login" value="登录"></a>
				<a id="log" href="${pageContext.request.contextPath }/login/regist.jsp"><input type="button" name="regist" value="注册"></a>
			</c:if>
				
			<c:if test = "${sessionScope.emp != null }">
			<div align="right">
				<table border="1" class="welcome">
				<tr>
					<td><font color="white">欢迎${emp.name }成功登录</font></td>
				</tr>
				
				<tr>
					<td><font color="white">您上次登录时间为:${requestScope.lastTime }</font></td>
				</tr>
				</table>
			</div>
			<div align="left">
				<table border="1" >
					<tr>
						<td><a href="${pageContext.request.contextPath }/AllServlet?cmd=empListS">员工列表</a></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath }/AllServlet?cmd=logout" onclick="return safequit()">安全退出</a></td>
					</tr>
				</table>
			</div>
			</c:if>
			<div class="bot" ><jsp:include page="/homePage/bot.jsp"></jsp:include></div>
		</div>
	</div>
</body>
</html>