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
				上传文件
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
<s:form action="upFileAction" enctype="multipart/form-data">
	<table border="2" cellpadding="0" cellspacing="0" bgcolor="#95BDFF" width="60%" align="center">
		<tr>
			<td><s:textfield name="title" label="文件标题"/></td>
			<td><s:file name="upload" label="选择文件"/></td>
			<td><s:submit value="上传"/></td>
		</tr>
	</table>
</s:form>
</body>
</html>