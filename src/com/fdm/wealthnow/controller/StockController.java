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
		String user_errormsg4 = "The stock is not available in the Singapore Stock Exchange (SGX) to be removed from the watchlist!";
		
		switch(editstocklist){
		case "+":{
			list.add(stocksymbol);
			if(stocksvc.getStockFromWeb(list).get(0)==null||stocksvc.getStockFromWeb(list).get(0).isEmpty()||stocksvc.getStockFromWeb(list).get(0).equals("N/A")){
				 System.out.println("No such stock in the Singapore Stock Exchange (SGX) to add into the watchlist! Try again!");
				 request.setAttribute("errorMessage3", user_errormsg3);
				 request.getRequestDispatcher("/WatchListAddStocks.jsp").forward(request, response);
			}
			else {
				try {
					for(String s : WatchListDAO.retrieveWatchlist(userId).keySet()){
						if(WatchListDAO.retrieveWatchlist(userId).get(s).equals(allwatchlistforuser)){
							WatchListDAO.addStock(s, stocksymbol);
							break;
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
			if(stocksvc.getStockFromWeb(list).get(0).equals("N/A") ||stocksvc.getStockFromWeb(list).get(0)==null||stocksvc.getStockFromWeb(list).get(0).isEmpty()){
				 System.out.println("No such stock in the Singapore Stock Exchange (SGX) to remove from the watchlist! Try again!");
				 request.setAttribute("errorMessage4", user_errormsg4);
				 request.getRequestDispatcher("/WatchListAddStocks.jsp").forward(request, response);
			}
			else {
				try {
					for(String s : WatchListDAO.retrieveWatchlist(userId).keySet()){
						if(WatchListDAO.retrieveWatchlist(userId).get(s).equals(allwatchlistforuser)){
							WatchListDAO.removeStock(s, stocksymbol);
							break;
						}	
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		}
	
		request.getRequestDispatcher("/WatchListAddStocks.jsp").forward(request, response);
	}
	
//	request.setAttribute("result", result);
//	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WatchList.jsp");
//	dispatcher.forward(request, response);

	
	//	WatchlistService.createNewWatchlist									//Add new watchlist/watchlistname into sql
	
																			
	//WatchlistService.editWatchlistName									//Replace watchlist name to sql
	
	
	//WatchlistService.selectWatchlist									//Select watchlist from drop down list
		
	//String stockName = request.getParameter("stockname");				//add stocks into watchlist
	//System.out.println("WatchList Name: " + stockName);					
																		//add stocks into sql
				
	
}
