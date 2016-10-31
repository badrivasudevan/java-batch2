<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page 
	import="com.fdm.wealthnow.common.User" %>
<%@ page 
 	import="com.fdm.wealthnow.dao.UserDAO" %>	 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
	<H1> Welcome <%= currentUser.getFullName() %></H1>
	<H2> Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H2>
	<h3> Deposit/Withdraw Funds </h3>
	<form action = "${pageContext.request.contextPath}/BalanceController" method="POST">
		current balance :
		<input type="text" value=session.getAttribute(UserDAO.getBalance(currentUser.getUserId())) name="bal"/>
		<%-- $<%= UserDAO.getBalance(currentUser.getUserId()) %> --%>
		<%-- <%= request.getAttribute("bal") %> --%>
		<select name="operator">
						<option value="+">Deposit</option>
						<option value="-">Withdrawal</option>
		</select>
		<input type="text" name="fund">
		<button type="submit">Calculate</button>
	</form>
	<p> Balance = ${result} </p>
	
</body>
</html>