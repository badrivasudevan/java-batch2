package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.wealthnow.dao.UserDAO;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
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
		//doGet(request, response);

		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("confirmpassword");
		String user_errormsg = "The passwords do not match!";
		String user_errormsg2 = "The username is already in use. Please use other username!";
		String user_errormsg3 = "The password cannot be empty. Please enter the password!";
		String user_errormsg4 = "The username cannot be empty. Please enter the username!";
		String user_register = "The account is created! You can access the website now!";
		List<String> userlist = new ArrayList<>();
		try {
			userlist = UserDAO.retrieveAllUserName();
			if(userlist.contains(username)){
				if(!username.isEmpty()){
				 System.out.println("The username is already exist! Try again!");
				 request.setAttribute("errorMessage2", user_errormsg2);
				 request.getRequestDispatcher("/registration.jsp").forward(request, response);				
				}
				else {
					System.out.println("The username cannot be empty! Please enter the username!");
					request.setAttribute("errorMessage4", user_errormsg4);
					request.getRequestDispatcher("/registration.jsp").forward(request, response);				
			
				}
			}
			else {
				if(password1.equals(password2) && !password2.isEmpty()) {		
					 try {
						UserDAO.newUser(username, fullname, password1);
						System.out.println("The account is created! You can access the website now!");
						request.setAttribute("success", user_register);
						request.getRequestDispatcher("/login.jsp").forward(request, response);				
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(password1.equals(password2) && password2.isEmpty()) {		
					 try {
						System.out.println("The password cannot be empty! Please enter the password!");
						request.setAttribute("errorMessage3", user_errormsg3);
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

}
