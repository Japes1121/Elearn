package com.elearn.storage;

import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {

		try {
			MySQLOperations.getData("USER");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
