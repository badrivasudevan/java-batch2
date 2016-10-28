package com.fdm.wealthnow.backendService;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.StockService;

public class BuySellTask implements Runnable {
	
	Order order;
	StockService stockService;
	

	public BuySellTask(Order order) {

		this.order = order;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		String stockSymbol = order.getStockSymbol();
		
		
	}
	
	
	
	

}
