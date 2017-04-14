<%@page import="JavaBean.UserNameBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#CCCCFF">
	<%
		String loginname=null;
		ArrayList login=(ArrayList)session.getAttribute("username");
		if(login==null||login.size()==0){
			loginname="水木清华";
		}else{
			for(int i=login.size()-1;i>=0;i--){
				UserNameBean nm=(UserNameBean)login.get(i);
				loginname=nm.getUserName();
			}
		}
	%>
	<table width="100%" align="right" bgcolor="blue">
		<tr height="10" bgcolor="gray" align="center">
			<td>
				<a href="../personMessage/lookMessage.jsp" target="about">个人信息管理</a>
			</td>
			<td>
				<a href="../friendManager/lookFriends.jsp" target="about">通讯录管理</a>
			</td>
			<td>
				<a href="../dateTimeManager/lookDay.jsp" target="about">日程安排管理</a>
			</td>
			<td>
				<a href="../fileManager/lookFile.jsp" target="about">个人文件管理</a>
			</td>
			<td>
				<a href="../login/login.jsp" target="_top">退出主页面</a>
			</td>
			<td>
				欢迎<%=loginname %>使用本系统
			</td>
		
		</tr>
	</table>
</body>
</html>