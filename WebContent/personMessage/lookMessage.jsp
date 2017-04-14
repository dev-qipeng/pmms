<%@page import="JavaBean.MyMessBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理</title>
</head>
<body bgcolor="gray">
<hr noshade>
<s:div align="center">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
		<tr>
			<td width="33%">
				<s:a href="../personMessage/updateMessage.jsp">修改个人信息</s:a>
			</td>
			<td width="33%">
				查看个人信息
			</td>
			<td width="33%">
				<s:a href="../personMessage/updatePass.jsp">修改个人密码</s:a>
			</td>
		</tr>
	</table>
</s:div>
<hr noshade>
	<table border="2" cellpadding="0" cellspacing="0" bgcolor="#95BDFF" width="60%" align="center">
		<%
			MyMessBean mess=null;
			ArrayList MyMessage=(ArrayList)session.getAttribute("MyMess");
			if(MyMessage==null||MyMessage.size()==0){
				response.sendRedirect("../login/login.jsp");
			}else{
				for(int i=MyMessage.size()-1;i>=0;i--){
					mess=(MyMessBean)MyMessage.get(i);
		%>
		<tr>
			<td height="30">
				用户姓名
			</td>
			<td>
				<%=mess.getName() %>
			</td>
		</tr>
		<tr>
			<td height="30">
				用户性别
			</td>
			<td>
				<%=mess.getSex() %>
			</td>
		</tr>
		<tr>
			<td height="30">
				出生日期
			</td>
			<td>
				<%=mess.getBirth() %>
			</td>
		</tr>
		<tr>
			<td height="30">
				用户民族
			</td>
			<td>
				<%=mess.getNation() %>
			</td>
		</tr>
		<tr>
			<td height="30">
				用户学历
			</td>
			<td>
				<%=mess.getEdu() %>
			</td>
		</tr>
		<tr>
			<td height="30">
				用户职称
			</td>
			<td>
				<%=mess.getWork() %>
			</td>
		</tr>
		<tr>
			<td height="30">
				用户电话
			</td>
			<td>
				<%=mess.getPhone() %>
			</td>
		</tr>
		<tr>
			<td height="30">
				用户住址
			</td>
			<td>
				<%=mess.getPlace() %>
			</td>
		</tr>
		<tr>
			<td height="30">
				用户邮箱
			</td>
			<td>
				<%=mess.getEmail() %>
			</td>
		</tr>
		<% 
		}
			}
		%>
	</table>
</body>
</html>