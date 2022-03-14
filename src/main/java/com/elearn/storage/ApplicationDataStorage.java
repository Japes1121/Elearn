package com.elearn.storage;

import java.sql.Connection;
import java.sql.Statement;

public class ApplicationDataStorage {

	private static Connection getConnection() {
		return MySQLConnector.getMySQLConnection();
	}

	private static void closeConnection(Connection con) {
		MySQLConnector.closeConnection(con);
	}

	static void addCategory(String categoryName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
		} catch (Exception e) {

		} finally {
			closeConnection(con);
		}

	}

}
