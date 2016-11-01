package com.fdm.wealthnow.backendService;

import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.dao.UserDAO;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class BuySellTask implements Runnable {

	Order order;
	StockService stockService;

	public BuySellTask(Order order) {

		this.order = order;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("Buy/Sell Task is executing");

		TransactionType type = order.getTransacType();

		if (type == TransactionType.Buy) {

			try {
				BuyTask(order);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Exception: " + e.getStackTrace() +"Exception is being caught");
			}

		} else {
			
			try {
				SellTask(order);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Exception: " + e.getMessage());
			}

		}

	}

	private void BuyTask(Order order) throws Exception {
		
		System.out.println("Buying stock");

		StockService s1 = new StockService();
		List<String> list = new ArrayList<>();
		list.add(order.getStockSymbol());
		
		double askPrice= s1.stockStorage(s1.getStockFromWeb(list)).get(0).getAskprice();

		if (OrderService.validateCashBalance(order)) {
			
			System.out.println("Sufficent funds.");
			if (order.getPriceType() == PriceType.Market) {

				System.out.println("Buy price of " + order.getStockSymbol() +" is "+ s1.stockStorage(s1.getStockFromWeb(list)).get(0).getAskprice());
				
				order.setPriceExecuted(askPrice);
				OrderService.updatePriceExecuted(order);
				order.setOrderStatus(OrderStatus.Completed);
				
				System.out.println("----------------Stock is bought-----------" + order);
				System.out.println("---------------------------------");

			} else if (order.getPriceType() == PriceType.Limit) {

				if (askPrice <= order.getPriceExecuted())
					order.setOrderStatus(OrderStatus.Completed);

			}
		}

		if (order.getOrderStatus() == OrderStatus.Completed){
			HoldingService.updatePortfolio(order);
			OrderService.updateCompletedOrder(order);
		}
		System.out.println("Cash Balance: " + UserDAO.getBalance(order.getUserID()));
	}

	private static void SellTask(Order order) throws Exception {
		
		System.out.println("Selling stock");
		
		StockService s1 = new StockService();
		List<String> list = new ArrayList<>();
		list.add(order.getStockSymbol());
		
		double bidPrice= s1.stockStorage(s1.getStockFromWeb(list)).get(0).getBidprice();

		if (OrderService.validateOwnedQuantity(order) && OrderService.hasHolding(order)) {
			
			System.out.println("Stock quantity verified.");
			
			System.out.println("Cash Balance: " + UserDAO.getBalance(order.getUserID()));

			if (order.getPriceType() == PriceType.Market) {
				
				System.out.println("Sell price of " + order.getStockSymbol() +" is "+ s1.stockStorage(s1.getStockFromWeb(list)).get(0).getBidprice());
				
				System.out.println("Price before" + order.getPriceExecuted());
				order.setPriceExecuted(bidPrice);
				OrderService.updatePriceExecuted(order);
				System.out.println("Price after" + order.getPriceExecuted());
				order.setOrderStatus(OrderStatus.Completed);
				System.out.println("----------------Stock is sold-----------" + order);
				System.out.println("---------------------------------");

			} else if (order.getPriceType() == PriceType.Limit || order.getPriceType() == PriceType.StopLoss) {
				if (bidPrice >= order.getPriceExecuted())
					order.setOrderStatus(OrderStatus.Completed);

			}
		}

		if (order.getOrderStatus() == OrderStatus.Completed){
			HoldingService.updatePortfolio(order);
			OrderService.updateCompletedOrder(order);
		}
		System.out.println("Cash Balance: " + UserDAO.getBalance(order.getUserID()));
	}
	

	/*public static void main(String[] args) throws Exception {
		Order order = new Order(1,TransactionType.Buy,1,"S51",Term.GoodForDay,PriceType.Market,0,OrderStatus.Pending);
		SellTask(order);
	}*/
}
