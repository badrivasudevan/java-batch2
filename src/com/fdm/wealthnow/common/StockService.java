package com.fdm.wealthnow.common;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
//import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.fdm.wealthnow.common.Stock;

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
		
		String yahooUrl = "http://finance.yahoo.com/d/quotes.csv?s=" +URLEncoder.encode(sb.toString()) + "&f=nsbal1b6a5d1t1";
		InputStream response = new URL(yahooUrl).openStream();
		Scanner scanner = new Scanner(response, "UTF-8").useDelimiter("\n");
		int j = 0;
		while(scanner.hasNext()) {
			list.add(scanner.next());
			//System.out.println("Output: " + scanner.next());
	
		//	list = Arrays.asList(items);
			//list.add("","","");
		}
		//System.out.println(scanner.next());
//		return; 
//				
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
			double bid = Double.parseDouble(stockdetails[2]);
			double ask = Double.parseDouble(stockdetails[3]);
			double current = Double.parseDouble(stockdetails[4]);
			stock = new Stock(stockdetails[0],stockdetails[1],bid,ask,current,stockdetails[5],stockdetails[6],stockdetails[7]);
			stocklist.add(stock);

		}	
		return stocklist;

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
	}
}

