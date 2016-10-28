package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;

public class OrderDAO {
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	
	private static String getCurrentTimeStamp() {
		
		java.util.Date today = new java.util.Date();
		String date = dateFormat.format(today.getTime());

		return date;
	}

	public static boolean storeOrder(Order order) throws SQLException{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		final String insertOrder = "INSERT INTO stock_order (order_Id, user_Id, stock_Symbol, order_Date,"
									+ "transaction_Type, purchased_Quantity, term, price_Type, price_Executed, order_Status)"
									+ " values (order_id.nextVal, ?, ?, to_date(?, 'yyyy/mm/dd hh24:mi:ss'),"
									+ "?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(insertOrder);
			ps.setLong(1, order.getUserID());
			ps.setString(2, order.getStockSymbol());	
			ps.setString(3, getCurrentTimeStamp());
			ps.setString(4, order.getTransacType().toString());
			ps.setLong(5, order.getOrderQuantity());
			ps.setString(6, order.getTerm().toString());
			ps.setString(7, order.getPriceType().toString());
			ps.setDouble(8, order.getPriceExecuted());
			ps.setString(9, order.getOrderStatus().toString());
			
			ps.executeUpdate();
			
			
			con.commit();
			
			return true;	
			
		} finally{
			// TODO Auto-generated catch block
			DBUtil.closeConnection(rs, ps, con);
		}

	}
	
	
	public static List<Order> fetchOrders(OrderStatus status) throws SQLException{
		
		List<Order> orderList = new ArrayList<>();
		
		
		//Fetch Orders by batch code
		
		return orderList;
		
	}

	

}
