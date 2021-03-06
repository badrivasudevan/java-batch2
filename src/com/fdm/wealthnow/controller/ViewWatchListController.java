package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.fdm.wealthnow.common.Stock;
//import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.dao.WatchListDAO;

/**
 * Servlet implementation class ViewWatchListController
 */
@WebServlet("/ViewWatchListController")
public class ViewWatchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewWatchListController() {
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
		long userId = Long.parseLong(request.getParameter("userid"));
		String allwatchlistforuser = request.getParameter("Watchlist");
		String w_id = "";
		
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
