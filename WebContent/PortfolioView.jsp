<%@ page import="com.fdm.wealthnow.common.User"%>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.HoldingDAO"%>
<%@ page import="com.fdm.wealthnow.common.Holding"%>


<html>
<head>
<title>Holdings</title>
</head>
<body>
			<% HttpSession session = request.getSession(true);%>


	<% User currentUser = (User) (session.getAttribute("loggedInUser"));
	
	long userId = currentUser.getUserId();%>
	
	<form 
	
	<H1>
		Welcome
		<%= currentUser.getFullName() %></H1>
	<H2>
		Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H2>
	<H2>
		Stocks : $<%= HoldingDAO.retrieveHolding(currentUser.getUserId().getStockSymbol() %></H2>
	<H2>
		Quantity : $<%= HoldingDAO.retrieveHolding(currentUser.getUserId()getR %></H2>
	<H2>
		Price : $<%= HoldingDAO.retrieveHolding(currentUser.getUserId()).get(1).getPricePaid() %></H2>

</body>
</html>

	