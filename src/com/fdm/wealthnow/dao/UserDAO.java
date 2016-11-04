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
		final String connectSQL = "SELECT balance FROM " + USER_TABLE + " WHERE user_id= ?";
		
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
			final String connectSQL = "UPDATE "+ USER_TABLE +" SET balance=(balance+?) WHERE user_id = ?";
			
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
			final String connectSQL = "UPDATE " + USER_TABLE + " SET balance=(balance-?) WHERE user_id = ?";
			
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
		
		public static boolean newUser(String user_name, String full_name, String user_password,String email) throws Exception {
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			final String newUserSQL = "INSERT into "+ USER_TABLE +" (user_id, user_name, full_name, user_password,balance,email) VALUES (user_seq.nextval,?,?,?,0,?)";
			
			try {
				connection = DBUtil.getConnection();
				ps = connection.prepareStatement(newUserSQL);
				ps.setString(1, user_name);
				ps.setString(2, full_name);
				ps.setString(3, user_password);
				ps.setString(4,email);
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
			
			final String retrieveAllUserName = "SELECT user_name FROM "+ USER_TABLE;

			try{
				con = DBUtil.getConnection();
				ps = con.prepareStatement(retrieveAllUserName);
				rs = ps.executeQuery();
				
				List<String> username = new ArrayList<>();
				
				while(rs.next()){
					username.add(rs.getString("user_name").toLowerCase());
				}
				
				return username;
			}
			finally{
				DBUtil.closeConnection(rs, ps, con);
			}		
		
		}
		
		public static List<String> retrieveAllEmail() throws SQLException {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;		
			
			final String retrieveAllEmail = "SELECT email FROM "+ USER_TABLE;

			try{
				con = DBUtil.getConnection();
				ps = con.prepareStatement(retrieveAllEmail);
				rs = ps.executeQuery();
				
				List<String> email = new ArrayList<>();
				//String front = "";
				//String back = "";
				
				while(rs.next()){
				//	front = rs.getString("email").replace("@", "a");
				//	back = front.replace(".","b");
				//	email.add(back.toLowerCase());
					System.out.println("email:" + rs.getString("email"));
					email.add(rs.getString("email"));
					
					
				}
		
				return email;
			}
			finally{
				DBUtil.closeConnection(rs, ps, con);
			}		
		
		}
		
}
