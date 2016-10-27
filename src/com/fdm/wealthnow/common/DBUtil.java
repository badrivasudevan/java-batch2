package com.fdm.wealthnow.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fdm.wealthnow.common.DBConnectionFactory.ConnectionType;

public class DBUtil {
	
	public static Connection getConnection() throws SQLException {
		return DBConnectionFactory.getConnection(ConnectionType.ORACLE);
	}
	
	public static void closeConnection(ResultSet rs, Statement s, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
			}
		}
		
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
}
