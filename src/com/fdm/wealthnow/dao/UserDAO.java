package com.fdm.wealthnow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.wealthnow.common.DBUtil;
import com.fdm.wealthnow.common.User;
import com.fdm.wealthnow.common.UserAuth;

public class UserDAO {
	private static final String USER_TABLE = "users";

	public static UserAuth authenticate(String username, String password) throws Exception {

		if (checkPassword(username, password)) {
			return new UserAuth(getUser(username));
		} else {
			return new UserAuth("Invalid username or password");
		}
	}

	public static boolean checkPassword(String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String checkPasswordSQL = "Select user_name, user_password from " + USER_TABLE
				 					  + " where user_name = ? and user_password = ?";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(checkPasswordSQL);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			return rs.next();
		} 
		finally {
			DBUtil.closeConnection(rs, ps, con);
		}
	}

	public static User getUser(String userName) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String connectSQL = "SELECT user_id, user_name, full_name FROM " + 
								  USER_TABLE + " WHERE user_name= ?";
		
		try {
			connection = DBUtil.getConnection();
			ps = connection.prepareStatement(connectSQL);
			ps.setString(1, userName);
			rs = ps.executeQuery();
			rs.next();
			User user = new User(rs.getInt("user_id"), 
			  				     rs.getString("user_name"), 
							 	 rs.getString("full_name"));
			return user;
		}
		finally {
			DBUtil.closeConnection(rs,  ps, connection);
		}
	} 
	
	public static User getUserUsingID(long userID) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String connectSQL = "SELECT user_id, user_name, full_name FROM " + 
								  USER_TABLE + " WHERE user_ID= ?";
		
		try {
			connection = DBUtil.getConnection();
			ps = connection.prepareStatement(connectSQL);
			ps.setLong(1, userID);
			rs = ps.executeQuery();
			rs.next();
			User user = new User(rs.getInt("user_id"), 
			  				     rs.getString("user_name"), 
							 	 rs.getString("full_name"));
			return user;
		}
		finally {
			DBUtil.closeConnection(rs,  ps, connection);
		}
	} 
	
	public static float getBalance(long user_id) throws Exception {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		final String connectSQL = "SELECT balance FROM users WHERE user_id= ?";
		
		try {
			connection = DBUtil.getConnection();
			ps = connection.prepareStatement(connectSQL);
			ps.setLong(1, user_id);
			rs = ps.executeQuery();
			rs.next();
	
			return rs.getFloat("balance");
		}
		finally {
			DBUtil.closeConnection(rs,  ps, connection);
		}
	}
		
		public static boolean addBalance(long user_id, double cash) throws Exception {
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			final String connectSQL = "Update users SET balance=(balance+?) where user_id = ?";
			
			try {
				connection = DBUtil.getConnection();
				ps = connection.prepareStatement(connectSQL);
				ps.setDouble(1, cash);
				ps.setLong(2, user_id);
				rs = ps.executeQuery();
				connection.commit();
		
				return true;
			}
			finally {
				DBUtil.closeConnection(rs,  ps, connection);
			}
	}
	
		public static boolean deductBalance(long user_id, double cash) throws Exception {
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			final String connectSQL = "Update users SET balance=(balance-?) where user_id = ?";
			
			try {
				connection = DBUtil.getConnection();
				ps = connection.prepareStatement(connectSQL);
				ps.setDouble(1, cash);
				ps.setLong(2, user_id);
				rs = ps.executeQuery();
				connection.commit();
		
				return true;
			}
			finally {
				DBUtil.closeConnection(rs,  ps, connection);
			}
	}
		
		public static boolean newUser(String user_name, String full_name, String user_password) throws Exception {
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			final String newUserSQL = "Insert into users (user_id, user_name, full_name, user_password) values (user_seq.nextval,?,?,?)";
			
			try {
				connection = DBUtil.getConnection();
				ps = connection.prepareStatement(newUserSQL);
				ps.setString(1, user_name);
				ps.setString(2, full_name);
				ps.setString(3, user_password);//here
				rs = ps.executeQuery();
				connection.commit();
		
				return true;
			}
			finally {
				DBUtil.closeConnection(rs,  ps, connection);
			}
	}
		
		public static List<String> retrieveAllUserName() throws SQLException {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;		
			
			final String retrieveAllUserName = "Select user_name from users";

			try{
				con = DBUtil.getConnection();
				ps = con.prepareStatement(retrieveAllUserName);
				rs = ps.executeQuery();
				
				List<String> username = new ArrayList<>();
				
				while(rs.next()){
					username.add(rs.getString("user_name"));
				}
				
				return username;
			}
			finally{
				DBUtil.closeConnection(rs, ps, con);
			}		
		
		}
		
}
