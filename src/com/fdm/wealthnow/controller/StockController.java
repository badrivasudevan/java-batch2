
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

import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.dao.WatchListDAO;

/**
 * Servlet implementation class StockController
 */
@WebServlet("/StockController")
public class StockController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockController() {
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
		String stocksymbol = request.getParameter("stockname");
		String editstocklist = request.getParameter("addorremovestock");
		String allwatchlistforuser = request.getParameter("watchlist");
		List<String> list = new ArrayList<>();
		StockService stocksvc = new StockService();
		String user_errormsg3 = "The stock is not available in the Singapore Stock Exchange (SGX) to be added into the watchlist!";
		String user_errormsg4 = "The stock is not in the watchlist! It cannot be removed! Try again!";
		String user_addMessage = "The stock is successfully added into the watchlist";
		String user_rmMessage = "The stock is successfully removed from the watchlist";
		String user_errormsg5 = "The stock is already in the watchlist! It cannot be added! Try again!";
		
		switch(editstocklist){
		case "+":{
			list.add(stocksymbol);
			String w_idp = "";
			try {
				for(String s : WatchListDAO.retrieveWatchlist(userId).keySet()){
					if(WatchListDAO.retrieveWatchlist(userId).get(s).equals(allwatchlistforuser)){
						w_idp = s;
						break;
					}	
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<String> lists = new ArrayList<>();
			lists = stocksvc.getStockFromWeb(list);
			if(stocksvc.stringToDouble(stocksvc.stockStorage(lists).get(0).getCurrentmarketprice())== 0.0){
				 System.out.println("No such stock in the Singapore Stock Exchange (SGX) to add into the watchlist! Try again!");
				 request.setAttribute("errorMessage3", user_errormsg3);
				 request.getRequestDispatcher("/WatchListAddStocks.jsp").forward(request, response);
			}
			else {
				try {
					if(WatchListDAO.retrieveAllStockForWatchlist(w_idp).contains(stocksymbol)){
						System.out.println(stocksymbol + " is already in the "+allwatchlistforuser +"!");
						request.setAttribute("errorMessage5", user_errormsg5);
						request.getRequestDispatcher("/WatchListAddStocks.jsp").forward(request, response);
					}
					else {
						for(String s : WatchListDAO.retrieveWatchlist(userId).keySet()){
							if(WatchListDAO.retrieveWatchlist(userId).get(s).equals(allwatchlistforuser)){
								WatchListDAO.addStock(s, stocksymbol);
								System.out.println(stocksymbol + " is successfully added into "+allwatchlistforuser +"!");
								request.setAttribute("addMessage", user_addMessage);
								request.getRequestDispatcher("/WatchListAddStocks.jsp").forward(request, response);
								break;
							}	
						}
					}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;	
		}
		case "-":{
			list.add(stocksymbol);
			String w_id = "";
			try {
				for(String s : WatchListDAO.retrieveWatchlist(userId).keySet()){
					if(WatchListDAO.retrieveWatchlist(userId).get(s).equals(allwatchlistforuser)){
						w_id = s;
						break;
					}	
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				if(!WatchListDAO.retrieveAllStockForWatchlist(w_id).contains(stocksymbol)){
					 System.out.println("No such stock in the watchlist to remove! Try again!");
					 request.setAttribute("errorMessage4", user_errormsg4);
					 request.getRequestDispatcher("/WatchListAddStocks.jsp").forward(request, response);
				}
				else {
					try {
						for(String s : WatchListDAO.retrieveWatchlist(userId).keySet()){
							if(WatchListDAO.retrieveWatchlist(userId).get(s).equals(allwatchlistforuser)){
								WatchListDAO.removeStock(s, stocksymbol);
								System.out.println(stocksymbol + " is successfully removed from "+allwatchlistforuser +"!");
								request.setAttribute("rmMessage", user_rmMessage);
								request.getRequestDispatcher("/WatchListAddStocks.jsp").forward(request, response);
								break;
							}	
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		}
	
	}
	
}