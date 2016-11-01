 <%@ page 
	import="com.fdm.wealthnow.common.User" %>
 <%@ page 
	import="com.fdm.wealthnow.common.Stock" %>
 <%@ page 
	import="com.fdm.wealthnow.common.StockService" %>	
 <%@ page 
 	import="com.fdm.wealthnow.dao.WatchListDAO" %>	
 <%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<html>
<head>
	<style>
	
	</style>
</head>
<body>
	<h1>Watchlist</h1>
	<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
	<H2>User: <%= currentUser.getFullName() %></H2>
	<H3>Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H3>
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
	<% long user = currentUser.getUserId(); %>
	<input type="hidden" name="userid" value="<%=user%>" /> 	
	<fieldset>
 		Current WatchList ID: ${watchListId} <br>
<%--  		Current WatchList ID: <%= request.getAttribute("watchListId") %> <br> --%>
		Current WatchList Name: ${WatchListName}
<%-- 	    Current WatchList Name: <%= request.getAttribute("watchListName") %> --%>
	</fieldset>
	
	<fieldset>
		<legend><strong>Watchlist Tools Bar</strong></legend>
			<div id = "watchlist">
			Watchlist Name: <input type = "text" name = "WatchListName"> <br>
			<input type = "button" value = "Add WatchList" onclick = ""> 
			<input type = "button" value = "Remove WatchList" onclick = ""> <br> <br>
		
			Stocks: <input type = "text" name = "stockname" value = ""> 
			<input type = "button" value = "Add to Current WatchList" onclick = ""> 
			<input type = "button" value = "Remove from Current WatchList" onclick=""> <br><br>
		
			<select>
					<option value = "Select WatchLists"> Select WatchLists </option>
			</select>
		
			<select>
					<option value = "WatchLists Type"> Type </option>
					<option value = "WatchLists Type"> Global </option>
					<option value = "WatchLists Type"> Private </option>
			</select>
			</div> <br>
	</fieldset>
	
	<fieldset>
		<legend><strong>Watchlist</strong></legend>
			<div id = "righttab">
				<select>
					<option value = "WatchLists"> WatchLists </option>
				</select>
			</div>
		
			<div id = "watchlistmain">
			</div>
	</fieldset>
</body>
</html>