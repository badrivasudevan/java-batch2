package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.Holdings;

public class HoldingDAO {
	private static final String STOCK_HOLDING = "stock_holding";

	public static List<Holdings> retrieveHolding(long userId) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Holdings holding = null;
		List<Holdings> holdingList = new ArrayList<>();
		final String retrieveHoldingSQL = "Select holding_id_sequence, stock_symbol, remaining_quantity, price_paid, currency from " +
										   STOCK_HOLDING + " where user_id = ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveHoldingSQL);
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				holding = new Holdings(rs.getLong("holding_id_sequence"),
									   userId,
									   rs.getString("stock_symbol"),
									   rs.getInt("remaining_quantity"),
									   rs.getDouble("price_paid"),
									   rs.getString("currency"));
				holdingList.add(holding);
			}
			return holdingList;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
	}

	public static void main(String[] args) throws Exception{

		List<Holdings> holding = HoldingDAO.retrieveHolding(1L);
		System.out.println(holding);
	}

}
