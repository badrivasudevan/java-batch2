package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fdm.wealthnow.backendService.Formatter;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.OrderDAO;

/**
 * Servlet implementation class OrderConfirmation
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long userID = Long.parseLong(request.getParameter("userID"));
		TransactionType transacType = Formatter.formatTransacType(request.getParameter("transacType"));
		int orderQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
		String stockSymbol = request.getParameter("stockSymbol");
		System.out.println("Printing term: " +request.getParameter("term"));
		Term term = Term.valueOf(request.getParameter("term"));
		PriceType priceType = Formatter.formatPriceType(request.getParameter("priceType"));
		double priceExecuted = Double.parseDouble(request.getParameter("priceExecuted"));
		OrderStatus orderStatus = OrderStatus.Pending;
	
		
		
		//double priceExecuted = Double.parseDouble(request.getParameter("priceExecuted"));
		
		System.out.println("Creating new order");
		Order newOrder = new Order(userID, transacType, orderQuantity, stockSymbol, term, priceType, priceExecuted, orderStatus);
		System.out.println(newOrder);
		
		
		try {
		System.out.println("Storing order");	
		OrderDAO.storeOrder(newOrder);
		}
		catch (SQLException e) {System.out.println("doing catch");	
}
		//System.out.println("Executing OrderDAO Store Order.");
		request.getRequestDispatcher("/homePage.jsp").forward(request, response);

	}
	
}
