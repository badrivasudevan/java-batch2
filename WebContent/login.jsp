<html>
	<head>
			<title>Login Page</title>
		</head>
		<body>
			<h1>Login Page</h1>
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