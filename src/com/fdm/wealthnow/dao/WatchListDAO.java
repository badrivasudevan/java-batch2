package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.Holdings;

public class WatchListDAO {

	//Activate connection to SQL database where the watchlist_table is stored 
	//extract information and update the information based on the method being called in java
	//
	
	public static List<String> retrieveStockSymbol(int userId) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		final String retrieveStockSymbolSQL = "Select symbol from watchlist_stocks where w_id=?";
//		final String retrieveWatchlistSQL = "Select w_id from userswatchlist where user_id=?";
		final String retrieveWatchlistSQL = "select userswatchlist.user_id,userswatchlist.w_id,watchlist.watchlist_name from userswatchlist right join watchlist on watchlist.w_id=userswatchlist.w_id where userswatchlist.user_id=? order by watchlist_name";

		
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveWatchlistSQL);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			
			HashMap<String, String> watchlistforuserid = new HashMap<>();
			
			while(rs.next()){
				watchlistforuserid.put(rs.getString("w_id"), rs.getString("watchlist_name"));
			}
			
			//for user's viewing purpose of all watchlists he or she possess
			List<String> list = new ArrayList<>();
			list.addAll(watchlistforuserid.values());
		
			return list;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
	
	public static List<String> retrieveWatchlistName(String w_name) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		final String retrieveWatchlistSQL = "Select watchlist_name from watchlist where w_id=?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveWatchlistSQL);
			ps.setString(1, w_id);
			rs = ps.executeQuery();
			
			List<String> watchlistname = new ArrayList<>();
			
			while(rs.next()){
				watchlistname.add(rs.getString("watchlist_name"));
			}
		
			return watchlistname;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
	
	}
	
	public static void main(String[] args) throws Exception{

		List<String> list = WatchListDAO.retrieveStockSymbol(4);
		System.out.println(list);
//		List<String> list2 = WatchListDAO.retrieveWatchlistName("w1");
//		System.out.println(list2);
	}
}
