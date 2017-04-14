<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理系统  </title>
</head>
<body>
	<s:form action="loginAction" method="post">
		<table align="center" width="100%">
			<tr>
				<td align="right" width="50%">
					<img src="../images/cc.gif" alt="为之则易，不为则难！" height="80"/>
				</td>
				<td align="left" width="50%">
					<h1>个人信息管理系统</h1>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<hr align="center" width="100%" size="20" color="green"/>
				</td>
			</tr>
			<tr>
				<td width="30%" align="center">
					<img src="../images/a.gif" alt="长城" height="280"/>
				</td>
				<td width="70%">
					<table border="5" align="center" bgcolor="#99aadd">
						<tr>
							<td>
								<s:textfield name="userName" label="登 录 名:" size="16" />
							</td>
						</tr>
						<tr>
							<td>
								<s:password name="password" label="登录密码:" size="17"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="确定"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="清空 "/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<s:a href="register.jsp">注册</s:a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>