package com.fdm.wealthnow.backendService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.dao.HoldingDAO;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.UserDAO;

public class HoldingService{
	
	private static final double TRANSACTION_FEE = 9.99;
	
	
	private static List<Holding> retrieveHolding(Order order) throws Exception{
		List<Holding> holding = HoldingDAO.retrieveHolding(order.getUserID()); 
		return holding;
	}
	
	public static List<Holding> callDAO(long userId) throws Exception{
		List<Holding> holding = HoldingDAO.retrieveHolding(userId); 
		return holding;

	}
	
	public static Double getCurrentMarketPrice(String stockSymbol) throws MalformedURLException, IOException{
		
		double marketPrice;
		List<String> symbolList = new ArrayList<>();
		symbolList.add(stockSymbol);
		marketPrice = StockService.stringToDouble(StockService.stockStorage(StockService.getStockFromWeb(symbolList)).get(0).getCurrentmarketprice());
		System.out.println("marketprice is: " + marketPrice);
		return marketPrice;
	
	}
	
	public static Double getInvestmentAmount(List<Holding> holdingList, Order order) throws SQLException{
		
		double investmentAmount;
		investmentAmount =  HoldingService.calculatePurchasePrice(holdingList, order) * HoldingService.calculateQuantityBought(holdingList, order);
		System.out.println("investment amount is: " + investmentAmount);
		return investmentAmount;
	}
	
	
	private static double calculateMoneyRealized(List<Holding> holdingList, Order order) {
	
		double moneyRealized = 0.0d;
		
		for(Holding holding : holdingList){

			if(order.getStockSymbol().equals(holding.getStockSymbol())){
					moneyRealized = holding.getMoneyRealized() + (order.getPriceExecuted() * order.getOrderQuantity());					
			}
		}
		System.out.println("moneyrealised is: " + moneyRealized);
		return moneyRealized;
	}
	
	/*private static double calculateCurrentStockWorth(List<Holding> holdingList, Order order) {
		
		double currentStockWorth = 0.0d;
		
		for(Holding holding : holdingList){

			if(order.getStockSymbol().equals(holding.getStockSymbol())){
					currentStockWorth = (holding.getRemainingQuantity() - order.getOrderQuantity()) * order.getPriceExecuted();		
			}
			
		}
		
		return currentStockWorth;
	}*/
	
	private static double calculateProfitLoss(List<Holding> holdingList, Order order) throws Exception{
		
		double profitLoss = 0.0d;
		
		for(Holding holding : holdingList){

			if(order.getStockSymbol().equals(holding.getStockSymbol())){
					profitLoss = (holding.getCurrentStockWorth() + holding.getMoneyRealized()) - (getInvestmentAmount(holdingList, order));	
			}
		}
		return profitLoss;
	}
	
	private static double calculatePurchasePrice(List<Holding> holdingList, Order order){
		
		double purchasePrice = 0.0d;
		
		boolean isExist = false;
		
		for(Holding holding : holdingList){

			if(order.getStockSymbol().equals(holding.getStockSymbol())){
				isExist = true;

				if(order.getTransacType() == TransactionType.Buy){
					purchasePrice = ((order.getOrderQuantity() * order.getPriceExecuted()) + (holding.getHoldingQuantity() * holding.getPricePaid()))/(order.getOrderQuantity() + holding.getHoldingQuantity());
					break;
				}
				else {
					purchasePrice = holding.getPricePaid();
					break;
				}
			}	
		}

		if(!isExist)
		{
			purchasePrice = order.getPriceExecuted();
		}
		return purchasePrice;
	}
	
	private static int calculateQuantityHeld(List<Holding> holdingList, Order order){
		
		int holdingQuantity = 0;
		
		boolean isExist = false;
		
		for(Holding holding : holdingList){
			
			if(order.getStockSymbol().equals(holding.getStockSymbol())){		
				isExist = true;
				
				if(order.getTransacType() == TransactionType.Buy){
					holdingQuantity = holding.getHoldingQuantity() + order.getOrderQuantity();
				}
				else{
					holdingQuantity = holding.getHoldingQuantity() - order.getOrderQuantity();
				}
			}
		}
		
		if(!isExist)
		{
			holdingQuantity = order.getOrderQuantity();
		}
		return holdingQuantity;
	}
	
