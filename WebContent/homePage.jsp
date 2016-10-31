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
						<a href="http://www.w3schools.com/html/">Account Balance</a>
						<a href="http://www.w3schools.com/html/">Watchlists</a>
						<a href="http://www.w3schools.com/html/">Visit our HTML tutorial</a>
					</form>
			</fieldset>
	</body>
</html>