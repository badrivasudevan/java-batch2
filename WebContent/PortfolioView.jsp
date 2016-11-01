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
			
		
/* 	List<Holding> holdingList = (List<Holding>) request
			.getAttribute("holdingList"); */
//out.println("holdingList: "+holdingList.size());
%>
	
	<H1>
		User:<%=currentUser.getFullName()%></H1>
	<H2>
		Balance : $<%=UserDAO.getBalance(currentUser.getUserId())%></H2>
	<fieldset id style = "width: 210px">
			<legend><strong>Trading Platform Quick Links</strong></legend>
					<form action = post method >
						<a href="balancePage.jsp">Account Balance</a> <br>
						<a href="WatchList.jsp">Watchlists</a> <br>
						<a href="PortfolioView.jsp">User Portfolio Viewer</a> <br>
						<a href="OrderForm.jsp">Order Form</a> <br>
						<a href="logout.jsp">Logout</a>
					</form>
			</fieldset>

		<table id="portfolio_holdings" class="table table-striped">

			<tr>
				<th>Stock Symbol</th>
				<th>Quantity</th>
				<th>Price Paid</th>
				<th>Currency</th>

			</tr>

			<%
				for (Holding holding : holdingList) {
			%>
			<tr>
				<td><%=holding.getStockSymbol()%></td>
				<td><%=holding.getRemainingQuantity()%></td>
				<td><%=holding.getPricePaid()%></td>
				<td><%=holding.getCurrency()%></td>
			</tr>
			<%
				}
			%>

		</table>
		<input type="submit" value="Refresh" />

	</form>
</body>
</html>
