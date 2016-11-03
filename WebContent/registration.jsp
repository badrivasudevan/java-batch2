<%@ page 
	import="com.fdm.wealthnow.common.User" %>
 <%@ page 
	import="com.fdm.wealthnow.common.Stock" %>
 <%@ page 
	import="com.fdm.wealthnow.common.StockService" %>	
 <%@ page 
 	import="com.fdm.wealthnow.dao.WatchListDAO" %>	
 <%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<html>
<head>
	
</head>
<body>
	<form action="${pageContext.request.contextPath}/RegisterController" method="POST">	
	
	<fieldset>
		<legend><strong>Registration Page</strong></legend>
	Username : <input type="text" name="username" /> <br> <br>
	Full name : <input type="text" name="fullname" /> <br> <br>
	Password : <input type="password" name="password" /> <br> <br>
	Confirm Password : <input type="password" name="confirmpassword" /> <br> <br>
		
		<%	if(request.getAttribute("errorMessage") != null) {	 %>
	  		   <script>alert("The passwords do not match! Please try again!");</script>
	    <%		}
		%>
		<%	if(request.getAttribute("errorMessage2") != null) {	 %>
	  		   <script>alert("The username is already in use! Please try again!");</script>
	    <%		}
		%>

		<button type="submit">Submit</button>
		<button type="reset" value="Reset">Reset</button>
	</fieldset>	
	</form>
	
		<form action="login.jsp" method="get">
		<button>Home Page</button>
		</form>
		
</body>
</html>