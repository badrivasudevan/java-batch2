 <%@ page 
	import="com.fdm.wealthnow.common.User" %>
<html>
	<head>
			<title>Home Page</title>
	</head>
	<body>
		 	<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
			<H1> Welcome <%= currentUser.getFullName() %></H1>
	</body>
</html>