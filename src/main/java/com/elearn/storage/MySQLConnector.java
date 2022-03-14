package com.elearn.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MySQLConnector {

	static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ELEARN", "dev", "dev");
		return con;
	}

	static void closeConnection(Connection con) throws SQLException {
		if (con != null) {
			con.close();
		}
	}
}
