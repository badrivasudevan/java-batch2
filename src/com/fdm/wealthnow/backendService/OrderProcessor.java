package com.fdm.wealthnow.backendService;


import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import java.io.IOException;


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
//      session.setAttribute("loggedInUser", user);
		
		User currentUser = (User) session.getAttribute("loggedInUser");
		long userID = currentUser.getUserId();
		TransactionType transacType = Formatter.formatTransacType(request.getParameter("transactionType"));
		String stockSymbol = request.getParameter("symbol");
		Term term = Formatter.formatTerm(request.getParameter("term"));
		//System.out.println("term: " + term);
		PriceType priceType = null;
		if (request.getParameter("transactionType").equals("Buy")) {
			priceType = Formatter.formatPriceType(request.getParameter("priceTypeBuy"));

		}
		if (request.getParameter("transactionType").equals("Sell")) {
			priceType = Formatter.formatPriceType(request.getParameter("priceTypeSell"));

		}
	
		
		String limitBuy = request.getParameter("limitBuy");
		String limitSell = request.getParameter("limitSell");
		String stopLoss = request.getParameter("stopLoss");
		double priceExecuted = 0;
		int orderQuantity = Integer.parseInt(request.getParameter("quantity"));
		
		try { 
		if (limitBuy != null) {
			priceExecuted = Double.parseDouble(limitBuy);
			
		}
	
		
		if (limitSell != null) {
			priceExecuted = Double.parseDouble(limitSell);
		}
		
		if (stopLoss != null) {
			priceExecuted = Double.parseDouble(stopLoss);
			
		}
		
		else
			priceExecuted = 0;
		}
		catch (NumberFormatException e) {};
		
		
		//double priceExecuted = Double.parseDouble(request.getParameter("priceExecuted"));
		OrderStatus orderStatus = OrderStatus.Pending;
		
		Order newOrder = new Order(userID, transacType, orderQuantity, stockSymbol, term, priceType, priceExecuted, orderStatus);
		
		
		request.setAttribute("fieldList", newOrder);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderFormConfirmation.jsp");
		
		dispatcher.forward(request, response);
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
	
/*	private long createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
//        session.setAttribute("loggedInUser", user);
		User currentUser = (User) (session.getAttribute("loggedInUser"));
		
		long userID =  currentUser.getUserId();
		TransactionType transacType = Formatter.formatTransacType(request.getParameter("transactionType"));
		System.out.println(request.getParameter("transactionType"));
		String stockSymbol = request.getParameter("symbol");
		Term term = Formatter.formatTerm(request.getParameter("term")); 
		PriceType priceType = Formatter.formatPriceType(request.getParameter("priceType"));
		
		
		String limitBuy = request.getParameter("limitBuy");
		String limitSell = request.getParameter("limitSell");
		String stopLoss = request.getParameter("stopLoss");
		double marketprice = 0;

		double priceExecuted = 0;
		int orderQuantity = Integer.parseInt(request.getParameter("quantity"));
		
		if (limitBuy != null) {
			priceExecuted = Double.parseDouble(limitBuy);
			
		}
		
		if (limitSell != null) {
			priceExecuted = Double.parseDouble(limitSell);
			
		}
		
		if (stopLoss != null) {
			priceExecuted = Double.parseDouble(stopLoss);
			
		}
		
		else priceExecuted = marketprice;
		
		
		//double priceExecuted = Double.parseDouble(request.getParameter("priceExecuted"));
		OrderStatus orderStatus = OrderStatus.Pending;
		
		Order newOrder = new Order(userID, transacType, orderQuantity, stockSymbol, term, priceType, priceExecuted, orderStatus);
		
		return newOrder.getOrderID();
	}*/
	
/*	public static void main(String[] args) {
		newOrder.getTerm() = 
	}*/

}
