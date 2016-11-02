
<%@ page import="com.fdm.wealthnow.common.User"%>
<%@ page import="com.fdm.wealthnow.dao.UserDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.fdm.wealthnow.backendService.OrderService"%>
<%@ page import="com.fdm.wealthnow.backendService.Formatter"%>

<!--  -->

<html>

<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"> </script>
<script>
    $(document).ready(function() {
        $('#PriceTypeBuy').on('change', function() { //# is ID
            if (this.value == 'Limit') {
                $("#Limit-Buy").show(); //# is div
            } else {
                $("#Limit-Buy").hide();
            }
        });

        $('#PriceTypeSell').on('change', function() { //# is ID
            if (this.value == 'Limit') {
                $("#Limit-Sell").show(); //# is div
            } else {
                $("#Limit-Sell").hide();
            }

        });

        $('#PriceTypeSell').on('change', function() { //# is ID
            if (this.value == 'Stoploss') {
                $("#Stop-loss").show(); //# is div
            } else {
                $("#Stop-loss").hide();
            }
        });
    });
    </script>
<script type="text/javascript">
    window.onload = function() {
        document.getElementById('buyChecked').style.display = 'none';
        document.getElementById('sellChecked').style.display = 'none';

    }

    function OrderTypeCheck() {
        if (document.getElementById('transactionTypeBuy').checked) {
            document.getElementById('buyChecked').style.display = 'block';
        } else {
            document.getElementById('buyChecked').style.display = 'none';
        }

        if (document.getElementById('transactionTypeSell').checked) {
            document.getElementById('sellChecked').style.display = 'block';
        } else {
            document.getElementById('sellChecked').style.display = 'none';
        }

    }   
    </script>
<!--     <link rel="stylesheet" type="text/css" href="./css/testpage1.css">-->
</head>

<body>
<% 		User currentUser = (User) (session.getAttribute("loggedInUser"));
		ArrayList<String> stockSymbols = OrderService.callDAO(currentUser.getUserId());
		ArrayList<String> sgxSymbols = Formatter.sgxstock();
%>	
	
	<H1>
		Welcome
		<%= currentUser.getFullName() %></H1>
	<H2>
		Balance : $<%= UserDAO.getBalance(currentUser.getUserId()) %></H2>

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
	<h3>Stock Order Form</h3>

	<form method="POST" action="OrderProcessor">
	
		<%-- <label for="stock">Stocks</label>

 		
		<select name="STOCKS">
			<%for(String stocksList:stockSymbols)  
    { %>
			<option><%=stocksList%></option>

			<% } 
    %>  --%>
	<!-- 	</select> <input type="text" id="symbol" name="symbol" value="<symbol>"></select> -->
		
		<p>
			<input type="radio" name="transactionType" value="Buy"
			id=transactionTypeBuy onclick="javascript:OrderTypeCheck();">
			<label for="transactionTypeBuy">Buy</label>
			<input type="radio"	name="transactionType" value="Sell" 
			id=transactionTypeSell onclick="javascript:OrderTypeCheck();"> <label
				for="transactionTypeSell">Sell</label>
		</p>
		
<%-- 		<div id="sellChecked">
		<select id="PriceTypeSell" name="STOCKS">
			<%for(String stocksList:stockSymbols){ %>
			<option><%=stocksList%></option><% } %> 
			</select>
		</div> --%>
		 
		<div id="buyChecked">
		
			<select id="symbol" name="symbolBuy">
			<%for(String stocksList:sgxSymbols){ %>
			<option name=<%=stocksList%> value="<%=stocksList%>" id=<%=stocksList%>><%=stocksList%></option><% } %> 
			</select>
		<%-- <input type="text" id="symbol" name="symbolBuy" value="<symbol>"> --%>
		
			<select id="PriceTypeBuy" name="priceTypeBuy">
				<option name="Market" value="Market">Market Price</option>
				<option name="Limit" value="Limit" id="limit">Limit</option>
			</select>
		</div>
		<div id="sellChecked">
		
			<select id="symbol" name="symbolSell">
			<%for(String stocksList:stockSymbols){ %>
			<option name=<%=stocksList%> value="<%=stocksList%>" id=<%=stocksList%>><%=stocksList%></option><% } %> 
			</select>
			
			<select id="PriceTypeSell" name="priceTypeSell">
				<option name="Market" value="Market">Market Price</option>
				<option name="Stoploss" value="Stoploss" id="stoploss">
					Stop Loss</option>
				<option name="Limit" value="Limit" id="limit">Limit</option>
			</select>
		</div>
		<div id="Limit-Buy" style='display: none;'>
			<label for="limitBuy">Buy limit</label> <input type="number"
				id="limitBuy" name="limitBuy">
		</div>
		<div id="Limit-Sell" style='display: none;'>
			<label for="limitSell">Sell limit</label> <input type="number"
				id="limitSell" name="limitSell">
		</div>
		<div id="Stop-loss" style='display: none;'>
			<label for="Stop-loss">Stop Loss</label> <input type="number"
				id="stopLoss" name="stopLoss">
		</div>
		<label for="quantity">Quantity</label> <input id="quantity"
			type="number" style="width: 50px;" name="quantity">
		<p>
			<label for="Term">Term</label> <select name="term">
				<option value="GoodTilCanceled" name="goodTilCanceled">
					Good 'Til Canceled</option>
				<option value="GoodTilDay" name="goodTilDay">Good 'Til Day
				</option>
			</select>
		</p>
		<input type="submit" value="Submit" />
	</form>
    
    <% 	if(request.getAttribute("notenoughcash") != null) { %>
  		      		 <!-- out.println(request.getAttribute("errorMessage")); -->
  		      	<script>alert("Not enough cash");</script>
		  <% 	}
			%>
	
	    <%
		    	if(request.getAttribute("notenoughquantity") != null) { %>
  		      		<!--  out.println(request.getAttribute("errorMessage"));  -->
  		      	<script>alert("Not enough quantity");</script>
		  <% 	}
			%>
			
				    <%
		    	if(request.getAttribute("noholding") != null) { %>
  		      		<!--  out.println(request.getAttribute("errorMessage"));  -->
  		      	<script>alert("No such stock in portfolio");</script>
		  <% 	}
			%>			
			
    

</body>

</html>
