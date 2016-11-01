<%@ page import="com.fdm.wealthnow.common.User"%>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="com.fdm.wealthnow.dao.HoldingDAO"%>
<%@ page import="com.fdm.wealthnow.common.Holding"%>


<html>


<form action="PortfolioViewerController" method="post">

<table id="portfolio_stocks" class="table table-striped">
 
<tr>
<th colspan="2">Stock Symbol</th>
<th>Quantity</th>
<th>Price Paid</th>
<th>Currency</th>

</tr>
<tr>
<th></th>
<th></th>
<th>$</th>
<th>$</th>
</tr>

<%
List<StockHolding> shList = pfs.getPortfolioInStockHolding(user_id);
for (StockHolding newShList : shList) {
System.out.println("List $$$$$$$$" + newShList);

String stock_symbol = newShList.getStock_symbol();
Integer order_id = newShList.getOrder_id();
Integer quantity = newShList.getRemaining_quantity();
%>

</table>
</form>
</html>
	