package com.fdm.wealthnow.backendService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;

public class RetrieveOrder {
	private static final String STOCK_ORDER = "stock_order";

	public static List<Order> retrieveOrder(OrderStatus orderStatus) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String status = null;
		List<Order> orderList = new ArrayList<>();

		final String retrieveOrderSQL = "Select order_id, user_id, transaction_type, purchased_quantity, stock_symbol, "
				+ "term, order_date, price_type, price_executed, order_status from " +
				STOCK_ORDER + " where order_status = ?";
		
		if(orderStatus == OrderStatus.Pending){
			status = OrderStatus.Pending.toString();
		}
		else if (orderStatus == OrderStatus.Completed){
			status = OrderStatus.Completed.toString();
		}
		else{ 
			status = OrderStatus.Cancelled.toString();
		}
		

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveOrderSQL);
			ps.setString(1, status);
			rs = ps.executeQuery();
			Order order = null;
			while (rs.next()){
				order = new Order(rs.getLong("order_id"),
								   rs.getLong("user_id"),
								   rs.getDate("order_date"),
								   TransactionType.valueOf(rs.getString("transaction_type")),
								   rs.getInt("purchased_quantity"),
								   rs.getString("stock_symbol"),
								   Term.valueOf(rs.getString("term")),
								   PriceType.valueOf(rs.getString("price_type")),
								   rs.getDouble("price_executed"),
								   OrderStatus.valueOf(rs.getString("order_status")));
			}

						
			orderList.add(order);
		
		return orderList;	
	} 
		
	finally{
		DBUtil.closeConnection(rs, ps, con);
	}

}

public static void main(String[] args) throws Exception {

	List<Order> order = retrieveOrder(OrderStatus.Pending);
	System.out.println(order);
}
}



