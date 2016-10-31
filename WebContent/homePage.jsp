 <%@ page 
	import="com.fdm.wealthnow.common.User" %>
<html>
	<head>
			<title>Home Page</title>
	</head>
	<body>
		 	<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
			<H1> Welcome <%= currentUser.getFullName() %></H1>
			<H2> Balance <%= UserDAO.getBalance(UserDAO.getUser(session.getAttribute("loggedInUser"))) %></H2>
			
			
			<fieldset>
				<legend>Trading Platform Quick Links</legend>
					<form action = post method >
						<a href="">Account Balance</a>
						<a href="WatchList.jsp">Watchlists</a>
						<a href="">Visit our HTML tutorial</a>
					</form>
			</fieldset>
	</body>
</html>