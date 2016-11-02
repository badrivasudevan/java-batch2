package com.fdm.wealthnow.backendService;

import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.dao.UserDAO;

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

		User user = null;
		try {
			user = UserDAO.getUserUsingID(order.getUserID());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		synchronized (user) {

			TransactionType type = order.getTransacType();

			if (type == TransactionType.Buy) {

				try {
					BuyTask(order);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.err.println("Exception: " + e.getStackTrace() + "Exception is being caught");
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

	}

	private void BuyTask(Order order) throws Exception {

		System.out.println("Buying stock");

		List<String> list = new ArrayList<>();
		list.add(order.getStockSymbol());

		double askPrice = StockService.stringToDouble(StockService.stockStorage(StockService.getStockFromWeb(list)).get(0).getAskprice());
		
		System.out.println("----------Ask price is " + askPrice + " ----------");

		if (OrderService.validateCashBalance(order)) {

			System.out.println("Sufficent funds for" + order.getStockSymbol());
			
			if (order.getPriceType() == PriceType.Market) {

				System.out.println("Buy price of " + order.getStockSymbol() + " is "
						+ StockService.stringToDouble(StockService.stockStorage(StockService.getStockFromWeb(list)).get(0).getAskprice()));

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

		if (order.getOrderStatus() == OrderStatus.Completed) {
			HoldingService.updatePortfolio(order);
			OrderService.updateCompletedOrder(order);
		}
		System.out.println("Cash Balance: " + UserDAO.getBalance(order.getUserID()));
	}

	private static void SellTask(Order order) throws Exception {

		System.out.println("Selling stock");

		List<String> list = new ArrayList<>();
		list.add(order.getStockSymbol());

		double bidPrice = StockService.stringToDouble(StockService.stockStorage(StockService.getStockFromWeb(list)).get(0).getBidprice());
		
		System.out.println("----------Bid price is " + bidPrice + " ----------");

		if (OrderService.validateOwnedQuantity(order) && OrderService.hasHolding(order)) {

			System.out.println("Stock quantity verified.");

			System.out.println("Cash Balance: " + UserDAO.getBalance(order.getUserID()));

			if (order.getPriceType() == PriceType.Market) {

				System.out.println("Sell price of " + order.getStockSymbol() + " is "
						+ StockService.stringToDouble(StockService.stockStorage(StockService.getStockFromWeb(list)).get(0).getBidprice()));

				order.setPriceExecuted(bidPrice);
				OrderService.updatePriceExecuted(order);

				order.setOrderStatus(OrderStatus.Completed);

			} else if (order.getPriceType() == PriceType.Limit) {
				if (bidPrice >= order.getPriceExecuted())
					order.setOrderStatus(OrderStatus.Completed);
			}
			
			else if(order.getPriceType() == PriceType.StopLoss){
				
				if (bidPrice == order.getPriceExecuted())
					order.setOrderStatus(OrderStatus.Completed);
			}
		}

		if (order.getOrderStatus() == OrderStatus.Completed) {
			HoldingService.updatePortfolio(order);
			OrderService.updateCompletedOrder(order);
		}
		System.out.println("Cash Balance: " + UserDAO.getBalance(order.getUserID()));
		System.out.println("----------------Stock is sold-----------" + order);
		System.out.println("---------------------------------");
	}

}
