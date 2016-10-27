package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.Holdings;

public class holdingDAO {
	private static final String STOCK_HOLDING = "stock_holding";
	
	public static Holdings retrieveHolding(long userId) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String retrieveHoldingSQL = "Select holding_id_sequence, stock_symbol, remaining_quantity, price_paid, currency from " +
										  STOCK_HOLDING + " where user_id = ?";
		
		try{
			con = DBUtil.getConnection();
		ps = con.prepareStatement(retrieveHoldingSQL);
		ps.setLong(1, userId);
		rs = ps.executeQuery();
		rs.next();
		
		Holdings holding = new Holdings(rs.getLong("holding_id_sequence"),
										userId,
										rs.getString("stock_symbol"),
										rs.getInt("remaining_quantity"),
										rs.getDouble("price_paid"),
										rs.getString("currency"));
		
		return holding;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}

}
