package com.fdm.wealthnow.backendService;

import java.util.List;
import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.dao.HoldingDAO;

public class HoldingService{
	
	private static List<Holding> retrieveHolding(Order order) throws Exception{
		List<Holding> holding = HoldingDAO.retrieveHolding(order.getUserID()); 
		return holding;
	}
	
	
	private static double calculatePurchasePrice(List<Holding> holdingList, Order order){
		
		System.out.println("Inside function");
		double purchasePrice = 0.0d;
		
		for(Holding holding : holdingList){
			
			System.out.println("Order symbol: " + order.getStockSymbol());
			System.out.println("Holding symbol: " + holding.getStockSymbol());
			
			if(order.getStockSymbol().equals(holding.getStockSymbol())){
				System.out.println("found stock symbol");
				if(order.getTransacType() == TransactionType.Buy){
					System.out.println("within loop");
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
			if(order.getStockSymbol() == holding.getStockSymbol()){
				
				if(order.getTransacType() == TransactionType.Buy){
					remainingQuantity = holding.getRemainingQuantity() + order.getOrderQuantity();
				}
				else {
					remainingQuantity = holding.getRemainingQuantity() + order.getOrderQuantity();
				}
			}
			
		}
		return remainingQuantity;
	}
	
	private static double getCashBalance(){
		double cashBalance = 0.0d;
		
		return cashBalance;
	}
	
	public static void updatePortfolio(Order order) throws Exception{
		
		List<Holding> holdingList = HoldingService.retrieveHolding(order);
		
		if(order.getTransacType() == TransactionType.Buy){
			
			for(Holding holding : holdingList){
				if(order.getStockSymbol() == holding.getStockSymbol()){
					//call methods
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
		Order order = new Order(1, TransactionType.Buy, 100, "AAPL", Term.GoodForDay, PriceType.Market, 9.99, OrderStatus.Pending);
		List<Holding> holdingList = HoldingService.retrieveHolding(order);
		System.out.println(HoldingService.calculatePurchasePrice(holdingList, order));
	}
	
}
