package com.fdm.wealthnow.controller;

import java.io.IOException;
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
		try {
		 String userName = request.getParameter("username");
         User userId = UserDAO.getUser(userName);    
         float bal = UserDAO.getBalance(userId.getUserId());
         HttpSession session = request.getSession(true);
         session.setAttribute("bal", bal);
         request.getRequestDispatcher("homePage.jsp").forward(request, response); 
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
		
		//setattribute
	}

}