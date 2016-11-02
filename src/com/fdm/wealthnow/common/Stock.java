
package com.fdm.wealthnow.common;

public class Stock {
	private String name;
	private String symbol;
	private String bidprice;
	private String askprice;
	private String currentmarketprice;
//	private String bidvol;
//	private String askvol;
	public String updatedtime;
	
	public Stock(String name, String symbol, String bidprice, String askprice, String currentmarketprice, String updatedtime) {
		super();
		this.name = name;
		this.symbol = symbol;
		this.bidprice = bidprice;
		this.askprice = askprice;
		this.currentmarketprice = currentmarketprice;
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

	public String getBidprice() {
		return bidprice;
	}

	public void setBidprice(String bidprice) {
		this.bidprice = bidprice;
	}

	public String getAskprice() {
		return askprice;
	}

	public void setAskprice(String askprice) {
		this.askprice = askprice;
	}

	public String getCurrentmarketprice() {
		return currentmarketprice;
	}

	public void setCurrentmarketprice(String currentmarketprice) {
		this.currentmarketprice = currentmarketprice;
	}

//	public String getBidvol() {
//		return bidvol;
//	}
//
//	public void setBidvol(String bidvol) {
//		this.bidvol = bidvol;
//	}
//
//	public String getAskvol() {
//		return askvol;
//	}
//
//	public void setAskvol(String askvol) {
//		this.askvol = askvol;
//	}

	public String getUpdatedtime() {
		return updatedtime;
	}

	public void setUpdatedtime(String updatedtime) {
		this.updatedtime = updatedtime;
	}

	@Override
	public String toString() {
		return "Stock [name=" + name + ", symbol=" + symbol + ", bidprice=" + bidprice + ", askprice=" + askprice
				+ ", currentmarketprice=" + currentmarketprice + ", updatedtime=" + updatedtime + "]";
	}
	
	

}

