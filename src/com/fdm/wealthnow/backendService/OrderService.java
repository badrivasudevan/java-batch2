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
	
	String errorMsg;

	public OrderService(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public static List<Order> getPendingOrders() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Order> orderList = new ArrayList<>();
		
		orderList = OrderDAO.fetchOrder(OrderStatus.Pending);
		
		for(Order order: orderList){
			
			System.out.println(order);
		}
		
		
		return orderList;
	}
	
	public static List<Order> callOrderDAO(long userId) throws Exception{
		List<Order> orderList= OrderDAO.fetchAll(userId); 
		return orderList;
	}

	public static ArrayList<String> callDAO(long userId) throws Exception{
		ArrayList<String> stockSymbols = OrderDAO.fetchStockSymbol(userId); 
		return stockSymbols;

	}

	public static void updateCompletedOrder(Order order) throws SQLException{
		
		OrderDAO.updateOrderStatus(order);
	}
	
	public static void updatePriceExecuted(Order order) throws SQLException{
		
		OrderDAO.updatePriceExecuted(order);
	}
	
	public static boolean validateCashBalance(Order order) throws Exception{
		
		System.out.println("Price executed (in orderService): " + order.getPriceExecuted() + " of " + order.getStockSymbol());
		double totalAmount = order.getOrderQuantity() * order.getPriceExecuted();

		if (totalAmount > UserDAO.getBalance(order.getUserID())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateOwnedQuantity(Order order) throws Exception{
		
		int ownedQuantity = HoldingDAO.retrieveIndividualHolding(order).getHoldingQuantity();

		if (order.getOrderQuantity() > ownedQuantity)
			return false;
		else
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
	
	public String getErrorMsg(){
		return errorMsg;
	}
	
	

}
