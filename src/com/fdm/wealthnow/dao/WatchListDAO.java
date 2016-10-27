package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;

public class WatchListDAO {

	//Activate connection to SQL database where the watchlist_table is stored 
	//extract information and update the information based on the method being called in java
	//
	
	public static List<String> retrieveStockSymbol(int userId, String watchlist) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		final String retrieveStockSymbolSQL = "Select symbol from watchlist_stocks where w_id in (?)";
		final String retrieveWatchlistSQL = "Select w_id from userswatchlist where user_id=?";
		
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveWatchlistSQL);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			rs.next();
		
			ps2 = con.prepareStatement(retrieveStockSymbolSQL);
			ps2.setString(1, rs.getString("w_id"));
			rs2 = ps2.executeQuery();
			rs2.next();
			
			List<String> list = new ArrayList<>();
			list.add(rs2.getString("symbol"));
			
			return list;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
}
