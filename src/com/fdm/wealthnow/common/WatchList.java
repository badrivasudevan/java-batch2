package com.fdm.wealthnow.common;

import java.util.ArrayList;
import java.util.List;

public class WatchList {
	private int userId;
	private String watchlist_name;
	private List<Stock> watchlist = new ArrayList<>();
	private StockService stocksvc;
	private Stock stock;
	
	public WatchList(int userId, String watchlist_name){
		this.userId=userId;
		this.watchlist_name=watchlist_name;
	}
	
	public void AddStockinWatchlist(List<Stock> list, String symbol,int userId, String watchlist_name) {
		//update the sql database for that particular userId and watchlist_name
		//look for the given watchlist_name under the particular userId
		//If the symbol is not inside the watch_list, perform add to list and insert into sql database
		//otherwise do nothing
		// After checking, extract information from stockservice where the data is stored in the list
		//Write the code here
		
		
		List<Stock> stock = new ArrayList<>();
		//for all the stock under the given watchlist_name for that particular userId
		//We add the stock into the stock list
		
		
		list.add(stocksvc.getInfo(symbol));
		//add the new stock to sql database too
	}
	
	
	public void RemoveStockinWatchlist(List<Stock> list, String symbol, int userId, String watchlist_name){
		//update the sql database for that particular userId and watchlist_name
		//Write the code here
		list.remove(stocksvc.getInfo(symbol));
	
	}
	
	
	public List<Stock> WatchlistDetails(int userId, String watchlist) {
		//check all the stock under the given watchlist for that particular userId
		//Create an empty arraylist and add all the stock details in
		//Return that list
		
		List<Stock> stocklist = new ArrayList<>();
		//a = (select symbol from watchlist_table where id=user_id and w_id=watchlist
		for(stock.getSymbol() symbol : a) {
			stocklist.add(stocksvc.getInfo(symbol));
		}
		
		
		return stocklist;
		
		
	}
	
}