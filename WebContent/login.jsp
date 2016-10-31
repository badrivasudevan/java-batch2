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
		<fieldset style = "width: 200px">
			<form action="LoginController" method="POST"> 
			   <br> User name 
				<input type="text" name="username"/>
				<br> Password 
				<input type="password" name="password"/> <br><br>
				<input type="submit" value="submit">
			</form>
		</fieldset>
		</body>
</html>