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
import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;

public class OrderDAO {

	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final String STOCK_ORDER = "stock_order";


	private static String getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		String date = dateFormat.format(today.getTime());

		return date;
	}
	
	public static ArrayList<String> fetchStockSymbol(Long userId) throws SQLException{  
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ArrayList<String> stocksList = new ArrayList<String>();
			String query = "Select unique stock_symbol from stock_holding where user_id = ? order by stock_symbol";

			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				stocksList.add(rs.getString("stock_symbol"));
			} 
			return stocksList;

		}
		finally{
			DBUtil.closeConnection(rs, ps, con);

		}

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


	public static List<Order> fetchOrder(OrderStatus status) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String orderStatus = null;
		List<Order> orderList = new ArrayList<>();

		final String fetchOrderSQL = "Select order_id, user_id, transaction_type, purchased_quantity, stock_symbol, "
				+ "term, order_date, price_type, price_executed, order_status from " +
				STOCK_ORDER + " where order_status = ? and rownum <10";


		if(status == OrderStatus.Pending){
			orderStatus = OrderStatus.Pending.toString();
		}
		else if (status == OrderStatus.Completed){
			orderStatus = OrderStatus.Completed.toString();
		}
		else{ 
			orderStatus = OrderStatus.Cancelled.toString();
		}


		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(fetchOrderSQL);
			ps.setString(1, orderStatus);
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

				orderList.add(order);
			}

			return orderList;	
		} 
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}
	}
	
	public static List<Order> fetchPending(long userId) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String orderStatus = null;
		List<Order> orderList = new ArrayList<>();

		final String fetchOrderSQL = "Select STOCK_SYMBOL,ORDER_ID,USER_ID,ORDER_DATE,TRANSACTION_TYPE,PURCHASED_QUANTITY,TERM,PRICE_TYPE,PRICE_EXECUTED,ORDER_STATUS from " +
				STOCK_ORDER + " where user_id = ?" + " and order_status = 'Pending'";



	
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(fetchOrderSQL);
			ps.setLong(1, userId);
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

				orderList.add(order);
			}

			return orderList;	
		} 
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}
	}
	
	public static void updatePending(int stockId) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//List<Order> orderList = new ArrayList<>();

		final String fetchOrderSQL = "Delete from " +
				STOCK_ORDER + " where order_id = ?";
			
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(fetchOrderSQL);
			ps.setInt(1, stockId);
			rs = ps.executeQuery();
			Order order = null;
			/*while (rs.next()){
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

				orderList.add(order);
			}

			return orderList;	*/
		} 
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}
	}
	
	public static void updateOrderStatus(Order order) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String updateOrderSQL = "Update " + STOCK_ORDER + " set order_status = ? where order_id = ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(updateOrderSQL);
			ps.setString(1, "Complete");
			ps.setLong(2, order.getOrderID());
			
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
	
	public static void updatePriceExecuted(Order order) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String updateOrderSQL = "Update " + STOCK_ORDER + " set price_executed = ? where order_id = ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(updateOrderSQL);
			ps.setDouble(1, order.getPriceExecuted());
			ps.setLong(2, order.getOrderID());
			
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}

}




