<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
</head>
<body>

Below are the exceptions instance wise:
<br/>

<table border="1" style="width: 100%">
<tr>
<c:forEach var="instanceExceptionMap" items="${instanceExceptionMap}">
<td valign="top" style="width:15%;">
<div style="border:solid;">${instanceExceptionMap.key}</div>

<c:forEach var="exceptions" items="${instanceExceptionMap.value}">
<% out.print("<div style='border:1px solid black;'>");%>
${exceptions.key} : <span style="color: red;">${exceptions.value}</span>
<% out.print("</div>");%>
</c:forEach>
</td>
</c:forEach>
</tr>
</table>
</body>
</html>