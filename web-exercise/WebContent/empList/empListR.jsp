<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>滑稽回收站</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/all.css">
<script type="text/javascript">
	function re(){
		if(confirm("确认是否恢复该员工信息数据?") == true) {
			return true;
		}else{
			return false;
		}
	}
	
	function destroy(){
		if(confirm("确认是否彻底销毁该员工数据信息?") == true) {
			return true;
		}else {
			return false;
		}
	}
</script>
</head>
<body bgcolor="black">
<h1 class="title" align="center"><img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png">滑稽回收站<img class="fairy" src="${pageContext.request.contextPath }/img/fairy1.png"></h1>
		<hr width="80%">
	<div align="center" class="recyclelistbimg">
		
			<table border="1" width="700" cellspacing="0" style="text-align:center">
	
				<thead>
					<tr>
						<td>id</td>
						<td>姓名</td>
						<td>密码</td>
						<td>性别</td>
						<td>年龄</td>
						<td>入职日期</td>
						<td>薪资</td>
						<td>电话</td>
						<td>邮箱</td>
						<th colspan="2">操作</th>
					</tr>
				</thead>
				
				<c:forEach items="${requestScope.listr }" var="e">
					<tr>
						<td>${e.id }</td>
						<td>${e.name }</td>
						<td>${e.password }</td>
						<td>${e.gender }</td>
						<td>${e.age }</td>
						<td>${e.hiredate }</td>
						<td>${e.salary }</td>
						<td>${e.phone }</td>
						<td>${e.email }</td>
						<td><a href="${pageContext.request.contextPath }/AllServlet?id=${e.id }&cmd=rEmp" onclick="return re()">恢复</a></td>
						<td><a href="${pageContext.request.contextPath }/AllServlet?id=${e.id }&cmd=destroyEmp" onclick="return destroy()">销毁</a></td>
					</tr>
				</c:forEach>
			</table>
			<div align="center">
			<a href="${pageContext.request.contextPath }/homePage/homepage.jsp">返回主页</a>
			<a href="${pageContext.request.contextPath }/AllServlet?cmd=empListS">返回</a>
			</div>
	</div>
	<hr width="80%">
	<div class="bot" ><jsp:include page="/homePage/bot.jsp"></jsp:include></div>
</body>
</html>