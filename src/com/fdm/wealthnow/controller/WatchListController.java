package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.UserDAO;
import com.fdm.wealthnow.dao.WatchListDAO;

/**
 * Servlet implementation class WatchListController
 */
@WebServlet("/WatchListController")
public class WatchListController extends HttpServlet {
//	private WatchListDAO watchlist;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WatchListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long userId = Long.parseLong(request.getParameter("userid"));
//		HashMap<String, String> watchlistforuserid = new HashMap<>();
		WatchListDAO watchlist = new WatchListDAO();
		String watchlistname = request.getParameter("WatchListName");
		String editwatchlist = request.getParameter("addorremove");
		String user_errormsg = "No such watchlist in the database!";
		String user_errormsg2 = "The watchlist is already in the database!";
		String rwl = watchlistname + " " + "is successfully removed from the database!";
		String awl = watchlistname + " " + "is successfully added into the database!";
		
		switch(editwatchlist) {
		case "+":{
			try {
				if(watchlist.retrieveWatchlist(userId).containsValue(watchlistname)) {
					 System.out.println("The watchlist is already in the database! Try again!");
					 request.setAttribute("errorMessage2", user_errormsg2);
					 request.getRequestDispatcher("/WatchList.jsp").forward(request, response);
				     break;
				}
				else {
					watchlist.createWatchlist(userId, watchlistname);
					System.out.println(watchlistname+" is successfully added into the database!");
				    request.setAttribute("addwatchlist", awl); 	
				    request.getRequestDispatcher("/WatchList.jsp").forward(request, response); 
				    break;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		case "-":{	
			try {
				if (watchlist.retrieveWatchlist(userId).containsValue(watchlistname)) {
					 for(String s : watchlist.retrieveWatchlist(userId).values()){
						 if(s.equals(watchlistname)){
							 String rkey = watchlist.retrieveWatchlist(userId).get(s);
							 watchlist.removeWatchlist(rkey);
							 break;
						 }
					 };	
					 System.out.println(watchlistname+" is successfully removed from the database!");
				     request.setAttribute("removewatchlist", rwl); 	
				     request.getRequestDispatcher("/WatchList.jsp").forward(request, response); 
				     break;
				} 
				else {
					 System.out.println("No such watchlist in the database! Try again!");
					 request.setAttribute("errorMessage", user_errormsg);
					 request.getRequestDispatcher("/WatchList.jsp").forward(request, response);
				     break;
				     }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		}
		
//		request.setAttribute("result", result);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WatchList.jsp");
		dispatcher.forward(request, response);

		
		//	WatchlistService.createNewWatchlist									//Add new watchlist/watchlistname into sql
		
																				
		//WatchlistService.editWatchlistName									//Replace watchlist name to sql
		
		
		//WatchlistService.selectWatchlist									//Select watchlist from drop down list
			
		//String stockName = request.getParameter("stockname");				//add stocks into watchlist
		//System.out.println("WatchList Name: " + stockName);					
																			//add stocks into sql
									
	}
	
	
	
}