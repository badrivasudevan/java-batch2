package com.fdm.wealthnow.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fdm.wealthnow.common.StockService;

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
				 request.getRequestDispatcher("/WatchList.jsp").forward(request, response);
			}
			else {
				//add stock into the sql database
			}
			
		}
		case "-":{
			list.add(stocksymbol);
			if(stocksvc.getStockFromWeb(list).get(0).equals("N/A") ||stocksvc.getStockFromWeb(list).get(0)==null||stocksvc.getStockFromWeb(list).get(0).isEmpty()){
				 System.out.println("No such stock in the Singapore Stock Exchange (SGX) to remove from the watchlist! Try again!");
				 request.setAttribute("errorMessage4", user_errormsg4);
				 request.getRequestDispatcher("/WatchList.jsp").forward(request, response);
			}
		}
	}
	
	 request.getRequestDispatcher("/WatchList.jsp").forward(request, response);
	 
	}

//	<%	if(request.getAttribute("errorMessage3") != null) {	 %>
//	     <!-- out.println(request.getAttribute("errorMessage")); -->
//	   <script>alert("No such Stock in the Singapore Stock Exchange (SGX) to be added!");</script>
//<%		}
//%>
//<%	if(request.getAttribute("errorMessage4") != null) {	 %>
//	     <!-- out.println(request.getAttribute("errorMessage")); -->
//	   <script>alert("No such Stock in the Singapore Stock Exchange (SGX) to be removed!");</script>
//<%		}
//%>
}
