package com.fdm.wealthnow.common;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class StockService {
	//extract all stock data from yahoo finance api and store it in the arraylist and update the values using list.set()
	//or store data from yahoo finance api in SQL database and update the values from there
	//by calling SQL statements in java 
	private ConcurrentHashMap<String,Stock> stocklist = new ConcurrentHashMap<>();
	
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
	
}
