<%@ page import="com.fdm.wealthnow.common.User"%>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.HoldingDAO"%>
<%@ page import="com.fdm.wealthnow.common.Holding"%>
<%@ page import="com.fdm.wealthnow.common.Order"%>

<%@ page import="com.fdm.wealthnow.backendService.HoldingService"%>
<%@ page import="com.fdm.wealthnow.backendService.PendingService"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Orders</title>
</head>

    <style>
    .even {
        /*all even rows will be coloured*/
        background-color: #80bfff;
    }
    </style>
<body>
	<fieldset id style = "width: 210px">
			<legend><strong>Trading Platform Quick Links</strong></legend>
					<form action = post method >
						<a href="balancePage.jsp">Account Balance</a> <br>
						<a href="WatchList.jsp">View Watchlists</a> <br>
						<a href="WatchListAdd.jsp">WatchList - Add</a> <br>
						<a href="WatchListAddStocks.jsp">Watchlists - Add Stocks</a> <br>
						<a href="PortfolioView.jsp">User Portfolio Viewer</a> <br>
						<a href="Pending.jsp">Pending Orders</a> <br>
						<a href="OrderForm.jsp">Order Form</a> <br>
						<a href="logout.jsp">Logout</a>
					</form>
	</fieldset>

<% 		User currentUser = (User) (session.getAttribute("loggedInUser"));
		List<Order> pendingList = PendingService.callDAO(currentUser.getUserId());
		//out.println(pendingList);
%>
<h3> Pending Orders</h3>
<form method="POST" action="PendingController">
<table id="portfolio_pending">
			<tr class = "even">
				<th>Select</th>
				<th>Stock Symbol</th>
				<th>Transaction Type</th>
				<th>Quantity</th>
				<th>Price Type</th>
				<th>Term</th>
				<th>Executing Price</th>
				<th>Currency</th>
				<th>Date</th>
				<th>Status</th>
				
			</tr>
			
			
					<%
				for (Order pending : pendingList) {
			%>
			<tr>
				<td><input type="checkbox" id="pendingCheckbox" name ="pendingCheckbox" value =<%=pending.getOrderID() %>></td>
				<td ><%=pending.getStockSymbol()%></td>
				<td><%=pending.getTransacType()%></td>
				<td ><%=pending.getOrderQuantity()%></td>
				<td><%=pending.getPriceType()%></td>
				<%-- <td ><%=pending.getTerm()%></td> --%>
				<td><% if(pending.getTerm().toString().equalsIgnoreCase("GoodUntilCancelled"))
				out.println("GTC");
				else
					out.println("GTD");%></td>
				<td><%=pending.getPriceExecuted()%></td>
				<td >SGD</td>				
				<td><%=pending.getOrderDate()%></td>
				<td ><%=pending.getOrderStatus() %></td>
			</tr>
			<%
				}
			%>
			</table>
			<br>
			<input type = "submit" value = "Remove"> 
</form>

<script>

function SelectedCheck() {
	alert(pending.getOrderID());
<%-- 	if(document.getElementById(<%= pending.getOrderID()%>).checked)
	if(<%= pending.getOrderID()%>.isSelected()) {
	_do_something_if_selected_ --%>
	
	
}

</script>
</body>
</html>

