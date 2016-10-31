package com.fdm.wealthnow.backendService;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Stock;
import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.common.TransactionType;

public class BuySellTask implements Runnable {

	Order order;
	StockService stockService;

	public BuySellTask(Order order) {

		this.order = order;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("BuySellTask is executing");

		TransactionType type = order.getTransacType();

		if (type == TransactionType.Buy) {

			try {
				BuyTask(order);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Exception: " + e.getMessage());
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
		
		System.out.println("I am buying stock");

		Stock stock = StockService.getInfo(order.getStockSymbol());

		if (OrderService.validateCashBalance(order)) {
			
			System.out.println("There is enough cash to buy stock!");

			if (order.getPriceType() == PriceType.Market) {
				order.setPriceExecuted(stock.getAskprice());
				order.setOrderStatus(OrderStatus.Completed);
				
				System.out.println(order);

			} else if (order.getPriceType() == PriceType.Limit) {

				if (stock.getCurrentmarketprice() <= order.getPriceExecuted())
					order.setOrderStatus(OrderStatus.Completed);

			}

		}

		if (order.getOrderStatus() == OrderStatus.Completed){
			HoldingService.updatePortfolio(order);
			OrderService.updateCompletedOrder(order);
		}
		

	}

	private void SellTask(Order order) throws Exception {

		Stock stock = StockService.getInfo(order.getStockSymbol());

		if (OrderService.validateOwnedQuantity(order) && OrderService.hasHolding(order)) {

			if (order.getPriceType() == PriceType.Market) {
				order.setPriceExecuted(stock.getBidprice());
				order.setOrderStatus(OrderStatus.Completed);

			} else if (order.getPriceType() == PriceType.Limit || order.getPriceType() == PriceType.StopLoss) {
				if (stock.getCurrentmarketprice() >= order.getPriceExecuted())
					order.setOrderStatus(OrderStatus.Completed);

			}
		}

		if (order.getOrderStatus() == OrderStatus.Completed){
			HoldingService.updatePortfolio(order);
			OrderService.updateCompletedOrder(order);
		}

	}

}
