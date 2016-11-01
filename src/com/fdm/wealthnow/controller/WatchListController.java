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
import com.fdm.wealthnow.dao.WatchListDAO;

/**
 * Servlet implementation class WatchListController
 */
@WebServlet("/WatchListController")
public class WatchListController extends HttpServlet {
	private WatchListDAO watchlist;
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
		//String watchListName = request.getParameter("WatchListName");		//Get watchlist name from jsp
		//System.out.println("WatchList Name: " + watchListName);
		HashMap<String, String> watchlistforuserid = new HashMap<>();
		
		watchlist = new WatchListDAO();
		try {
			watchlistforuserid=watchlist.retrieveWatchlist(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		//get watchlist ID 
		//String watchListId = new String("");
		//String watchListName = new String("");
		//String wlid = watchlistforuserid.get(0);
		//String wlname = "Hello";
		if(watchlistforuserid.isEmpty()) {													
			//if watchlist is empty, there won't show any current watchlist id or watchlist name for that user
//			watchListId = "";
//			watchListName = "";
		//	System.out.println("Is Empty");
			request.setAttribute("watchListId","");	
			request.setAttribute("watchListName","");
			request.getRequestDispatcher("/WatchList.jsp").forward(request, response);
		}
//		else {
//			watchListId = watchlistforuserid.get(0);
//			watchListName = "Hello";
			System.out.println("Hello");
			request.setAttribute("watchListId",watchlistforuserid.get(0));	
			request.setAttribute("watchListName","Hello");	
			request.getRequestDispatcher("/WatchList.jsp").forward(request, response);
			//Set set = (Set) watchlistforuserid.entrySet();
			//Iterator it = set.iterator();
			//while(it.hasNext()){
			// Map.Entry entry = (Map.Entry) it;
			// watchListId = (String) entry.getKey();
			// watchListName = (String) entry.getValue();
			//   break;	    
			//}
//		}
		
		//	WatchlistService.createNewWatchlist									//Add new watchlist/watchlistname into sql
		
																				
		//WatchlistService.editWatchlistName									//Replace watchlist name to sql
		
		
		//WatchlistService.selectWatchlist									//Select watchlist from drop down list
			
		//String stockName = request.getParameter("stockname");				//add stocks into watchlist
		//System.out.println("WatchList Name: " + stockName);					
																			//add stocks into sql
									
//	request.setAttribute("result", result);
	//request.setAttribute("watchListId",watchListId);	
	//request.setAttribute("watchListName",watchListName);	
//  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WatchList.jsp");
//	dispatcher.forward(request, response);
	}
	
	
	
}