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
	private PriceType priceType;
	private double priceExecuted;
	private OrderStatus orderStatus;


	

	public Orders(long userID, TransactionType transacType, int orderQuantity, String stockSymbol, Term term,
			PriceType priceType, double priceExecuted, OrderStatus orderStatus) {
		super();
		this.userID = userID;
		TransacType = transacType;
		this.orderQuantity = orderQuantity;
		this.stockSymbol = stockSymbol;
		this.term = term;
		this.priceType = priceType;
		this.priceExecuted = priceExecuted;
		this.orderStatus = orderStatus;
	}



	public Orders(long orderID, long userID, Date orderDate, TransactionType transacType, int orderQuantity,

		String stockSymbol, Term term, PriceType priceType, double priceExecuted, OrderStatus orderStatus) {
		
		this.orderID = orderID;
		this.userID = userID;
		this.orderDate = orderDate;
		TransacType = transacType;
		this.orderQuantity = orderQuantity;
		this.stockSymbol = stockSymbol;
		this.term = term;
		this.priceType = priceType;
		this.priceExecuted = priceExecuted;
		this.orderStatus = orderStatus;
	}
	
	

	public long getOrderID() {
		return orderID;
	}



	public long getUserID() {
		return userID;
	}



	public Date getOrderDate() {
		return orderDate;
	}



	public TransactionType getTransacType() {
		return TransacType;
	}



	public int getOrderQuantity() {
		return orderQuantity;
	}



	public String getStockSymbol() {
		return stockSymbol;
	}



	public Term getTerm() {
		return term;
	}



	public PriceType getPriceType() {
		return priceType;
	}



	public double getPriceExecuted() {
		return priceExecuted;
	}



	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	
	

}
