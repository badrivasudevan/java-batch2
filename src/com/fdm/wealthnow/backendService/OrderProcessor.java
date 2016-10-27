package com.fdm.wealthnow.backendService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.Orders;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private long createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession session = request.getSession(true);
//        session.setAttribute("loggedInUser", user);
		
		long userID =  Long.parseLong(request.getParameter("userID"));
		TransactionType transacType = Formatter.formatTransacType(request.getParameter("transacType"));
		int orderQuantity = Integer.parseInt(request.getParameter("quantity"));
		String stockSymbol = request.getParameter("symbol");
		Term term = Formatter.formatTerm(request.getParameter("term")); 
		PriceType priceType = Formatter.formatPriceType(request.getParameter("priceType")); 
		double priceExecuted = Double.parseDouble(request.getParameter("priceExecuted"));
		OrderStatus orderStatus = OrderStatus.Pending;
		
		Orders newOrder = new Orders(userID, transacType, orderQuantity, stockSymbol, term, priceType, priceExecuted, orderStatus);
		
		return newOrder.getOrderID();
	}
	
	

}
