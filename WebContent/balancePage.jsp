<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.fdm.wealthnow.common.User"%>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<html>
<head>
	<title>Balance Page</title>
</head>
<body>
		<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
		<H1>User: <%= currentUser.getFullName() %></H1>
		<H2>Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H2>
	
	
	<fieldset id style = "width: 230px">
			<legend><strong>Trading Platform Quick Links</strong></legend>
					<form action = post method >
						<a href="balancePage.jsp">Account Balance</a> <br>
						<a href="WatchList.jsp">View Watchlists</a> <br>
						<a href="WatchListAdd.jsp">WatchList - Add</a> <br>
						<a href="WatchListAddStocks.jsp">Watchlists - Add Stocks</a> <br>
						<a href="PortfolioView.jsp">User Portfolio Viewer</a> <br>
						<a href="OrderForm.jsp">Order Form</a> <br>	
						<a href="Pending.jsp">Pending Orders</a> <br>
						<a href="OrderHistory.jsp">Order History</a> <br>
						<a href="logout.jsp">Logout</a> 
					</form>
	</fieldset>

	<fieldset>
		<legend><Strong>Deposit/Withdraw Funds</Strong></legend>
	
		<%	if(request.getAttribute("errorMessage") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("Not Enough Funds to withdraw!");</script>
	    <%		}
		%>
		<form action="${pageContext.request.contextPath}/BalanceController"
			method="POST">
			<% long user = currentUser.getUserId(); %>
			<input type="hidden" name="userid" value="<%=user%>" /> Transaction
			Type :
			<%	float bal = UserDAO.getBalance(currentUser.getUserId()); %>
			<input type="hidden" name="bal" value="<%=bal%>" /> <select
				name="operator">
				<option value="+">Deposit</option>
				<option value="-">Withdrawal</option>
			</select> Amount : <input type="number" name="fund">
			<button type="submit">Calculate</button>
		
		</form>
	</fieldset>
</body>
</html>