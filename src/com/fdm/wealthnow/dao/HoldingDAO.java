package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.Holding;
import com.fdm.wealthnow.common.Order;

public class HoldingDAO {
	private static final String STOCK_HOLDING = "stock_holding";

	public static Holding retrieveIndividualHolding(Order order) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Holding holding = null;
		
		final String retrieveHoldingSQL = "Select holding_id, stock_symbol, holding_quantity, price_paid, bought_Quantity, currentStock_Worth, sold_quantity, money_realised, profit_loss, currency from " +
										   STOCK_HOLDING + " where user_id = ? and stock_symbol in ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(retrieveHoldingSQL);
			ps.setLong(1, order.getUserID());
			ps.setString(2, order.getStockSymbol());
			rs = ps.executeQuery();
			rs.next();
			holding = new Holding(rs.getLong("holding_id"),
								  order.getUserID(),
								  rs.getString("stock_symbol"),
								  rs.getInt("holding_quantity"),
								  rs.getDouble("price_paid"),
								  rs.getInt("bought_Quantity"),
								  rs.getDouble("currentStock_Worth"),
								  rs.getInt("sold_quantity"),
								  rs.getDouble("money_realised"),
								  rs.getDouble("profit_loss"),
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
		final String retrieveHoldingSQL = "Select holding_id, stock_symbol, holding_quantity, price_paid, bought_Quantity, currentStock_Worth, sold_quantity, money_realised, profit_loss, currency from " +
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
									  rs.getInt("holding_quantity"),
									  rs.getDouble("price_paid"),
									  rs.getInt("bought_Quantity"),
									  rs.getDouble("currentStock_Worth"),
									  rs.getInt("sold_quantity"),
									  rs.getDouble("money_realised"),
									  rs.getDouble("profit_loss"),
									  rs.getString("currency"));
				
				holdingList.add(holding);
			}
			return holdingList;
		}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
	}
	
	public static void updateHoldingWhenBuy(Holding holding) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String updateHoldingSQL = "Update " + STOCK_HOLDING + " set price_paid = ?, holding_quantity = ?, bought_Quantity = ?,  currentStock_Worth = ?, profit_loss = ? where user_id = ? and stock_symbol = ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(updateHoldingSQL);
			ps.setDouble(1, holding.getPricePaid());
			ps.setInt(2, holding.getHoldingQuantity());
			ps.setInt(3, holding.getBoughtQuantity());
			ps.setDouble(4, holding.getCurrentStockWorth());
			ps.setDouble(5, holding.getProfitLoss());
			ps.setLong(6, holding.getUserId());
			ps.setString(7, holding.getStockSymbol());
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
	
	public static void updateHoldingWhenSell(Holding holding) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String updateHoldingSQL = "Update " + STOCK_HOLDING + " set price_paid = ?, holding_quantity = ?, sold_quantity = ?, money_realised = ?, currentStock_Worth = ?, profit_loss = ? where user_id = ? and stock_symbol = ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(updateHoldingSQL);
			ps.setDouble(1, holding.getPricePaid());
			ps.setInt(2, holding.getHoldingQuantity());
			ps.setInt(3, holding.getSoldQuantity());
			ps.setDouble(4, holding.getMoneyRealized());
			ps.setDouble(5, holding.getCurrentStockWorth());
			ps.setDouble(6, holding.getProfitLoss());
			ps.setLong(7, holding.getUserId());
			ps.setString(8, holding.getStockSymbol());
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
	
	public static void storeHolding(Holding holding) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String storeHoldingSQL = "Insert into " + STOCK_HOLDING + " values (holding_id.nextVal, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(storeHoldingSQL);
			ps.setLong(1, holding.getUserId());
			ps.setString(2, holding.getStockSymbol());
			ps.setInt(3, holding.getHoldingQuantity());
			ps.setDouble(4, holding.getPricePaid());
			ps.setInt(5, holding.getBoughtQuantity());
			ps.setDouble(6, holding.getCurrentStockWorth());
			ps.setInt(7, holding.getSoldQuantity());
			ps.setDouble(8, holding.getMoneyRealized());
			ps.setDouble(9, holding.getProfitLoss());
			ps.setString(10, holding.getCurrency());
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}
	
public static void removeHolding(Order order) throws SQLException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		final String removeHoldingSQL = "Delete from " + STOCK_HOLDING + " where user_id = ? and stock_symbol in ?";

		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(removeHoldingSQL);
			ps.setLong(1, order.getUserID());
			ps.setString(2, order.getStockSymbol());
			
			ps.executeUpdate();
			con.commit();
			
			}
		finally{
			DBUtil.closeConnection(rs, ps, con);
		}		
		
	}

	public static void main(String[] args) throws Exception{
		
		/*Order order = new Order(1, TransactionType.Sell, 50, "S59", Term.GoodForDay, PriceType.Market, 3.66, OrderStatus.Pending);
		
		//Testing retrieveIndividualHolding
		Holding holding = HoldingDAO.retrieveIndividualHolding(order);
		System.out.println(holding);*/
		
		//Testing storeHolding
/*		Holding holding = new Holding(31, "S41", 100, 2.18, 100, 216, 0, 0, 0, "SGD");
		System.out.println(holding);
		HoldingDAO.storeHolding(holding);
		*/
		//Testing removeHolding
		/*Order order = new Order(6, TransactionType.Sell, 50, "APPL", Term.GoodForDay, PriceType.Market, 9.99, OrderStatus.Pending);
		HoldingDAO.removeHolding(order);*/
		
		//Testing updateHolding
		/*Holding holding = new Holding(10, "AAPL", 222, 99.99, "SGD");
		System.out.println(holding);
		HoldingDAO.updateHolding(holding);*/
	}

}
