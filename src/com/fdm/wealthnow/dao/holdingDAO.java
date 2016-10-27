package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fdm.wealthnow.common.DBUtil;

public class holdingDAO {
	private static final String STOCK_HOLDING = "stock_holding";
	
	public Holdings retrieveHolding(long userId){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String retrieveHoldingSQL = "Select stock_symbol, remaining_quantity, price_paid, currency from " +
										  STOCK_HOLDING + " where user_id = ?";
		
		con = DBUtil.getConnection();
		ps = con.prepareStatement(retrieveHoldingSQL);
		ps.setLong(1, userId);
		
	}

}
