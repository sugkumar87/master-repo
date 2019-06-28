<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exception Tracker</title>
</head>
<body style="width:100%; height:100%;background-color:#abc1c1;">
<center><h2>Welcome to Exception Tracker</h2></center>
<p>Exception Tracker is a tool thats check the exceptions that have occurred in the bConnected instances. The tool checks the exceptions in the server logs and if found, then all those exceptions is listed with the count of number of times the exception is seen in the logs. <br/>
<b>By default, the tool checks all type of exception that are seen in the server log file.</b>
<br/>
If certain type of exception(s) needs to be excluded in the search, then that functionality is also possible. But, currently the tool is in intial stage of development and hence, this functionality is not possible from UI. 
Later, this functionality will be added on the UI.
<br/>

The tool currently searches the exceptions in Yesturday's server log file. Later, the UI will be changed to have a date selector for the users, so that user(s) can search for the exceptions on a particular date (or date range). The user(s) will also have the flexibiltiy to select the instances for which the exception  needs to be searched.
</p>
<br/>
<br/>
<div style="text-align: center;">
<form action="./checkException" method="get">
	<input type="submit" value="Check Exceptions" style="display: inline-block;" />
</form>
</div>
</body>
</html>