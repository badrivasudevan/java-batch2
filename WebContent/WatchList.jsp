<%@ page import="com.fdm.wealthnow.common.User" %>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.WatchListDAO" %>	
<%@ page import="com.fdm.wealthnow.common.Stock" %>
<%@ page import="com.fdm.wealthnow.common.StockService" %>
<%@ page import="com.fdm.wealthnow.controller.ViewWatchListController" %>	
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE>
<html>
<head>
<% String w_id = (String) request.getAttribute("watchlistid"); %>
<% List<String> listsymbol = (List<String>) WatchListDAO.retrieveAllStockForWatchlist(w_id); %>
<% List<String> liststockfmyahoo = (List<String>) StockService.getStockFromWeb(listsymbol); %>
<% List<Stock> liststock = StockService.stockStorage(liststockfmyahoo);%>
<% HashMap<String,Stock> stockhashmap = StockService.createHashMap(liststock); %>
</head>
<body>
	<% out.println(stockhashmap.get(listsymbol.get(0))); %>
	<h1>Watchlist - Stocks</h1>
		<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
	<H2>User: <%= currentUser.getFullName() %></H2>
	
	<fieldset id style = "width: 210px">
			<legend><strong>Trading Platform Quick Links</strong></legend>
					<form action = post method >
						<a href="balancePage.jsp">Account Balance</a> <br>
						<a href="WatchList.jsp">View Watchlists</a> <br>
						<a href="WatchListAdd.jsp">WatchList - Add</a> <br>
						<a href="WatchListAddStocks.jsp">Watchlists - Add Stocks</a> <br>
						<a href="PortfolioView.jsp">User Portfolio Viewer</a> <br>
						<a href="OrderForm.jsp">Order Form</a> <br>
						<a href="logout.jsp">Logout</a>
					</form>
	</fieldset>
	
	<fieldset>
		<form action="${pageContext.request.contextPath}/ViewWatchListController" method="POST">		
		<% long user = currentUser.getUserId(); %>
		<input type="hidden" name="userid" value="<%=user%>" />
		
		Select Watchlist: <select name="Watchlist">
			<% WatchListDAO watchlist = new WatchListDAO();
			 for(String s : watchlist.retrieveWatchlist(user).values()) { %>
			<tr>
				<option value="<%=s %>" style="width:100px;" > <%=s %> </option>
			</tr>	
			<% } %>
		</select>
	</fieldset>
			<% for(String s : stockhashmap.keySet()) { %>
		<tr>
			<td><%= stockhashmap.get(s).getName() %> </td>
			<td><%= stockhashmap.get(s).getSymbol() %> </td>
			<td><%= stockhashmap.get(s).getBidprice() %> </td>
			<td><%= stockhashmap.get(s).getAskprice() %> </td>
			<td><%= stockhashmap.get(s).getCurrentmarketprice() %> </td>
			<td><%= stockhashmap.get(s).getUpdatedtime() %> </td>		
		</tr>	
			<% } %>
	
	<a href="WatchListAdd.jsp">WatchList - Add</a> <br>
	<a href="WatchListAddStocks.jsp">Watchlists - Add Stocks</a>
	
</body>
</html>