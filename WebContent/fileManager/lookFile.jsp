<%@page import="java.util.ArrayList"%>
<%@page import="JavaBean.MyFileBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统</title>
</head>
<body bgcolor="gray">
<hr noshade>
<s:div align="center">
<s:form action="findFileAction" method="post">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
		<tr>
			<td width="30%">
				<s:a href="http://localhost:8080/PMMS/fileManager/fileUp.jsp">上传文件</s:a>
			</td>
			<td width="30%">
				文件列表
			</td>
			<td width="40%">
				文件标题：<input type="text" size="12" name="title"/>
				<input type="submit" value="下载"/>
			</td>
		</tr>
	</table>
</s:form>
</s:div>
<hr noshade>
<table border="2" cellpadding="0" cellspacing="0" bgcolor="#95BDFF" width="60%" align="center">
	<tr>
		<th width="25%">文件标题</th>
		<th width="25%">文件名字</th>
		<th width="25%">文件类型</th>
		<th width="25%">文件大小</th>
	</tr>
	<%
		ArrayList file=(ArrayList)session.getAttribute("file");
		if(file==null||file.size()==0){
	%>
		<s:div align="center" >您还没有上传任何文件</s:div>
	<%
		}else{
			for(int i=file.size()-1;i>=0;i--){
				MyFileBean mfb=(MyFileBean)file.get(i);
	%>
	<tr>
		<td><%=mfb.getTitle()%></td>
		<td><%=mfb.getName()%></td>
		<td><%=mfb.getContentType()%></td>
		<td><%=mfb.getSize()%></td>
	</tr>
	<% 
		}
			}
		%>
</table>
</body>
</html>