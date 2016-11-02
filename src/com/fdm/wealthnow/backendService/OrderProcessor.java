package com.fdm.wealthnow.backendService;


import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.common.UserAuth;

/**
 * Servlet implementation class OrderProcessor
 */
@WebServlet("/OrderProcessor")
public class OrderProcessor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProcessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		double priceExecuted = 0;
		StockService s1 = new StockService();
		List<String> list = new ArrayList<>(); 
		String stockSymbol = null;
		User currentUser = (User) session.getAttribute("loggedInUser");
		long userID = currentUser.getUserId();
		TransactionType transacType = Formatter.formatTransacType(request.getParameter("transactionType"));
		if (transacType == TransactionType.Buy)
		stockSymbol = request.getParameter("symbolBuy");
		if (transacType == TransactionType.Sell)
			stockSymbol = request.getParameter("symbolSell");
		Term term = Formatter.formatTerm(request.getParameter("term"));
		PriceType priceType = null;
		if (request.getParameter("transactionType").equals("Buy")) {
			priceType = Formatter.formatPriceType(request.getParameter("priceTypeBuy"));
			
			list.add(stockSymbol);
			priceExecuted = StockService.stringToDouble(s1.stockStorage(s1.getStockFromWeb(list)).get(0).getAskprice());

		}
		if (request.getParameter("transactionType").equals("Sell")) {
			priceType = Formatter.formatPriceType(request.getParameter("priceTypeSell"));
			
			list.add(stockSymbol);
			priceExecuted = StockService.stringToDouble(s1.stockStorage(s1.getStockFromWeb(list)).get(0).getBidprice());

		}
	
		String limitBuy = request.getParameter("limitBuy");
		String limitSell = request.getParameter("limitSell");
		String stopLoss = request.getParameter("stopLoss");
	
		int orderQuantity = Integer.parseInt(request.getParameter("quantity"));

		try { 
		if (limitBuy !="") {
			priceExecuted = Double.parseDouble(limitBuy);
		}
	
		if (limitSell != "") {
			priceExecuted = Double.parseDouble(limitSell);
		}
		
		if (stopLoss != "") {
			priceExecuted = Double.parseDouble(stopLoss);
		}
		
		}
		catch (NumberFormatException e) {};
		
		
		OrderStatus orderStatus = OrderStatus.Pending;
		
		Order newOrder = new Order(userID, transacType, orderQuantity, stockSymbol, term, priceType, priceExecuted, orderStatus);
		
		Boolean enoughCash = false;
		Boolean enoughQuantity = false;
		Boolean hasHolding = false;
		
		try {
			enoughCash = OrderService.validateCashBalance(newOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			enoughQuantity = OrderService.validateOwnedQuantity(newOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			hasHolding = OrderService.hasHolding(newOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(transacType == TransactionType.Buy){
			if(!enoughCash){
			OrderService errorOrder = new OrderService("not enough cash");
			request.setAttribute("notenoughcash", errorOrder.getErrorMsg());
			request.getRequestDispatcher("OrderForm.jsp").forward(request, response);
			}
		}
		else{
			
			if(!hasHolding){
			OrderService errorOrder = new OrderService("no holding");
			request.setAttribute("noholding", errorOrder.getErrorMsg());
			request.getRequestDispatcher("OrderForm.jsp").forward(request, response);
			}
			else if(hasHolding && !enoughQuantity){
			OrderService errorOrder = new OrderService("not enough quantity");
			request.setAttribute("notenoughquantity", errorOrder.getErrorMsg());
			request.getRequestDispatcher("OrderForm.jsp").forward(request, response);
		}
	}

		if((enoughCash && transacType == TransactionType.Buy)|| 
				enoughQuantity && transacType == TransactionType.Sell || 
				hasHolding && transacType == TransactionType.Sell){
			request.setAttribute("fieldList", newOrder);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderFormConfirmation.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
}
