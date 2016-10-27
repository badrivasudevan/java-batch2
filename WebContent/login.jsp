<html>
	<head>
			<title>Login Page</title>
		</head>
		<body>
			<%
		    	if(request.getAttribute("errorMessage") != null) {
  		      		out.println(request.getAttribute("errorMessage"));
    			}
			%>

			<form action="LoginController" method="POST"> 
			   <br> User name 
				<input type="text" name="username"/>
				<br> Password 
				<input type="password" name="password"/>
				<input type="submit" value="submit">
			</form>
		</body>
</html>