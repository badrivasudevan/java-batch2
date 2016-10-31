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
		Integer fund = Integer.parseInt(request.getParameter("fund"));
		String sign = request.getParameter("operator");
		Integer result = 0;
		int bal = UserDAO.getBalance(session.getUserId) ;
		switch(sign) {
		case "+":{
			result = bal + fund;
			break;
		}
		case "-":{
//			if(bal < fund){
//			}
//			else {
				result = bal - fund;
				break;
//			}
		}
		}
		
		request.setAttribute("result", result);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("balancePage.jsp");
		dispatcher.forward(request, response);

	}
	
	private float getBalance(long userId) throws Exception{
		float bal = 0;
		bal = UserDAO.getBalance(userId);
		return bal; 
	}

}