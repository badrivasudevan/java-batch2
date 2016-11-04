<%@ page import="com.fdm.wealthnow.common.User" %>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.WatchListDAO" %>	
<%@ page import="com.fdm.wealthnow.common.Stock" %>
<%@ page import="com.fdm.wealthnow.common.StockService" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE>
<html>
<head>
	<style>
		table, td, th {
		    border: 1px solid black;
		}
		
		table {
		    
		    width: 900px;
		}
		
		td, th {
		    text-align: center;
		}
		
		th {
			background-color: #80bfff;
		}
	</style>
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
						<a href="PortfolioView.jsp">User Portfolio Viewer</a> <br>
						<a href="OrderForm.jsp">Order Form</a> <br>	
						<a href="Pending.jsp">Pending Orders</a> <br>
						<a href="OrderHistory.jsp">Order History</a> <br>
						<a href="logout.jsp">Logout</a> 
					</form>
	</fieldset>
	
	<form action="${pageContext.request.contextPath}/ViewWatchListController" method="POST">
	<fieldset>		
		<legend><strong>WatchLists</strong></legend>
		<% long user = currentUser.getUserId(); %>
		<input type="hidden" name="userid" value="<%=user%>" />
		
		Select Watchlist : <select name="Watchlist">
			<option selected disabled>Please Choose WatchLists</option>
			<% WatchListDAO watchlist = new WatchListDAO();
			 for(String s : watchlist.retrieveWatchlist(user).values()) { %>
			<tr>
				<option value="<%=s %>" style="width:100px;" > <%=s %> </option >
			</tr>	
			<% } %>
		</select>
		<button type="submit">Submit</button> <br><br>
	
	<% String w_id = (String) request.getAttribute("watchlistid"); %>
	
	<% List<String> listsymbol = (List<String>) WatchListDAO.retrieveAllStockForWatchlist(w_id); %>
	<% List<String> liststockfmyahoo = (List<String>) StockService.getStockFromWeb(listsymbol); %>
	<% List<Stock> liststock = StockService.stockStorage(liststockfmyahoo);%>
	<% HashMap<String,Stock> stockhashmap = StockService.createHashMap(liststock); %>
	<% HashMap<String,String> watchlisthashmap = WatchListDAO.retrieveWatchlist(user); %>
	Current Watchlist : <%= (watchlisthashmap.get(w_id)==null)?"NONE":watchlisthashmap.get(w_id) %>

		<table id="WatchListTable" >
			<tr>
				<th>Stock Name</th>
				<th>Symbol</th>
				<th>Bid Price</th>
				<th>Ask Price</th>
				<th>Current Price</th>
				<th>Date</th>
			</tr>
			
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
	</table>		
	</fieldset>	
	</form>	
	
	<form action="WatchListAdd.jsp" method="get">
	<button>Add WatchLists</button>
	</form>

	<form action="WatchListAddStocks.jsp" method="get">
	<button>Add Stocks in WatchLists</button>
	</form>	<br><br>
	
	
</body>
</html>