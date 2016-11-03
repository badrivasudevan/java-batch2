package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.wealthnow.dao.UserDAO;

public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
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

		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("confirmpassword");
		String user_errormsg = "The passwords do not match!";
		String user_errormsg2 = "The username is already in use. Please use other username!";
		String user_register = "The account is created! You can now access the website now!";
		List<String> userlist = new ArrayList<>();
		try {
			userlist = UserDAO.retrieveAllUserName();
			if(userlist.contains(username)){
				 System.out.println("The username is already exist! Try again!");
				 request.setAttribute("errorMessage2", user_errormsg2);
				 request.getRequestDispatcher("/registration.jsp").forward(request, response);				
			}
			else {
				if(password1.equals(password2)) {		
					 try {
						UserDAO.newUser(username, fullname, password1);
						System.out.println("The username is already exist! Try again!");
						request.setAttribute("success", user_register);
						request.getRequestDispatcher("/registration.jsp").forward(request, response);				
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					 System.out.println("The passwords do not match! Try again!");
					 request.setAttribute("errorMessage", user_errormsg);
					 request.getRequestDispatcher("/registration.jsp").forward(request, response);
				}			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	//yo

}


