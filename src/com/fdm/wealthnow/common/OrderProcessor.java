package com.fdm.wealthnow.common;

import java.util.Date;

import src.com.fdm.wealthnow.common.Order;
import src.com.fdm.wealthnow.common.OrderExecutor;

public class OrderProcessor {
	
	private OrderExecutor executor = new OrderExecutor();
	
	public Order buy(int userId, String stockSymbol, int quantity){
		
		int orderId = generateOrderId(userId);
		Date datePlaced = new Date();
		Order order = new Order(userId, orderId, datePlaced, stockSymbol, TransactionType.Buy, quantity, OrderStatus.Pending);
		
		executor.submit(order);
		
		return null;
	}
	
	public Order sell(int userId, String stockSymbol, int quantity){
		
		int orderId = generateOrderId(userId);
		Date datePlaced = new Date();
		Order order = new Order(userId, orderId, datePlaced, stockSymbol, TransactionType.Sell, quantity, OrderStatus.Pending);
		
		executor.submit(order);
		
		return null;
	}
	
	public int generateOrderId(int userId){
		
		int date = (int)(new Date().getTime()/1000);
		
		int orderId = userId + date;
		
		return orderId;
		
	}

}
