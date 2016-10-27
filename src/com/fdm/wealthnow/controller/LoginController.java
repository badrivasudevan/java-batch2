package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.UserAuth;
import com.fdm.wealthnow.dao.UserDAO;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginController() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("Unsupported operation");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
		 String userName = request.getParameter("username");
         String password = request.getParameter("password");
         System.out.println("Authenticating user:" + userName);
             
         UserAuth user = UserDAO.authenticate(userName, password);
         if (user.isAuthenticationSuccessfull()) {
            	// Create a session and send to landing page
         	System.out.println("Authentication succeessful...");
             HttpSession session = request.getSession(true);
             session.setAttribute("loggedInUser", user);
             request.getRequestDispatcher("homePage.jsp").forward(request, response); 
         } 
         else {
         	System.out.println("Authentication failed");
         	request.setAttribute("errorMessage", user.getErrorMsg()); 	
         	request.getRequestDispatcher("login.jsp").forward(request, response); 
         }
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
