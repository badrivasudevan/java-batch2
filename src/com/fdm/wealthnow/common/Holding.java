package com.fdm.wealthnow.common;

import java.io.IOException;
import java.net.MalformedURLException;
import com.fdm.wealthnow.backendService.HoldingService;

public class Holding {
	private long holdingsId;
	private long userId;
	private String stockSymbol;
	private int holdingQuantity;
	private double pricePaid;
	private int boughtQuantity;
	private double currentStockWorth;
	private int soldQuantity;
	private double moneyRealized;
	private double profitLoss;
	private String currency;

	public Holding(long userId, String stockSymbol, int holdingQuantity, double pricePaid, int boughtQuantity,
			double currentStockWorth, int soldQuantity, double moneyRealized, double profitLoss, String currency) {
		super();
		this.userId = userId;
		this.stockSymbol = stockSymbol;
		this.holdingQuantity = holdingQuantity;
		this.pricePaid = pricePaid;
		this.boughtQuantity = boughtQuantity;
		this.currentStockWorth = currentStockWorth;
		this.soldQuantity = soldQuantity;
		this.moneyRealized = moneyRealized;
		this.profitLoss = profitLoss;
		this.currency = currency;
	}

	public Holding(long holdingsId, long userId, String stockSymbol, int holdingQuantity, double pricePaid,
			int boughtQuantity, double currentStockWorth, int soldQuantity, double moneyRealized, double profitLoss,
			String currency) {
		super();
		this.holdingsId = holdingsId;
		this.userId = userId;
		this.stockSymbol = stockSymbol;
		this.holdingQuantity = holdingQuantity;
		this.pricePaid = pricePaid;
		this.boughtQuantity = boughtQuantity;
		this.currentStockWorth = currentStockWorth;
		this.soldQuantity = soldQuantity;
		this.moneyRealized = moneyRealized;
		this.profitLoss = profitLoss;
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

	public int getHoldingQuantity() {
		return holdingQuantity;
	}

	public double getPricePaid() {
		return pricePaid;
	}

	public int getBoughtQuantity() {
		return boughtQuantity;
	}

	public double getCurrentStockWorth() {
		return currentStockWorth;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public double getMoneyRealized() {
		return moneyRealized;
	}

	public double getProfitLoss() {
		return profitLoss;
	}

	public String getCurrency() {
		return currency;
	}
	
	public Double getInvestmentAmount(){
		
		double investedAmt = this.pricePaid * this.boughtQuantity;
		
		return investedAmt;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public void setHoldingQuantity(Order order) {

		if (order.getTransacType() == TransactionType.Buy) {
			this.holdingQuantity = this.holdingQuantity + order.getOrderQuantity();
		} else {
			this.holdingQuantity = this.holdingQuantity - order.getOrderQuantity();
		}

	}

	public void setCurrentStockWorth() throws MalformedURLException, IOException {
		this.currentStockWorth = HoldingService.getCurrentMarketPrice(this.stockSymbol) * this.holdingQuantity;
	}

	public void setBoughtQuantity(Order order) {

		if (order.getTransacType() == TransactionType.Buy)
			this.boughtQuantity = this.boughtQuantity + order.getOrderQuantity();
	}

	public void setPricePaid(Order order) {

		if (order.getTransacType() == TransactionType.Buy) {
			this.pricePaid = ((order.getOrderQuantity() * order.getPriceExecuted())
					+ (this.holdingQuantity * this.pricePaid)) / (order.getOrderQuantity() + this.holdingQuantity);
		}

	}

	public void setSoldQuantity(Order order) {

		if (order.getTransacType() == TransactionType.Sell)
			this.soldQuantity = this.soldQuantity + order.getOrderQuantity();
	}

	public void setMoneyRealized(Order order) {
		if (order.getTransacType() == TransactionType.Sell)
			this.moneyRealized = this.moneyRealized + (order.getOrderQuantity() * order.getPriceExecuted());
	}

	public void setProfitLoss() {
		this.profitLoss = this.currentStockWorth + this.moneyRealized - this.getInvestmentAmount();
		System.out.println("Current Stock Worth " + this.currentStockWorth);
		System.out.println("Money Realized " + this.moneyRealized);
		System.out.println("Invested Amount" + this.getInvestmentAmount());
		
		System.out.println("Profit loss: " + this.profitLoss);
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Holding [holdingsId=" + holdingsId + ", userId=" + userId + ", stockSymbol=" + stockSymbol
				+ ", holdingQuantity=" + holdingQuantity + ", pricePaid=" + pricePaid + ", boughtQuantity="
				+ boughtQuantity + ", currentStockWorth=" + currentStockWorth + ", soldQuantity=" + soldQuantity
				+ ", moneyRealized=" + moneyRealized + ", profitLoss=" + profitLoss + ", currency=" + currency + "]";
	}

}