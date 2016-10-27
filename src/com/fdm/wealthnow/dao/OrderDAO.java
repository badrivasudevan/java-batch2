package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.OrderStatus;
import com.fdm.wealthnow.common.Orders;
import com.fdm.wealthnow.common.PriceType;
import com.fdm.wealthnow.common.Term;
import com.fdm.wealthnow.common.TransactionType;

import oracle.sql.DATE;

public class OrderDAO {

	public static boolean storeOrder(Orders order) throws SQLException{

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

//		final String insertOrder = "INSERT INTO stock_order (order_Id_Sequence, user_Id, stock_Symbol, order_Date,"
//									+ "transaction_Type, purchased_Quantity, term, price_Type, price_Executed, order_Status)"
//									+ "values(order_id_sequence.nextVal, ?, ?, to_date('20161026','YYYYMMDD') , ?, ?, ?, ?, ?, ?);";
		
		final String insertOrder = "INSERT INTO stock_order values(order_id_sequence.nextVal, ?, ?, to_date('20161026','YYYYMMDD') , ?, ?, ?, ?, ?, ?);";
		
		System.out.println(insertOrder);



		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(insertOrder);
			ps.setLong(1, order.getUserID());
			ps.setString(2, order.getStockSymbol());
//			ps.setDate(3, DATE.toDate("26-OCT-16"));
//			
			ps.setString(3, order.getTransacType().toString());
			ps.setLong(4, order.getOrderQuantity());
			ps.setString(5, order.getTerm().toString());
			ps.setString(6, order.getPriceType().toString());
			ps.setDouble(7, order.getPriceExecuted());
			ps.setString(8, order.getOrderStatus().toString());
			
			System.out.println(insertOrder);
			
			ps.executeUpdate();
			
			con.commit();
			
			return true;	
			
		} finally{
			// TODO Auto-generated catch block
			DBUtil.closeConnection(rs, ps, con);
		}

	}
	
	public static void main(String[] args) throws SQLException {
		
		Orders order = new Orders(12, TransactionType.Buy, 100, "GOOG", Term.GoodForDay, PriceType.Market, 100.00, OrderStatus.Pending);

		boolean insert = storeOrder(order);
		
		//System.out.println(new java.sql.Date(System.currentTimeMillis()));
		
	}
}
