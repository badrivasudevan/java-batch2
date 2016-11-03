package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.wealthnow.dao.WatchListDAO;

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
		
		
		
		try {
			for(String s : WatchListDAO.retrieveWatchlist(userId).keySet()){
				 if(WatchListDAO.retrieveWatchlist(userId).get(s).equals(allwatchlistforuser)){
						w_id = s;
						System.out.println("watchlistid :" +w_id);
						request.setAttribute("watchlistid", w_id);
						break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WatchList.jsp").forward(request, response);

	}

}

