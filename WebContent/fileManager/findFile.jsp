<%@page import="java.util.ArrayList"%>
<%@page import="JavaBean.MyFileBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
				<s:a href="http://localhost:8080/PMMS/fileManager/lookFile.jsp">文件列表</s:a>
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
			<th width="20%">文件标题</th>
			<th width="20%">文件名字</th>
			<th width="20%">文件类型</th>
			<th width="20%">文件大小</th>
			<th width="20%">用户操作</th>
		</tr>
		<%
			ArrayList findfile = (ArrayList) session.getAttribute("findfile");
			if (findfile == null || findfile.size() == 0) {
		%>
		<s:div align="center">您没有上传此文件</s:div>
		<%
			} else {
				for (int i = findfile.size() - 1; i >= 0; i--) {
					MyFileBean mfb = (MyFileBean) findfile.get(i);
		%>
		<tr>
			<td><%=mfb.getTitle()%></td>
			<td><%=mfb.getName()%></td>
			<td><%=mfb.getContentType()%></td>
			<td><%=mfb.getSize()%></td>
			<td>
				<a href="downFileAction?fileName=<%=mfb.getName()%>" >下载</a>
				<a href="deleteFileAction?fileName=<%=mfb.getName()%>&title=<%=mfb.getTitle()%>">删除</a>
			</td>
		</tr>
		<tr>
			<img src="<%=request.getServletContext().getRealPath("myfile/"+mfb.getName()) %>" alt="<%=mfb.getTitle()%>">
		</tr>
		<%
			}
			}
		%>
	</table>
</body>
</html>