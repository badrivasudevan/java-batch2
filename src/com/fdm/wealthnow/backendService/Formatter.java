package com.fdm.wealthnow.backendService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		if(transacType == "Buy")
			return TransactionType.Buy;
		else
			return TransactionType.Sell;
	}
	
	public static Term formatTerm(String transacTerm){
		
		if(transacTerm == "Good For Day"){
			return Term.GoodForDay;
		}
		else{
			return Term.GoodUntilCancelled;
		}
	}

}
