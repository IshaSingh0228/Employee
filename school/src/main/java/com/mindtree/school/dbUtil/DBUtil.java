package com.mindtree.school.dbUtil;

import java.sql.*;

import com.mindtree.school.exception.daoException.DatabaseFailedException;
public class DBUtil {
	private final String URL = "jdbc:mysql://localhost:3306/school";
	private final String PASSWORD = "isha";
	private final String USER_NAME = "root";

	public Connection getConnection() throws DatabaseFailedException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			throw new DatabaseFailedException("Database connection error", e);
		}
		return con;
	}


	public void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void closeConnection(ResultSet con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void closeConnection(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}