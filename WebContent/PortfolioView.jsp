<%@ page import="com.fdm.wealthnow.common.User"%>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.HoldingDAO"%>
<%@ page import="com.fdm.wealthnow.common.Holding"%>
<%@ page import="java.util.List"%>

<html>
<head>
<title>Holdings</title>
</head>
<% List<Holding> holdingList = (List<Holding>) request.getAttribute("holdingList"); %>

<body>
	<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
	<H1>
		User:<%= currentUser.getFullName() %></H1>
	<H2>
		Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H2>

	<table id="portfolio_holdings" class="table table-striped">

		<tr>
			<th>Stock Symbol</th>
			<th>Quantity</th>
			<th>Price Paid</th>
			<th>Currency</th>

		</tr>

		<% for (Holding holding : holdingList) { %>
		<tr>
			<td><%= holding.getStockSymbol()%></td>
			<td><%= holding.getRemainingQuantity()%></td>
			<td><%= holding.getPricePaid()%></td>
			<td><%= holding.getCurrency()%></td>
		</tr>
		<% } %>
		</body>
	</table>
</html>
