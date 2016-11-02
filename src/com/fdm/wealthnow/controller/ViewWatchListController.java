package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.StockService;
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		long userId = Long.parseLong(request.getParameter("userid"));
		String allwatchlistforuser = request.getParameter("Watchlist");
		List<String> listsymbol = new ArrayList<>();
		List<String> liststockfmyahoo = new ArrayList<>();
		List<Stock> liststock = new ArrayList<>();
		HashMap<String,Stock> stockhashmap = new HashMap<>();
		String w_id = "";
		
		try {
			for(String s : WatchListDAO.retrieveWatchlist(userId).keySet()){
				 if(WatchListDAO.retrieveWatchlist(userId).get(s).equals(allwatchlistforuser)){
						w_id = s;
						break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			listsymbol = WatchListDAO.retrieveAllStockForWatchlist(w_id);
//			liststockfmyahoo = StockService.getStockFromWeb(listsymbol);
//			liststock = StockService.stockStorage(liststockfmyahoo);
//			stockhashmap = StockService.createHashMap(liststock);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		request.setAttribute("watchlistid", w_id);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/list.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
