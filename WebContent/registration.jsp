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
	<style>
	
	</style>
</head>
<body>
	<form action="${pageContext.request.contextPath}/RegistrationController" method="POST">	
	Username : <input type="text" name="username" /> <br>
	Full name : <input type="text" name="fullname" /> <br>
	Password : <input type="password" name="password" /> <br>
	Confirm Password : <input type="confirmpassword" name="confirmpassword" /> <br>
		
	<fieldset>
		<%	if(request.getAttribute("errorMessage") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("The passwords do not match! Please try again!");</script>
	    <%		}
		%>
		<%	if(request.getAttribute("removewatchlist") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("removewatchlist");</script>
	    <%		}
		%>
		<%	if(request.getAttribute("errorMessage2") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("The watchlist is already in the database!");</script>
	    <%		}
		%>
		<%	if(request.getAttribute("addwatchlist") != null) {	 %>
	  		     <!-- out.println(request.getAttribute("errorMessage")); -->
	  		   <script>alert("addwatchlist");</script>
	    <%		}
		%>

		<button type="submit">Submit</button> <br><br>
		</form>
	</fieldset>
	
</body>
</html>