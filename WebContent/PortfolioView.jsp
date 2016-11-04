<%@ page import="com.fdm.wealthnow.common.User"%>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.HoldingDAO"%>
<%@ page import="com.fdm.wealthnow.common.Holding"%>
<%@ page import="com.fdm.wealthnow.backendService.HoldingService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<html>
<head>
<title>Holdings</title>
</head>


<body>

<% 		User currentUser = (User) (session.getAttribute("loggedInUser"));
		List<Holding> holdingList = HoldingService.callDAO(currentUser.getUserId());
%>
	
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

		<table id="portfolio_holdings" class="table table-striped" border="1">

			<tr>
				<th rowspan="2">Stock Symbol</th>
				<th colspan="2">Holding</th>
				<th colspan="2">Bought</th>
				<th colspan="2">Sold</th>
				<th rowspan="2">Profit/Loss</th>
				<th rowspan="2">Currency</th>

			</tr>
			
			<tr>
			<td>Quantity</td>
			<td>Current Stock Worth</td>
			
			<td>Quantity</td>
			<td>Price paid</td>
			
			<td>Quantity</td>
			<td>Money Realised</td>
			</tr>
			
			<%
				for (Holding holding : holdingList) {
			%>
			<tr>
				<td><%=holding.getStockSymbol()%></td>
				
				<td><%=holding.getHoldingQuantity()%></td>
				<td><%=holding.getCurrentStockWorth()%></td>
				
				<td><%=holding.getBoughtQuantity()%></td>
				<td><%=holding.getPricePaid()%></td>
				
				<td><%=holding.getSoldQuantity()%></td>
				<td><%=holding.getMoneyRealized()%></td>
				
				<td><%=holding.getProfitLoss()%></td>
				<td><%=holding.getCurrency()%></td>
			</tr>
			<%
				}
			%>

		</table>
</body>
</html>
