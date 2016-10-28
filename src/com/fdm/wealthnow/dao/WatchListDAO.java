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
	
	public static HashMap<String, String> retrieveWatchlist(int userId) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
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
			//List<String> list = new ArrayList<>();
			//list.addAll(watchlistforuserid.values());
		
			//return list;
			return watchlistforuserid;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
	
	public static List<String> retrieveStockSymbol(String w_id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		
		//Get the key from the hashmap which is w_id based on the w_name the user chooses
		final String retrieveStockSymbolSQL = "Select symbol from watchlist_stocks where w_id=?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveStockSymbolSQL);
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

		HashMap<String,String> watchlist = WatchListDAO.retrieveWatchlist(4);
		System.out.println(watchlist);
		List<String> list2 = WatchListDAO.retrieveStockSymbol("w1");
		System.out.println(list2);
	}
}
