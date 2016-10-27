package com.fdm.wealthnow.common;

import java.util.Date;

public class Stock {
	private String name;
	private String symbol;
	private double bidprice;
	private double askprice;
	private double currentmarketprice;
	private long bidvol;
	private long askvol;
	public Date updatedtime;
	
	public Stock(String name, String symbol, double bidprice, double askprice, double currentmarketprice, long bidvol,
			long askvol, Date updatedtime) {
		super();
		this.name = name;
		this.symbol = symbol;
		this.bidprice = bidprice;
		this.askprice = askprice;
		this.currentmarketprice = currentmarketprice;
		this.bidvol = bidvol;
		this.askvol = askvol;
		this.updatedtime = updatedtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getBidprice() {
		return bidprice;
	}

	public void setBidprice(double bidprice) {
		this.bidprice = bidprice;
	}

	public double getAskprice() {
		return askprice;
	}

	public void setAskprice(double askprice) {
		this.askprice = askprice;
	}

	public double getCurrentmarketprice() {
		return currentmarketprice;
	}

	public void setCurrentmarketprice(double currentmarketprice) {
		this.currentmarketprice = currentmarketprice;
	}

	public long getBidvol() {
		return bidvol;
	}

	public void setBidvol(long bidvol) {
		this.bidvol = bidvol;
	}

	public long getAskvol() {
		return askvol;
	}

	public void setAskvol(long askvol) {
		this.askvol = askvol;
	}

	public Date getUpdatedtime() {
		return updatedtime;
	}

	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

}
