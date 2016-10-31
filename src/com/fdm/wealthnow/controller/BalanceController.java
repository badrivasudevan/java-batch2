package com.fdm.wealthnow.controller;

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

import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.UserDAO;
/**
 * Servlet implementation class BalanceController
 */
@WebServlet("/BalanceController")
public class BalanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BalanceController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("Unsupported operation");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		float fund = Float.parseFloat(request.getParameter("fund"));
		String sign = request.getParameter("operator");
		float result = 0;
		float bal = Float.parseFloat(request.getParameter("bal"));
		String user_errormsg = "Not Enough Funds to withdraw!";
		long userId = Long.parseLong(request.getParameter("userid"));
		switch(sign) {
		case "+":{
			result = bal + fund;
			try {
				UserDAO.addBalance(userId, fund);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "-":{	
			if (bal < fund) {
		         System.out.println("Not enough cash to withdraw...");
		         request.setAttribute("errorMessage", user_errormsg); 	
		         request.getRequestDispatcher("/balancePage.jsp").forward(request, response); 
		         break;
		    } 
		         else {
		        	result = bal - fund;
		        	try {
						UserDAO.deductBalance(userId, fund);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	break;
		         }	
		}
		}
		
		request.setAttribute("result", result);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/balancePage.jsp");
		dispatcher.forward(request, response);

	}
	
}