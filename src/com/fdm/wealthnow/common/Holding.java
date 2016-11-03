package com.fdm.wealthnow.common;

public class Holding {
	private long holdingsId;
	private long userId;
	private String stockSymbol;
	private int remainingQuantity;
	private double pricePaid;
	private double moneyRealized;
	private double currentStockWorth;
	private double profitLoss;
	private String currency;


	public Holding(long userId, String stockSymbol, int remainingQuantity, double pricePaid, double moneyRealized,
			double currentStockWorth, double profitLoss, String currency) {
		super();
		this.userId = userId;
		this.stockSymbol = stockSymbol;
		this.remainingQuantity = remainingQuantity;
		this.pricePaid = pricePaid;
		this.moneyRealized = moneyRealized;
		this.currentStockWorth = currentStockWorth;
		this.profitLoss = profitLoss;
		this.currency = currency;
	}
	
	
	public Holding(long holdingsId, long userId, String stockSymbol, int remainingQuantity, double pricePaid,
			double moneyRealized, double currentStockWorth, double profitLoss, String currency) {
		super();
		this.holdingsId = holdingsId;
		this.userId = userId;
		this.stockSymbol = stockSymbol;
		this.remainingQuantity = remainingQuantity;
		this.pricePaid = pricePaid;
		this.moneyRealized = moneyRealized;
		this.currentStockWorth = currentStockWorth;
		this.profitLoss = profitLoss;
		this.currency = currency;
	}



	public double getCurrentStockWorth() {
		return currentStockWorth;
	}

	public double getMoneyRealized() {
		return moneyRealized;
	}

	public double getProfitLoss() {
		return profitLoss;
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
	
	
	
	public void setHoldingsId(long holdingsId) {
		this.holdingsId = holdingsId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public void setCurrentStockWorth(double currentStockWorth) {
		this.currentStockWorth = currentStockWorth;
	}

	public void setMoneyRealized(double moneyRealized) {
		this.moneyRealized = moneyRealized;
	}

	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	@Override
	public String toString() {
		return "Holding [holdingsId=" + holdingsId + ", userId=" + userId + ", stockSymbol=" + stockSymbol
				+ ", remainingQuantity=" + remainingQuantity + ", pricePaid=" + pricePaid + ", moneyRealized="
				+ moneyRealized + ", currentStockWorth=" + currentStockWorth + ", profitLoss=" + profitLoss
				+ ", currency=" + currency + "]";
	}





	
	
	

}