package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.Holding;

public class HoldingDAO {
	private static final String STOCK_HOLDING = "stock_holding";

	public static Holding retrieveIndividualHolding(long userId, String stockSymbol) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Holding holding = null;
		
		final String retrieveHoldingSQL = "Select holding_id, stock_symbol, remaining_quantity, price_paid, currency from " +
										   STOCK_HOLDING + " where user_id = ? and stock_symbol in ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveHoldingSQL);
			ps.setLong(1, userId);
			ps.setString(2, stockSymbol);
			rs = ps.executeQuery();
			rs.next();
			holding = new Holding(rs.getLong("holding_id"),
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
	
	public static List<Holding> retrieveHolding(long userId) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Holding holding = null;
		List<Holding> holdingList = new ArrayList<>();
		final String retrieveHoldingSQL = "Select holding_id, stock_symbol, remaining_quantity, price_paid, currency from " +
										   STOCK_HOLDING + " where user_id = ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveHoldingSQL);
			ps.setLong(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				holding = new Holding(rs.getLong("holding_id"),
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
	
	public static void storeHolding(Holding holding) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String storeHoldingSQL = "Insert into " + STOCK_HOLDING + " values (holding_id.nextVal, ?, ?, ?, ?, ?)";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(storeHoldingSQL);
			ps.setLong(1, holding.getUserId());
			ps.setString(2, holding.getStockSymbol());
			ps.setInt(3, holding.getRemainingQuantity());
			ps.setDouble(4, holding.getPricePaid());
			ps.setString(5, holding.getCurrency());
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}

	public static void main(String[] args) throws Exception{

		//Testing retrieveHolding
		/*int holding = HoldingDAO.retrieveIndividualHolding(1L, "AAPL");
		System.out.println(holding);*/
		
		//Testing storeHolding
		/*Holding holding = new Holding(6, "APPL", 100, 99.99, "SGD");
		System.out.println(holding);
		HoldingDAO.storeHolding(holding);*/
	}

}
