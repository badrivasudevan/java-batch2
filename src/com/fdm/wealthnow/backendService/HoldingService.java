package com.fdm.wealthnow.backendService;

import java.io.IOException;
import java.net.MalformedURLException;
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
import com.fdm.wealthnow.dao.UserDAO;

public class HoldingService{
	
	private static final double TRANSACTION_FEE = 9.99;
	private static double purchasePrice;
	private static int remainingQuantity;
	private static double moneyRealized;
	private static double currentStockWorth;
	private static double profitLoss;
	
	
	private static List<Holding> retrieveHolding(Order order) throws Exception{
		List<Holding> holding = HoldingDAO.retrieveHolding(order.getUserID()); 
		return holding;
	}
	
	public static List<Holding> callDAO(long userId) throws Exception{
		List<Holding> holding = HoldingDAO.retrieveHolding(userId); 
		return holding;

	}
	
	public static Double getCurrentMarketPrice(String stockSymbol) throws MalformedURLException, IOException{
		
		List<String> symbolList = new ArrayList<>();
		symbolList.add(stockSymbol);
		return (StockService.stringToDouble(StockService.stockStorage(StockService.getStockFromWeb(symbolList)).get(0).getCurrentmarketprice()));
	
	}
	
	
	private static double calculateMoneyRealized(List<Holding> holdingList, Order order) {
	
		double moneyRealized = 0.0d;
		
		for(Holding holding : holdingList){

			if(order.getStockSymbol().equals(holding.getStockSymbol())){
					moneyRealized = holding.getMoneyRealized() + (order.getPriceExecuted() * order.getOrderQuantity());					
			}
		}
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
					profitLoss = (holding.getCurrentStockWorth() + holding.getMoneyRealized()) - (holding.getRemainingQuantity() * holding.getPricePaid());	
			}
		}
		return profitLoss;
	}
	
	private static double calculatePurchasePrice(List<Holding> holdingList, Order order){
		
		boolean isExist = false;
		
		for(Holding holding : holdingList){

			if(order.getStockSymbol().equals(holding.getStockSymbol())){
				isExist = true;

				if(order.getTransacType() == TransactionType.Buy){
					purchasePrice = ((order.getOrderQuantity() * order.getPriceExecuted()) + (holding.getRemainingQuantity() * holding.getPricePaid()))/(order.getOrderQuantity() + holding.getRemainingQuantity());
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
	
	private static int calculateQuantity(List<Holding> holdingList, Order order){
		
		boolean isExist = false;
		
		for(Holding holding : holdingList){
			
			if(order.getStockSymbol().equals(holding.getStockSymbol())){
				
				isExist = true;
				
				if(order.getTransacType() == TransactionType.Buy){
					remainingQuantity = holding.getRemainingQuantity() + order.getOrderQuantity();
				}
				else {
					remainingQuantity = holding.getRemainingQuantity() - order.getOrderQuantity();
				}
				
			}
			
		}
		
		if(!isExist)
		{
			remainingQuantity = order.getOrderQuantity();
		}
		return remainingQuantity;
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
		
		boolean isExist = false;

		try{
			Holding newHolding = null;
			List<Holding> holdingList = HoldingService.retrieveHolding(order);
			System.out.println("HoldingList: " + holdingList + "Size: " + holdingList.size());
			purchasePrice = HoldingService.calculatePurchasePrice(holdingList, order);
			remainingQuantity = HoldingService.calculateQuantity(holdingList, order);
			moneyRealized = HoldingService.calculateMoneyRealized(holdingList, order);
			currentStockWorth = remainingQuantity * HoldingService.getCurrentMarketPrice(order.getStockSymbol());
			profitLoss = HoldingService.calculateProfitLoss(holdingList, order);
			
			if(order.getTransacType() == TransactionType.Buy){

				for(Holding holding : holdingList){
					if(order.getStockSymbol().equals(holding.getStockSymbol())){

						isExist = true;

						newHolding = HoldingDAO.retrieveIndividualHolding(order);

						newHolding.setPricePaid(purchasePrice);
						newHolding.setRemainingQuantity(remainingQuantity);
						newHolding.setCurrentStockWorth();
						newHolding.setProfitLoss(profitLoss);

						HoldingDAO.updateHoldingWhenBuy(newHolding);

						break;
					}
				}
				//System.out.println("isExist Boolean: " + isExist);
				if(!isExist){
					newHolding = new Holding(order.getUserID(), order.getStockSymbol(), remainingQuantity, purchasePrice, 0.0d, currentStockWorth, 0.0d, "SGD"); //not zero
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
						newHolding.setRemainingQuantity(remainingQuantity);
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
