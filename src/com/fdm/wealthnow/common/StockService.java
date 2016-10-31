package com.fdm.wealthnow.common;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.fdm.wealthnow.common.Stock;

public class StockService {
	//extract all stock data from yahoo finance api and store it in the arraylist and update the values using list.set()
	//or store data from yahoo finance api in SQL database and update the values from there
	//by calling SQL statements in java 
	private static List<Stock> stockholding = new ArrayList<>();;	//???
	//private String symbol;
	private static Stock stock;
	
	public static Stock getInfo(String symbol){
		for(int i=0;i<stockholding.size();i++){
			if(stockholding.get(i).getSymbol().equals(symbol)) {
				stock = new Stock(stockholding.get(i).getName(),stockholding.get(i).getSymbol(),stockholding.get(i).getBidprice(), stockholding.get(i).getAskprice(),stockholding.get(i).getCurrentmarketprice(), stockholding.get(i).getUpdatedtime());
				break;
			}
		}
		return stock;
	}
	

//	public String getStockFromWeb(List<String> symbolList){
	public List<String> getStockFromWeb(List<String> symbolList) throws MalformedURLException, IOException{	
		List<String> list = new ArrayList<>();
		int i=0;

		StringBuffer sb = new StringBuffer();
		while(i < symbolList.size()){
			sb.append(symbolList.get(i));
			sb.append(".si");
			if(i!=symbolList.size()-1){
				sb.append("+");
			}
			i++;
		}
		
		String yahooUrl = "http://finance.yahoo.com/d/quotes.csv?s=" +URLEncoder.encode(sb.toString()) + "&f=nsbal1d1t1";
		InputStream response = new URL(yahooUrl).openStream();
		Scanner scanner = new Scanner(response, "UTF-8").useDelimiter("\n");
		int j = 0;
		while(scanner.hasNext()) {
			list.add(scanner.next());
			//System.out.println("Output: " + scanner.next());
	
		//	list = Arrays.asList(items);
			//list.add("","","");
		}
	
		response.close();
		return list;
	}
	
	public List<Stock> stockStorage(List<String> list) {
		List<Stock> stocklist = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			String[] stockdetails = list.get(i).split(",");

			for(int j=0;j<stockdetails.length;j++){
				System.out.println(stockdetails[j]);
				
			}
			//double bid = Double.parseDouble(stockdetails[2]);
			//double ask = Double.parseDouble(stockdetails[3]);
			double bid = stringToDouble(stockdetails[2]);
			double ask = stringToDouble(stockdetails[3]);
			double current = Double.parseDouble(stockdetails[4]);
			stock = new Stock(stockdetails[0],stockdetails[1],bid,ask,current,stockdetails[5]);
			stocklist.add(stock);

		}	
		return stocklist;
		
		//store it in hashmap
	}
	
	public HashMap<String,Stock> createHashMap(List<Stock> list) {
		HashMap<String,Stock> hm = new HashMap<>();
		for(int i=0; i<list.size();i++){
			stock = new Stock(list.get(i).getName(),list.get(i).getSymbol(),list.get(i).getBidprice(),list.get(i).getAskprice(),list.get(i).getCurrentmarketprice(),list.get(i).getUpdatedtime());
			hm.put(list.get(i).getSymbol(), stock);
		}
		return hm;
	}
	
	private Double stringToDouble(String s) {
		Double d = 0.0;
		
		try {
		d = Double.parseDouble(s);
		} catch (Exception e) {  }
		
		return d;
	}
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		StockService s1 = new StockService();
		List<String> list = new ArrayList<>();
		list.add("S51");
		list.add("N21");
		list.add("E5H");
		list.add("N03");
		System.out.println(s1.getStockFromWeb(list));
		System.out.println(s1.stockStorage(s1.getStockFromWeb(list)));
		
		HashMap<String,Stock> hashmap = new HashMap<>();
		hashmap = s1.createHashMap(s1.stockStorage(s1.getStockFromWeb(list)));
		//System.out.println(hashmap);
		//System.out.println(s1.createHashMap(s1.stockStorage(s1.getStockFromWeb(list))));
		System.out.println(hashmap.keySet());
		System.out.println(hashmap.values());
		System.out.println(hashmap);
		System.out.println(hashmap.get("\"N03.si\""));
		System.out.println(hashmap.get("\"S51.si\"").getAskprice());
		
		System.out.println("**************************");
		Iterator iter = hashmap.keySet().iterator();
		while(iter.hasNext()) {
			String key = (String) iter.next();
			System.out.println("key:"+ key + "value:" + hashmap.get(key));
		}
		
		System.out.println("fdsfsdfsd:" + hashmap.get("\"N03.si\""));
		
		//find a way to remove double quotes from key
	}
}

 