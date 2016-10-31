package com.fdm.wealthnow.backendService;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.common.TransactionType;

import commonObject.StockData;

public class BuySellTask implements Runnable {
	
	Order order;
	StockService stockService;
	

	public BuySellTask(Order order) {

		this.order = order;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		TransactionType type = order.getTransacType();
		String stockSymbol = order.getStockSymbol();
		double price;
		
		if(type == TransactionType.Buy){
			
			price = order.getPriceExecuted();
			
		}
		else{
			
		}	
		
	}
	
	private void BuyTask(Order order){
		
		if(OrderService.validateCashBalance(order)){
			
			if(order.getPriceType() == PriceType.Market){		
				order.setOrderStatus(OrderStatus.Completed);
				HoldingService.updatePortfolio(order);		
			}else if(order.getPriceType() == PriceType.Limit){
				
				if(order.getPriceExecuted() == StockService.getInfo(order.getStockSymbol()))
				
			}
		}
		
	}
	
	
	
	

}
