<%@ page import="com.fdm.wealthnow.common.User" %>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.WatchListDAO" %>	
<%@ page import="com.fdm.wealthnow.common.Stock" %>
<%@ page import="com.fdm.wealthnow.common.StockService" %>
<html>
<head>
	<style>
	
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
		<legend><strong>Watchlist Stocks Tool</strong></legend>
	<form action="${pageContext.request.contextPath}/StockController" method="POST">
		<% long user = currentUser.getUserId(); %>
		<input type="hidden" name="userid" value="<%=user%>" /> 
		
		
	<%	if(request.getAttribute("errorMessage3") != null) {	 %>
	     <!-- out.println(request.getAttribute("errorMessage")); -->
	   <script>alert("No such Stock in the Singapore Stock Exchange (SGX) to be added!");</script>
	<%		}
	%>
	<%	if(request.getAttribute("errorMessage4") != null) {	 %>
	     <!-- out.println(request.getAttribute("errorMessage")); -->
	   <script>alert("No such Stock in the watchlist! It cannot be removed! Try again!");</script>
	<%		}
	%>

	<%	if(request.getAttribute("errorMessage5") != null) {	 %>
	     <!-- out.println(request.getAttribute("errorMessage")); -->
	   <script>alert("The stock is already in the watchlist! It cannot be added! Try again!");</script>
	<%		}
	%>
	
	<%	if(request.getAttribute("addMessage") != null) {	 %>
	     <!-- out.println(request.getAttribute("errorMessage")); -->
	   <script>alert("The stock is added into the watchlist!");</script>
	<%		}
	%>
	<%	if(request.getAttribute("rmMessage") != null) {	 %>
	     <!-- out.println(request.getAttribute("errorMessage")); -->
	   <script>alert("The stock is removed from the watchlist!");</script>
	<%		}
	%>
	
		
		Stocks: <input type = "text" name = "stockname">  
			<select name="addorremovestock">
				<option selected disabled>Please Choose</option>
				<option value="+">Add to WatchList</option>
				<option value="-">Remove from WatchList</option>
			</select>
			
			
		Watchlist: <select name="watchlist">
			<option selected disabled>Please Choose WatchLists</option>
			<% WatchListDAO watchlistaorm = new WatchListDAO();
	 		for(String s : watchlistaorm.retrieveWatchlist(user).values()) { %>
			<tr>
				<option value="<%=s %>" style="width:100px;" > <%=s %> </option>
			</tr>	
			<% } %>
		</select>
			<button type="submit">Submit</button> <br><br>
	</fieldset>
	</form>	
</body>
</html>