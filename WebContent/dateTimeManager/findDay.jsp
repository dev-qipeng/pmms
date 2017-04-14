<%@page import="JavaBean.MyDayBean"%>
<%@page import="java.util.ArrayList"%>
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
<s:form action="findDayAction" method="post">
	<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
		<tr>
			<td width="30%">
				<s:a href="http://localhost:8080/PMMS/dateTimeManager/addDay.jsp">增加日程</s:a>
			</td>
			<td width="30%">
				<s:a href="http://localhost:8080/PMMS/dateTimeManager/lookDay.jsp">查看日程</s:a>
			</td>
			<td width="40%">
				日程时间：20<input type="text" size="1" name="year"/>年
				<input type="text" size="1" name="month"/>月
				<input type="text" size="1" name="day"/>日
				<input type="submit" value="修删日程"/>
			</td>
		</tr>
	</table>
</s:form>
</s:div>
<hr noshade>
	<table border="2" cellpadding="0" cellspacing="0" bgcolor="#95BDFF" width="60%" align="center">
		<tr>
			<th width="40%">日程时间</th>
			<th width="40%">日程内容</th>
			<th width="20%">用户操作</th>
		</tr>
		<%
			ArrayList day=(ArrayList)session.getAttribute("findday");
			if(day==null||day.size()==0){
		%>
			<s:div align="center" >您还没有日程安排</s:div>
		<%
			}else{
				for(int i=day.size()-1;i>=0;i--){
					MyDayBean dd=(MyDayBean)day.get(i);
		%>
		<tr>
			<td><%=dd.getDay()%></td>
			<td><%=dd.getThing()%></td>
			<td>
				<s:a href="http://localhost:8080/PMMS/dateTimeManager/updateDay.jsp">修改</s:a>
				<s:a href="deleteDayAction">删除</s:a>
			</td>
		</tr>
		<% 
			}
				}
		%>
	</table>
</body>
</html>