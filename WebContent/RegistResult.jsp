<%@ page language="java" contentType="text/html; charset=UTF-8" import="user.bean.RegistrantInfo" pageEncoding="Windows-31J" %>

<!DOCTYPE html>

<jsp:useBean id="userInfo" scope="session" class="user.bean.UserInfo" />
<jsp:useBean id="regInfo" scope="request" class="user.bean.RegistrantInfo" />
<html>
<head>
<link rel="stylesheet" href="Task.css" type="text/css" />
<title>�ٗp�ғo�^</title>	
</head>
<body>
	<%@ include file="Header.jsp" %>

	<div id="main">
		�ٗp�ғo�^<hr color=white width=30%>
		�������܂���<br />
		<table class="regList">
		<tr>
			<th width="150">ID</th>
			<td width="200"><jsp:getProperty name="regInfo" property="rId" /></td>
		</tr>
		<tr>
			<th>���O</th>
			<td><jsp:getProperty name="regInfo" property="rName" /></td>
		</tr>
		<tr>
			<th>�N��</th>
			<td><jsp:getProperty name="regInfo" property="rAge" /></td>
		</tr>
		</table>
		<br />
		<div id="menu"><a href="MenuList.jsp" style="text-decoration: none">�ꗗ��</a></div>
 	</div>

	<%@ include file="Footer.jsp" %>
</body>
</html>