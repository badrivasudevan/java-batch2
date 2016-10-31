package com.fdm.wealthnow.backendService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.dao.HoldingDAO;
import com.fdm.wealthnow.dao.OrderDAO;
import com.fdm.wealthnow.dao.UserDAO;



public class OrderService{

	public static List<Order> getPendingOrders() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Order> orderList = new ArrayList<>();
		
		orderList = OrderDAO.fetchOrder(OrderStatus.Pending);
		
		for(Order order: orderList){
			
			System.out.println(order);
		}
		
		
		return orderList;
	}
	
	public static void updateCompletedOrder(Order order) throws SQLException{
		
		OrderDAO.updateOrder(order);
	}
	
	public static boolean validateCashBalance(Order order) throws Exception{
		
		double totalAmount = order.getOrderQuantity() * order.getPriceExecuted();
		System.out.println("Total Amount: " + totalAmount);

		if (totalAmount > UserDAO.getBalance(order.getUserID())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateOwnedQuantity(Order order) throws Exception{
		
		int ownedQuantity = HoldingDAO.retrieveIndividualHolding(order).getRemainingQuantity();

		System.out.println("Owned quantity: " + ownedQuantity);
		if (order.getOrderQuantity() > ownedQuantity)
			return false;

		return true;

	}
	
	public static boolean hasHolding(Order order) throws Exception{
		
		List<Holding> holdingList = HoldingDAO.retrieveHolding(order.getUserID());
		
			for(Holding holding : holdingList){
			
			if(order.getStockSymbol().equals(holding.getStockSymbol()))
				return true;
		}
			
			return false;
		
	}
	
	

}
