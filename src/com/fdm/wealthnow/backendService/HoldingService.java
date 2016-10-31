package com.fdm.wealthnow.backendService;

import java.util.List;
import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.dao.HoldingDAO;
import com.fdm.wealthnow.dao.UserDAO;

public class HoldingService{
	
	private static final double TRANSACTION_FEE = 9.99;
	
	private static List<Holding> retrieveHolding(Order order) throws Exception{
		List<Holding> holding = HoldingDAO.retrieveHolding(order.getUserID()); 
		return holding;
	}
	
	
	private static double calculatePurchasePrice(List<Holding> holdingList, Order order){
		
		double purchasePrice = 0.0d;
		
		for(Holding holding : holdingList){
			
			purchasePrice = holding.getPricePaid();
			
			if(order.getStockSymbol().equals(holding.getStockSymbol())){
				if(order.getTransacType() == TransactionType.Buy){
					purchasePrice = ((order.getOrderQuantity() * order.getPriceExecuted()) + (holding.getRemainingQuantity() * holding.getPricePaid()))/(order.getOrderQuantity() + holding.getRemainingQuantity());
				}
				else {
					purchasePrice = holding.getPricePaid();
				}
			}
		}
		return purchasePrice;
	}
	
	private static int calculateQuantity(List<Holding> holdingList, Order order){
		
		int remainingQuantity = 0;
		
		for(Holding holding : holdingList){
			/*System.out.println("Stock symbol of holding: " + holding.getStockSymbol());
			System.out.println("Stock symbol of order: " + order.getStockSymbol());*/
			if(order.getStockSymbol().equals(holding.getStockSymbol())){
				
				if(order.getTransacType() == TransactionType.Buy){
					remainingQuantity = holding.getRemainingQuantity() + order.getOrderQuantity();
				}
				else {
					remainingQuantity = holding.getRemainingQuantity() - order.getOrderQuantity();
				}
				
			}
			
		}
		return remainingQuantity;
	}
	
	private static double calculateCashBalance(List<Holding> holdingList, Order order) throws Exception{
		
		double cashBalance = UserDAO.getBalance(order.getUserID());
		
		for(Holding holding : holdingList){
			
			if(order.getStockSymbol().equals(holding.getStockSymbol())){
				
				if(order.getTransacType() == TransactionType.Buy){
					cashBalance = cashBalance - ((order.getOrderQuantity() * order.getPriceExecuted()) + TRANSACTION_FEE);
				}
				else {
					cashBalance = cashBalance + ((order.getOrderQuantity() * order.getPriceExecuted()) - TRANSACTION_FEE);
				}
			}
			
		}
		
		
		return cashBalance;
	}
	
	public static void updatePortfolio(Order order) {
		
		List<Holding> holdingList = HoldingService.retrieveHolding(order);
		
		if(order.getTransacType() == TransactionType.Buy){
			
			for(Holding holding : holdingList){
				if(order.getStockSymbol() == holding.getStockSymbol()){
					HoldingService.calculatePurchasePrice(holdingList, order);
					HoldingService.calculateQuantity(holdingList, order);
					HoldingService.calculateCashBalance(holdingList, order);
					break;
				}
				
			}
			//else insert new entry
		}
		else{
			for(Holding holding : holdingList){
				if(order.getStockSymbol() == holding.getStockSymbol()){
					//call methods
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		Order order = new Order(1, TransactionType.Sell, 50, "AAPL", Term.GoodForDay, PriceType.Market, 9.99, OrderStatus.Pending);
		List<Holding> holdingList = HoldingService.retrieveHolding(order);
		System.out.println("Updated price: " + HoldingService.calculatePurchasePrice(holdingList, order));
		System.out.println("Updated quantity: " + HoldingService.calculateQuantity(holdingList, order));
	}
	
}