	private static int calculateQuantitySold(List<Holding> holdingList, Order order){
		
		int soldQuantity = 0;
		
		for(Holding holding : holdingList){
			
			if(order.getStockSymbol().equals(holding.getStockSymbol())){				
					soldQuantity = holding.getSoldQuantity() + order.getOrderQuantity();				
			}
		}
		
		return soldQuantity;
	}
	
private static int calculateQuantityBought(List<Holding> holdingList, Order order){

	int boughtQuantity = 0;
	boolean isExist = false;

	for(Holding holding : holdingList){

		if(order.getStockSymbol().equals(holding.getStockSymbol())){		
			isExist = true;

			if(order.getStockSymbol().equals(holding.getStockSymbol())){				
				boughtQuantity = holding.getSoldQuantity() + order.getOrderQuantity();				
			}
		}
	}

	if(!isExist)
	{
		boughtQuantity = order.getOrderQuantity();
	}

	return boughtQuantity;
}
	
	
	private static void updateCashBalance(Order order) throws Exception{

		double cashBalanceChange;

		if(order.getTransacType() == TransactionType.Buy){
			cashBalanceChange = ((order.getOrderQuantity() * order.getPriceExecuted()) + TRANSACTION_FEE);
			UserDAO.deductBalance(order.getUserID(), cashBalanceChange);
		}
		else {
			cashBalanceChange = ((order.getOrderQuantity() * order.getPriceExecuted()) - TRANSACTION_FEE);
			UserDAO.addBalance(order.getUserID(), cashBalanceChange);
		}
		
		System.out.println("Updated cash balance: " + cashBalanceChange);

	}
	
	public static void updatePortfolio(Order order){
		
		double purchasePrice;
		double moneyRealized;
		double currentStockWorth;
		double profitLoss;
		int holdingQuantity;
		int soldQuantity;
		int boughtQuantity;
		
		boolean isExist = false;

		try{
			Holding newHolding = null;
			List<Holding> holdingList = HoldingService.retrieveHolding(order);
			System.out.println("HoldingList: " + holdingList + "Size: " + holdingList.size());
			purchasePrice = HoldingService.calculatePurchasePrice(holdingList, order);
			holdingQuantity = HoldingService.calculateQuantityHeld(holdingList, order);
			soldQuantity = HoldingService.calculateQuantitySold(holdingList, order);
			boughtQuantity = HoldingService.calculateQuantityBought(holdingList, order);
			moneyRealized = HoldingService.calculateMoneyRealized(holdingList, order);
			currentStockWorth = holdingQuantity * HoldingService.getCurrentMarketPrice(order.getStockSymbol());
			profitLoss = HoldingService.calculateProfitLoss(holdingList, order);
			
			if(order.getTransacType() == TransactionType.Buy){

				for(Holding holding : holdingList){
					if(order.getStockSymbol().equals(holding.getStockSymbol())){

						isExist = true;

						newHolding = HoldingDAO.retrieveIndividualHolding(order);

						newHolding.setPricePaid(purchasePrice);
						newHolding.setBoughtQuantity(boughtQuantity);
						newHolding.setHoldingQuantity(holdingQuantity);
						newHolding.setCurrentStockWorth();
						newHolding.setProfitLoss(profitLoss);

						HoldingDAO.updateHoldingWhenBuy(newHolding);

						break;
					}
				}
				//System.out.println("isExist Boolean: " + isExist);
				if(!isExist){
					newHolding = new Holding(order.getUserID(), order.getStockSymbol(), holdingQuantity, purchasePrice, boughtQuantity, currentStockWorth, 0, 0.0d, 0.0d, "SGD");
					HoldingDAO.storeHolding(newHolding);

				}

				HoldingService.updateCashBalance(order);
			
		//----------------------------------------------------sell order---------------------------------------------------------
		}
		else{
			for(Holding holding : holdingList){
				if(order.getStockSymbol().equals(holding.getStockSymbol())){

						newHolding = HoldingDAO.retrieveIndividualHolding(order);
						
						newHolding.setPricePaid(purchasePrice);
						newHolding.setSoldQuantity(soldQuantity);
						newHolding.setHoldingQuantity(holdingQuantity);
						newHolding.setCurrentStockWorth();
						newHolding.setMoneyRealized(moneyRealized);
						newHolding.setProfitLoss(profitLoss);
						
						HoldingDAO.updateHoldingWhenSell(newHolding);
						HoldingService.updateCashBalance(order);
						break;
				}
			}
		}
	}
		catch(Exception e){
			System.out.println("Unable to update portfolio due to connection error");
		}
	}
	
	//Testing HoldingService
	/*public static void main(String[] args) throws Exception{
		Order order = new Order(5, TransactionType.Sell, 50, "AAPL", Term.GoodForDay, PriceType.Market, 9.99, OrderStatus.Pending);
		List<Holding> holdingList = HoldingService.retrieveHolding(order);
		System.out.println("Updated price: " + HoldingService.calculatePurchasePrice(holdingList, order));
		System.out.println("Updated quantity: " + HoldingService.calculateQuantity(holdingList, order));
		HoldingService.updatePortfolio(order);
		
	}*/
	
}
