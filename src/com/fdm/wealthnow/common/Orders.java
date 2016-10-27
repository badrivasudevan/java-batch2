package com.fdm.wealthnow.common;

import java.util.Date;

public class Orders {

	private long orderID;
	private long userID;
	private Date orderDate;
	private TransactionType TransacType;
	private int orderQuantity;
	private String stockSymbol;
	private Term term;
	private double priceExecuted;
	private OrderStatus orderStatus;

	public Orders(long orderID, long userID, Date orderDate, TransactionType transacType, int orderQuantity,

		String stockSymbol, Term term, double priceExecuted, OrderStatus orderStatus) {
		
		this.orderID = orderID;
		this.userID = userID;
		this.orderDate = orderDate;
		TransacType = transacType;
		this.orderQuantity = orderQuantity;
		this.stockSymbol = stockSymbol;
		this.term = term;
		this.priceExecuted = priceExecuted;
		this.orderStatus = orderStatus;
	}

}
