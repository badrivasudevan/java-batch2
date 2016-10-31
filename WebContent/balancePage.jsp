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
	
	<%
		if(request.getAttribute("errorMessage") != null) {
  		     out.println(request.getAttribute("errorMessage"));
    	}
	%>
	<form action = "${pageContext.request.contextPath}/BalanceController" method="POST">
		<% long user = currentUser.getUserId(); %>
		<input type="hidden" name="userid" value="<%=user%>"/>
		
		Transaction Type : 
		<%	float bal = UserDAO.getBalance(currentUser.getUserId()); %>
		<input type="hidden" name="bal" value="<%=bal%>" />
		<select name="operator">
						<option value="+">Deposit</option>
						<option value="-">Withdrawal</option>
		</select>
		Amount : 
		<input type="text" name="fund">
		<button type="submit">Calculate</button>
	</form>
	<%-- <p> Balance = ${result} </p> --%>
	
</body>
</html>