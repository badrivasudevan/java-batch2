package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.HoldingDAO;
import com.fdm.wealthnow.dao.OrderDAO;

/**
 * Servlet implementation class PendingController
 */
@WebServlet("/PendingController")
public class PendingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PendingController() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		//User currentUser = (User) session.getAttribute("loggedInUser");
		//long userID = currentUser.getUserId();
		String[] orderIDList = request.getParameterValues("pendingCheckbox");
		for (String orderId : orderIDList) {
			int stockId1 = Integer.parseInt(orderId);
			try {
				OrderDAO.updatePending(stockId1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Removing order "+orderId);
		}
		
		request.getRequestDispatcher("/Pending.jsp").forward(request, response);
		//int stockID = Integer.parseInt(request.getParameter("pendingCheckbox"));
		//System.out.println(stockID);
		//pendingList = OrderDAO.updatePending(userID,stockID); 
		
		
	}

}
