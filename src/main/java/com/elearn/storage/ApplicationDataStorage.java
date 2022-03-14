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

	static void addCategory(String CATEGORY_NAME) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "insert into CATEGORY (CATEGORY_NAME)" + "VALUES (" + CATEGORY_NAME + ")";
			
		 stmt.execute(query);
		
		} catch (Exception e) {
		} finally {
			closeConnection(con);
		}
	}

	static void addDepartment(int CATEGORY_ID, int DEPARTMENT_ID, String DEPARTMENT_NAME) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "insert into DEPARTMENT (CATEGORY_ID,DEPARTMENT_ID,DEPARTMENT_NAME)" + "VALUES ('1',11,'CSE&IT' )";
			stmt.executeUpdate(query);
			query = "insert into DEPARTMENT (CATEGORY_ID,DEPARTMENT_ID,DEPARTMENT_NAME)" + "VALUES ('2', 12,'ECE')";
			stmt.executeUpdate(query);
			query = "insert into DEPARTMENT (CATEGORY_ID,DEPARTMENT_ID,DEPARTMENT_NAME)" + "VALUES ('3',13,'EEE')";
			stmt.executeUpdate(query);
			query = "insert into DEPARTMENT (CATEGORY_ID,DEPRTMENT_ID,DEPARTMENT_NAME)" + "VALUES ('4',14,'MECH')";
			stmt.executeUpdate(query);
			query = "insert into DEPARTMENT (CATEGORY_ID,DEPARTMENT_ID,DEPARTMENT_NAME)" + "VALUES ('5',15,'CIVIL')";
			stmt.executeUpdate(query);
			
			
			stmt.execute(query);
			
			
			
		} catch (Exception e) {
 
		} finally {
			closeConnection(con);
		}
	}
	
	static void addSubject( int DEPARTMENT_ID, int SUBJECT_ID, String SUBJECT_CODE,String SUBJECT_NAME) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "insert into SUBJECT (DEPARTMENT_ID,SUBJECT_ID,SUBJECT_CODE,SUBJECT_NAME)" + "VALUES ('11',101," + SUBJECT_CODE +","+SUBJECT_NAME+")";
			stmt.executeUpdate(query);
			
			
			stmt.execute(query);
		} catch (Exception e) {
			 
		} finally {
			closeConnection(con);
		}
	}
}

