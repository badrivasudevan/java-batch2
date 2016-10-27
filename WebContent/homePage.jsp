 <%@ page 
	import="com.fdm.wealthnow.common.UserAuth" %>
<html>
	<head>
			<title>Home Page</title>
		</head>
		<body>
		 	<% UserAuth currentUser = (UserAuth) (session.getAttribute("loggedInUser"));%>
			<H1> Welcome <%= currentUser.getFullName() %></H1>
		</body>
</html>