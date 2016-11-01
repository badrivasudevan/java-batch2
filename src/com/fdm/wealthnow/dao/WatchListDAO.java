package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.fdm.wealthnow.common.DBUtil;

public class WatchListDAO {

	//Activate connection to SQL database where the watchlist_table is stored 
	//extract information and update the information based on the method being called in java
	//
	
	public static HashMap<String, String> retrieveWatchlist(long userId) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String retrieveWatchlistSQL = "select userswatchlist.user_id,userswatchlist.w_id,watchlist.watchlist_name from userswatchlist right join watchlist on watchlist.w_id=userswatchlist.w_id where userswatchlist.user_id=? order by watchlist_name";

		
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveWatchlistSQL);
			ps.setLong(1, userId);
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
	
	public static List<String> retrieveAllStockForWatchlist(String w_id) throws SQLException {
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
				watchlistname.add(rs.getString("symbol"));
			}
			
			return watchlistname;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
	
	}
	
	public static boolean removeWatchlist(String w_id) throws SQLException {
		Connection con = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;		
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;		
		PreparedStatement ps3 = null;
		ResultSet rs3 = null;		
		
		final String removeWatchlistFromUserswatchlist = "DELETE from userswatchlist where w_id=?";
		final String removeWatchlistFromWatchlist = "DELETE from watchlist where w_id=?";
		final String removeWatchlistFromWatchlistStocks = "DELETE from watchlist_stocks where w_id=?";

		try{
			con = DBUtil.getConnection();
			ps1 = con.prepareStatement(removeWatchlistFromUserswatchlist);
			ps1.setString(1, w_id);
			ps2 = con.prepareStatement(removeWatchlistFromWatchlist);
			ps2.setString(1, w_id);
			ps3 = con.prepareStatement(removeWatchlistFromWatchlistStocks);
			ps3.setString(1, w_id);
			rs1 = ps1.executeQuery();
			rs2 = ps2.executeQuery();
			rs3 = ps3.executeQuery();

			con.commit();
			
			return true;
		}
		finally{
			rs3.close();
			ps3.close();
			rs2.close();
			ps2.close();
			DBUtil.closeConnection(rs1, ps1, con);
		}		
	
	}
	
	public static boolean removeStock(String w_id, String symbol) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		

		final String removeStockSymbolFromWatchlist = "DELETE from watchlist_stocks where w_id=? and symbol=?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(removeStockSymbolFromWatchlist);
			ps.setString(1, w_id);
			ps.setString(2, symbol);
			rs = ps.executeQuery();
		
			con.commit();
			
			return true;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
	
	}
	
	public static boolean addStock(String w_id, String symbol) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		

		final String addStockSymbolFromWatchlist = "Insert into watchlist_stocks values (?,?)";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(addStockSymbolFromWatchlist);
			ps.setString(1, w_id);
			ps.setString(2, symbol);
			rs = ps.executeQuery();
		
			con.commit();
			
			return true;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
	
	       public static boolean createWatchlist(long userid, String watchlistname) throws SQLException {
	              Connection con = null;
	              PreparedStatement ps1 = null;
	              ResultSet rs1 = null;      
	              PreparedStatement ps2 = null;
	              ResultSet rs2 = null;             

	              final String createWatchlistForUser = "Insert into userswatchlist values (concat('w',watchlist_seq.nextval),?) ";
	              final String setWatchlistName = "Insert into watchlist values (concat('w',watchlist_seq.currval),?,sysdate)";
	              
	              try{
	                     con = DBUtil.getConnection();
	                     ps1 = con.prepareStatement(createWatchlistForUser);
	                     ps2 = con.prepareStatement(setWatchlistName);
	                     ps1.setLong(1, userid);
	                     ps2.setString(1, watchlistname);
	                     rs1 = ps1.executeQuery();
	                     rs2 = ps2.executeQuery();
	                     con.commit();
	                     
	                     return true;
	              }
	              finally{
	                     rs2.close();
	                     ps2.close();
	                     DBUtil.closeConnection(rs1, ps1, con);
	              }             
	       
	       }

	}
	public static void main(String[] args) throws Exception{

//		HashMap<String,String> watchlist = WatchListDAO.retrieveWatchlist(4);
//		System.out.println(watchlist);
//		List<String> list2 = WatchListDAO.retrieveAllStockForWatchlist("w1");
//		System.out.println(list2);
//		removeWatchlist("w5");
	//	removeStock("w1","S556");
//		addStock("w6","S56");
	}
}
