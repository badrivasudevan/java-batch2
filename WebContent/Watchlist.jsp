<%@ page import="com.fdm.wealthnow.common.User" %>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.WatchListDAO" %>	
<%@ page import="com.fdm.wealthnow.common.Stock" %>
<%@ page import="com.fdm.wealthnow.common.StockService" %>	
<!DOCTYPE>
<html>
<head>
</head>
<body>
	<h1>Watchlist - Stocks</h1>
		<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
	<H2>User: <%= currentUser.getFullName() %></H2>
	
	<fieldset>
		<form action="${pageContext.request.contextPath}/StockController" method="POST">		
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
	
	
</body>
</html>