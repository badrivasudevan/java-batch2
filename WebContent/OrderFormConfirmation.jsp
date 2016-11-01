<%@page import = "com.fdm.wealthnow.common.Order"  %>
<%@page import = "java.util.List"%>
<%@ page import="com.fdm.wealthnow.common.User"%>
 <%@ page 
	import="com.fdm.wealthnow.common.User" %>
 <%@ page 
 	import="com.fdm.wealthnow.dao.UserDAO" %>
<html>
<head>
	<title> Order Confirmation</title>
	
	
	<% Order order = (Order) request.getAttribute("fieldList"); %>
	

		<% User currentUser = (User) (session.getAttribute("loggedInUser"));%>
	
</head>
<body>
			<H1> Welcome <%= currentUser.getFullName() %></H1>
			<H2> Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H2>
			
			<fieldset id style = "width: 210px">
			<legend><strong>Trading Platform Quick Links</strong></legend>
					<form action = post method >
						<a href="balancePage.jsp">Account Balance</a> <br>
						<a href="WatchList.jsp">Watchlists</a> <br>
						<a href="PortfolioView.jsp">User Portfolio Viewer</a>  <br>
						<a href="OrderForm.jsp">Order Form</a> <br>
						<a href="logout.jsp">Logout</a>
					</form>
			</fieldset>
	<h3> Details of Order </h3>	
<p>
Stock: <%= order.getStockSymbol() %> <br>
Transaction Type:  <%= order.getTransacType() %><br>
Quantity: <%= order.getOrderQuantity() %><br>
Price Type: <% if(order.getPriceType().toString().equalsIgnoreCase("stoploss"))
				out.println("Stop Loss");
				else
					out.println(order.getPriceType());%><br>
					
Price Executed: <% if(order.getPriceExecuted() == 0)
					out.println("Market Price");
					else
						out.println(order.getPriceExecuted());%> <br>
						
						

Term: <% if (order.getTerm().toString().equalsIgnoreCase("GoodForDay"))
			out.println("Good Til Day (GTD)");
			else
				out.println("Good Til Canceled (GTD)");%>
		

</p>

<form method="POST" action="OrderConfirmation" >
<input type="hidden" id="OrderParameter1" name="userID" value="<%=currentUser.getUserId()%>">
<input type="hidden" id="OrderParameter2" name="transacType" value="<%=order.getTransacType()%>">
<input type="hidden" id="OrderParameter3" name="orderQuantity" value="<%=order.getOrderQuantity()%>">
<input type="hidden" id="OrderParameter4" name="stockSymbol" value="<%=order.getStockSymbol()%>">
<input type="hidden" id="OrderParameter5" name="term" value="<%=order.getTerm()%>">
<input type="hidden" id="OrderParameter6" name="priceType" value="<%=order.getPriceType()%>">
<input type="hidden" id="OrderParameter7" name="priceExecuted" value="<%=order.getPriceExecuted()%>">
<input type="hidden" id="OrderParameter8" name="orderStatus" value="<%=order.getOrderStatus()%>">
<input type="submit" value="Confirm" />
</form>
</body>
</html>