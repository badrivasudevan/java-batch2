package com.fdm.wealthnow.common;

public class Holdings {
	private long holdingsId;
	private long userId;
	private String stockSymbol;
	private int remainingQuantity;
	private double pricePaid;
	private String currency;


	public Holdings(long holdingsId, long userId, String stockSymbol, int remainingQuantity,
			double pricePaid, String currency) {
		this.holdingsId = holdingsId;
		this.userId = userId;
		this.stockSymbol = stockSymbol;
		this.remainingQuantity = remainingQuantity;
		this.pricePaid = pricePaid;
		this.currency = currency;
	}


	public long getHoldingsId() {
		return holdingsId;
	}


	public long getUserId() {
		return userId;
	}


	public String getStockSymbol() {
		return stockSymbol;
	}


	public int getRemainingQuantity() {
		return remainingQuantity;
	}


	public double getPricePaid() {
		return pricePaid;
	}


	public String getCurrency() {
		return currency;
	}


	@Override
	public String toString() {
		return "Holdings [holdingsId=" + holdingsId + ", userId=" + userId + ", stockSymbol=" + stockSymbol
				+ ", remainingQuantity=" + remainingQuantity + ", pricePaid=" + pricePaid + ", currency=" + currency
				+ "]";
	}
	
	
	

}