package com.fdm.wealthnow.backendService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.dao.OrderDAO;

public class OrderService{

	public static List<Order> getPendingOrders() throws SQLException {
		// TODO Auto-generated method stub
		
		List<Order> orderList = new ArrayList<>();
		
		orderList = OrderDAO.fetchOrders(OrderStatus.Pending);
		
		
		return orderList;
	}
	
	

}
