package com.fdm.wealthnow.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.common.WatchlistService;
import com.fdm.wealthnow.dao.WatchListDAO;

/**
 * Servlet implementation class WatchListController
 */
@WebServlet("/WatchListController")
public class WatchListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WatchListController() {
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
		User currentUser = (User) (session.getAttribute("loggedInUser"));	//Check for log in users
		long userID =  currentUser.getUserId();
		
		String watchListName = request.getParameter("WatchListName");		//Get watchlist name from jsp
		System.out.println("WatchList Name: " + watchListName);
	
	WatchlistService watchList = WatchListDAO.retrieveWatchlist(w_id);		//get watchlist ID 
	if(watchList != null) {													//if watchlist ID is not null
																			//create new watchlist using loop to check for hasnext() in sql
		WatchlistService.createNewWatchlist									//Add new watchlist/watchlistname into sql
		
																				
		WatchlistService.editWatchlistName									//Replace watchlist name to sql
		
		
		WatchlistService.selectWatchlist									//Select watchlist from drop down list
			
		String stockName = request.getParameter("stockname");				//add stocks into watchlist
		System.out.println("WatchList Name: " + stockName);					
																			//add stocks into sql
									
	}
}
