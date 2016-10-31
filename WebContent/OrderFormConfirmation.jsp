<%@page import = "com.fdm.wealthnow.common.Order"  %>
<%@page import = "java.util.List"%>

<html>
<head>
	<title> Order Confirmation</title>
	<h1> Confirmation </h1>
	
	<% Order order = (Order) request.getAttribute("fieldList"); %>
	
		<% out.println(request.getAttribute("fieldList"));%>
	
</head>
<body>

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

<form method="GET" action="OrderProcessor" >
<input type="hidden" id="OrderParameter1" name="userID" value="<%=order.getUserID()%>">
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