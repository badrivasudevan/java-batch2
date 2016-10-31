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
	private static double purchasePrice;
	private static int remainingQuantity;
	static boolean isExist = false;
	
	private static List<Holding> retrieveHolding(Order order) throws Exception{
		List<Holding> holding = HoldingDAO.retrieveHolding(order.getUserID()); 
		return holding;
	}
	
	
	private static double calculatePurchasePrice(List<Holding> holdingList, Order order){	
		
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

		try{
			Holding newHolding = null;
			List<Holding> holdingList = HoldingService.retrieveHolding(order);
			purchasePrice = HoldingService.calculatePurchasePrice(holdingList, order);
			remainingQuantity = HoldingService.calculateQuantity(holdingList, order);

			if(order.getTransacType() == TransactionType.Buy){

				for(Holding holding : holdingList){
					if(order.getStockSymbol().equals(holding.getStockSymbol())){

						isExist = true;

						newHolding = HoldingDAO.retrieveIndividualHolding(order);

						newHolding.setPricePaid(purchasePrice);
						newHolding.setRemainingQuantity(remainingQuantity);

						HoldingDAO.updateHolding(newHolding);

						break;
					}
				}

				if(!isExist){
					newHolding = new Holding(order.getUserID(), order.getStockSymbol(), remainingQuantity, purchasePrice, "SGD");
					HoldingDAO.storeHolding(newHolding);

				}

				HoldingService.updateCashBalance(order);
			
		//----------------------------------------------------sell order---------------------------------------------------------
		}
		else{
			for(Holding holding : holdingList){
				if(order.getStockSymbol().equals(holding.getStockSymbol())){

					if(remainingQuantity == 0){
						HoldingDAO.removeHolding(order);
						break;
					}
					else{
						newHolding = HoldingDAO.retrieveIndividualHolding(order);
						
						newHolding.setPricePaid(purchasePrice);
						newHolding.setRemainingQuantity(remainingQuantity);
						
						HoldingDAO.updateHolding(newHolding);
						HoldingService.updateCashBalance(order);
						break;
					}
				}
			}
		}
	}
		catch(Exception e){
			System.out.println("Unable to update portfolio due to connection error");
		}
	}
	
	public static void main(String[] args) throws Exception{
		Order order = new Order(5, TransactionType.Buy, 100, "AAPL", Term.GoodForDay, PriceType.Market, 150, OrderStatus.Pending);
		List<Holding> holdingList = HoldingService.retrieveHolding(order);
		System.out.println("Updated price: " + HoldingService.calculatePurchasePrice(holdingList, order));
		System.out.println("Updated quantity: " + HoldingService.calculateQuantity(holdingList, order));
		HoldingService.updatePortfolio(order);
		
	}
	
}
