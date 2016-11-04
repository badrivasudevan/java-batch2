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
	
	public Holding(long userId, String stockSymbol, int holdingQuantity, double pricePaid,
			int boughtQuantity, double currentStockWorth, int soldQuantity, double moneyRealized, double profitLoss,
			String currency) {
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

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public void setHoldingQuantity(int holdingQuantity) {
		this.holdingQuantity = holdingQuantity;
	}

	public void setPricePaid(double pricePaid) {
		this.pricePaid = pricePaid;
	}

	public void setBoughtQuantity(int boughtQuantity) {
		this.boughtQuantity = boughtQuantity;
	}

	public void setCurrentStockWorth() throws MalformedURLException, IOException {
		this.currentStockWorth = HoldingService.getCurrentMarketPrice(this.stockSymbol) * this.holdingQuantity;
	}

	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public void setMoneyRealized(double moneyRealized) {
		this.moneyRealized = moneyRealized;
	}

	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
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