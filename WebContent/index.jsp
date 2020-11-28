<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>首页</title>

<script type="text/javascript">
/*javascript脚本语言-----前端语言
 * 弱类型：JavaScript，  var，val:Scala大数据开发，强类型：Java
 */
//删除用户
function deleteUser(userid){
	var result = window.confirm("确定要删除此用户吗？");//window、document是浏览器内置对象，window可省略
	if(result){
		//删除用户
		location.href="UserServlet?action=delete&userid="+userid;
	}
}
function showPre(){
	var page = document.getElementById("page").value;
	if(page == 1){
		alert("当前为第一页！");
		return;
	}
	page--;
	location.href="UserServlet?action=findPage&page="+page;
}
function showNext(){
	var page = document.getElementById("page").value;//记录当前第几页
	var total = document.getElementById("total").value;//总页数
	if(page == total){
		alert("已经是最后一页！");
		return;
	}
	page++;
	location.href="UserServlet?action=findPage&page="+page;
}
</script>

</head>
<body>
	欢迎来到图书管理系统 ${user.name }
	<br>
	<form action="UserServlet" method="post">
		账号：<input type="text" name="userno" id="userno" value="${userno }"><input type="submit" value="搜索">
	</form>
	
	
	<table border="1">
	<!-- tr为一行，td为一个单元格 -->
	<tr><a href="UserServlet?action=toAdd" onclick="">新增用户</a></tr>
		<tr style="font-weight:bold">
			<td>用户id</td>
			<td>账号</td>
			<td>密码</td>
			<td>姓名</td>
			<td>地址</td>
			<td>操作</td>
		</tr>
		<c:forEach var="row" items="${list }"><!-- EL表达式，从request中取用户列表集合进行循环显示 -->
			<tr>
				<td>${row.userid }</td>
				<td>${row.userno }</td>
				<td>${row.password }</td>
				<td>${row.name }</td>
				<td>${row.address }</td>
				<td><a href="#" onclick="deleteUser('${row.userid }')">删除</a></td><!-- a标签是超链接 -->
				<td><a href="UserServlet?action=toUpdate&userid=${row.userid }" onclick="">修改</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="#" onclick="showPre()">上一页</a> 
	&nbsp;&nbsp; 共${total }页
	<a href="#" onclick="showNext()">下一页</a>
	 <input type="hidden" name="page" id="page" value="${page }"/>
	 <input type="hidden" name="total" id="total" value="${total }"/>
	
</body>
</html>