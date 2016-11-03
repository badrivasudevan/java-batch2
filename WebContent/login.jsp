<html>
   <style>
    html {
        background: url(https://s15.postimg.org/dijziymt7/FDM1.jpg);
        background-size: cover;
    }
     body {
        font-size: 16px;
        margin: 25;
        color: white;
    }
    </style>
	<head> 
			<title>Login Page</title>
		</head>
		<body>
			<h1>Login Page</h1>
			
			<%	if(request.getAttribute("success") != null) {	 %>
	  		   <script>alert("The account is successfully created. You can access the trading platform now!");</script>
	    <%		}
		%>
			
		<fieldset style = "width: 200px">
			<form action="LoginController" method="POST"> 
			   <br> User name 
				<input type="text" name="username"/>
				<br> Password 
				<input type="password" name="password"/> <br><br>
				<input type="submit" value="Submit">
				
			</form>
			
				<form action="registration.jsp" method="get">
				<button>New User</button>
				</form>
		</fieldset>

			<%
		    	if(request.getAttribute("errorMessage") != null) { %>
  		      		<!--  out.println(request.getAttribute("errorMessage"));  -->
  		      	<script>alert("Invalid username or password");</script>
		  <% 	}
			%>
		</body>
</html>