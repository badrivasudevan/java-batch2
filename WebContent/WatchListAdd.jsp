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
	<h1>WatchList</h1>
	<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
	<H2>User: <%= currentUser.getFullName() %></H2>
	<H3>Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H3>
	
	<fieldset id style = "width: 210px">
			<legend><strong>Trading Platform Quick Links</strong></legend>
					<form action = post method >
						<a href="balancePage.jsp">Account Balance</a> <br>
						<a href="WatchListAdd.jsp">WatchList - Add</a> <br>
						<a href="WatchListAddStocks.jsp">Watchlists - Add Stocks</a> <br>
						<a href="PortfolioView.jsp">User Portfolio Viewer</a> <br>
						<a href="OrderForm.jsp">Order Form</a> <br>
						<a href="logout.jsp">Logout</a>
					</form>
	</fieldset>
			
	<form action="${pageContext.request.contextPath}/WatchListController" method="POST">		
	<% long user = currentUser.getUserId(); %>
	<input type="hidden" name="userid" value="<%=user%>" /> 	
	<fieldset>
		<%	if(request.getAttribute("errorMessage") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("No such watchlist in the database!");</script>
	    <%		}
		%>
		<%	if(request.getAttribute("removewatchlist") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("removewatchlist");</script>
	    <%		}
		%>
		<%	if(request.getAttribute("errorMessage2") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("The watchlist is already in the database!");</script>
	    <%		}
		%>
		<%	if(request.getAttribute("addwatchlist") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("addwatchlist");</script>
	    <%		}
		%>
		
		<legend><strong>Watchlist Tools Bar</strong></legend>
			<div id = "watchlist">
				Watchlist Name: <input type = "text" name = "WatchListName">
			 	<select name="addorremove">
				 	<option selected disabled>Please Choose</option>
					<option value="+">Add WatchList</option>
					<option value="-">Remove WatchList</option>
				</select>
				<button type="submit">Submit</button> <br><br>
		</form>
		</div> <br>
	</fieldset>
	
</body>
</html>