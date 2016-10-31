package com.fdm.wealthnow.backendService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.UserDAO;



public class OrderService{

	public static List<Order> getPendingOrders() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Order> orderList = new ArrayList<>();
		
		orderList = OrderDAO.fetchOrder(OrderStatus.Pending);
		
		
		return orderList;
	}
	
	public static boolean validateCashBalance(Order order) throws Exception{
		
		double totalAmount = order.getOrderQuantity() * order.getPriceExecuted();
		System.out.println("Total Amount: " + totalAmount);

		if (totalAmount > UserDAO.getBalance(order.getUserID())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateOwnedQuantity(Order order){
		
		int ownedQuantity = PortfolioManager.getPortfolio(order.getUserID()).getStocks().get(order.getStockSymbol())
				.getQuantity();

		System.out.println("Owned quantity: " + ownedQuantity);
		if (quantity > ownedQuantity)
			return false;

		return true;

	}
	
	

}
