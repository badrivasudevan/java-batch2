package com.fdm.wealthnow.common;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StockService {
	//extract all stock data from yahoo finance api and store it in the arraylist and update the values using list.set()
	//or store data from yahoo finance api in SQL database and update the values from there
	//by calling SQL statements in java 
	private List<Stock> stockholding = new ArrayList<>();;	//???
	//private String symbol;
	private Stock stock;
	
	public Stock getInfo(String symbol){
		for(int i=0;i<stockholding.size();i++){
			if(stockholding.get(i).getSymbol().equals(symbol)) {
				stock = new Stock(stockholding.get(i).getName(),stockholding.get(i).getSymbol(),stockholding.get(i).getBidprice(), stockholding.get(i).getAskprice(),stockholding.get(i).getCurrentmarketprice(), stockholding.get(i).getBidvol(),stockholding.get(i).getAskvol(),stockholding.get(i).getUpdatedtime());
				break;
			}
		}
		return stock;
	}
	
	public getStockFromWeb(){
		StringBuffer sb = new StringBuffer();
		
		String yahooUrl = "http://finance.yahoo.com/d/quotes.csv?s=AAPL+GOOG+MSFT&f=nab";
		InputStream response = new URL(yahooUrl).openStream();
		Scanner scanner = new Scanner(response, "UTF-8");
		while(scanner.hasNext()) {
			System.out.println("Output: " + scanner.next());
		}
		return 
				
		response.close();
		
	}
}
