package com.fdm.wealthnow.backendService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;

public class Formatter {

	public static Date formatDate(String orderDate) { // Change date format from String
												// to Date

		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");

		try {
			date = formatter.parse(orderDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;

	}
	
	public static TransactionType formatTransacType(String transacType){
		
		if(transacType.equalsIgnoreCase("Buy"))
			return TransactionType.Buy;
		else
			return TransactionType.Sell;
	}
	
	public static Term formatTerm(String transacTerm){
		if(transacTerm.equalsIgnoreCase("GoodTilDay")){
			return Term.GoodForDay;
		}
		if(transacTerm.equalsIgnoreCase("GoodTilCanceled")){
			return Term.GoodUntilCancelled;
		}
		else
			return null;
		
	}
	
	public static PriceType formatPriceType (String priceType){
		
		if (priceType.equalsIgnoreCase("Market")){
			return PriceType.Market;
		}
		else if (priceType.equalsIgnoreCase("Limit")){
			return PriceType.Limit;
		}
		else
		return PriceType.StopLoss;

	}
	
	public static OrderStatus formatOrderStatus (String orderStatus){ 

		if (orderStatus.equalsIgnoreCase("Pending"))
		{
			return OrderStatus.Pending;
		}
		else if (orderStatus.equalsIgnoreCase("Completed")){
			return OrderStatus.Completed;
		}
		else {
			return OrderStatus.Cancelled;
		}
	}
	
	
	public static ArrayList<String> sgxstock() throws IOException {


		BufferedReader crunchifyBuffer = null;
		String crunchifyLine = null;
		try{

			crunchifyBuffer = new BufferedReader(new FileReader("C:/Users/Oliver.cheok/Documents/sgxstocks.csv"));
			crunchifyLine = crunchifyBuffer.readLine();
			ArrayList<String> crunchifyResult = new ArrayList<String>();
			String[] splitData = crunchifyLine.split("\\s*,\\s*");
			for (int i = 0; i < splitData.length; i++) {
				if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
					crunchifyResult.add(splitData[i].trim());
				}
			}

			/*for (String a : crunchifyResult) {
				System.out.println(a);
			}*/
			return crunchifyResult;

		}
		finally {
			try {
				if (crunchifyBuffer != null) crunchifyBuffer.close();
			} catch (IOException crunchifyException) {
				crunchifyException.printStackTrace();
			}
		}

	}

	


}
