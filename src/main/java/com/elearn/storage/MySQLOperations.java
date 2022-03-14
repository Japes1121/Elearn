package com.elearn.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class MySQLOperations {

	static void getData(String tableName) throws ClassNotFoundException, SQLException {
		Connection con = MySQLConnector.getMySQLConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from USER");
		while (rs.next())
			System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		MySQLConnector.closeConnection(con);
	}
}
