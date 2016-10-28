package com.fdm.wealthnow.common;

import java.util.Date;

public class Order {

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


	public Order(long userID, TransactionType transacType, int orderQuantity, String stockSymbol, Term term,
			PriceType priceType, double priceExecuted, OrderStatus orderStatus) {
		this.userID = userID;
		TransacType = transacType;
		this.orderQuantity = orderQuantity;
		this.stockSymbol = stockSymbol;
		this.term = term;
		this.priceType = priceType;
		this.priceExecuted = priceExecuted;
		this.orderStatus = orderStatus;
	}


	public Order(long orderID, long userID, Date orderDate, TransactionType transacType, int orderQuantity,

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
	
	@Override
	public String toString() {
		return "Order ID: " + orderID +
				" User ID: " + userID +
				" Order Date: " + orderDate +
				" Transaction Type: " + TransacType +
				" Purchased Quantity: "  + orderQuantity +
				" Stock Symbol: " + stockSymbol +
				" Term: " + term +
				" Price Type: " + priceType +
				" Price Executed: " + priceExecuted +
				" Order Status: " + orderStatus; 
	} 
	

}
