package com.fdm.wealthnow.backendService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.StockService;
import com.fdm.wealthnow.common.TransactionType;
import com.fdm.wealthnow.dao.HoldingDAO;
import com.fdm.wealthnow.dao.UserDAO;

public class HoldingService {

	private static final double TRANSACTION_FEE = 9.99;

	private static List<Holding> retrieveHolding(Order order) throws Exception {
		List<Holding> holding = HoldingDAO.retrieveHolding(order.getUserID());
		return holding;
	}

	public static List<Holding> callDAO(long userId) throws Exception {
		List<Holding> holding = HoldingDAO.retrieveHolding(userId);
		return holding;

	}

	public static Double getCurrentMarketPrice(String stockSymbol) throws MalformedURLException, IOException {

		double marketPrice;
		List<String> symbolList = new ArrayList<>();
		symbolList.add(stockSymbol);
		marketPrice = StockService.stringToDouble(
				StockService.stockStorage(StockService.getStockFromWeb(symbolList)).get(0).getCurrentmarketprice());
		System.out.println("marketprice is: " + marketPrice);
		return marketPrice;

	}

	private static void updateCashBalance(Order order) throws Exception {

		double cashBalanceChange;

		if (order.getTransacType() == TransactionType.Buy) {
			cashBalanceChange = ((order.getOrderQuantity() * order.getPriceExecuted()) + TRANSACTION_FEE);
			UserDAO.deductBalance(order.getUserID(), cashBalanceChange);
		} else {
			cashBalanceChange = ((order.getOrderQuantity() * order.getPriceExecuted()) - TRANSACTION_FEE);
			UserDAO.addBalance(order.getUserID(), cashBalanceChange);
		}

		System.out.println("Updated cash balance: " + cashBalanceChange);

	}

	public static void updatePortfolio(Order order) {

		Holding newHolding = null;
		double currentStockWorth = 0.0d;

		boolean isExist = false;

		try {
			List<Holding> holdingList = HoldingService.retrieveHolding(order);


			if (order.getTransacType() == TransactionType.Buy) {

				for (Holding holding : holdingList) {
					if (order.getStockSymbol().equals(holding.getStockSymbol())) {

						isExist = true;

						newHolding = HoldingDAO.retrieveIndividualHolding(order);
						
						newHolding.setHoldingQuantity(order);
						newHolding.setCurrentStockWorth();
						
						newHolding.setBoughtQuantity(order);
						newHolding.setPricePaid(order);
						
						newHolding.setProfitLoss();

						HoldingDAO.updateHoldingWhenBuy(newHolding);		

						break;
					}
				}
				// System.out.println("isExist Boolean: " + isExist);
				
				currentStockWorth = order.getOrderQuantity() * HoldingService.getCurrentMarketPrice(order.getStockSymbol());
				
				if (!isExist) {
					newHolding = new Holding(order.getUserID(), order.getStockSymbol(), order.getOrderQuantity(), order.getPriceExecuted(),
							order.getOrderQuantity(), currentStockWorth, 0, 0.0d, 0.0d, "SGD");
					HoldingDAO.storeHolding(newHolding);

				}

				HoldingService.updateCashBalance(order);

				// ----------------------------------------------------sell
				// order---------------------------------------------------------
			} else {
				for (Holding holding : holdingList) {
					if (order.getStockSymbol().equals(holding.getStockSymbol())) {

						newHolding = HoldingDAO.retrieveIndividualHolding(order);
						
						newHolding.setHoldingQuantity(order);
						newHolding.setCurrentStockWorth();
						
						newHolding.setSoldQuantity(order);
						newHolding.setMoneyRealized(order);
						
						newHolding.setProfitLoss();

						HoldingDAO.updateHoldingWhenSell(newHolding);
						HoldingService.updateCashBalance(order);
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to update portfolio due to connection error");
		}
	}

}
