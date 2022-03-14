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
			String query = "insert into CATEGORY (CATEGORY_NAME)" + "VALUES (" + categoryName + ")";
		
			stmt.execute(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}
	
	static void UpdateCategory(String categoryName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "(Update category set catgoryName where categoryId =1)";
		
			stmt.execute(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}
	
	
	static void deleteCategory(String categoryName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "(delete from category  where categoryId =1)";
		
			stmt.execute(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}
	


	
	
	static void addDepartment(int categoryId, String departmentName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "insert into DEPARTMENT (CATEGORY_ID,DEPARTMENT_NAME)" + "VALUES (" + categoryId + ","
					+ departmentName + ")";
			stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	
	static void updateDepartment(int categoryId, String departmentName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "(Update department set departmentName where departmentId =1)";
			stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	
	static void deleteDepartment(int categoryId, String departmentName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "(delete from department  where departmentId =1)";
			stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	
	
	
	
	static void addSubject(int departmentId, String subjectCode, String subjectName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "insert into SUBJECT (DEPARTMENT_ID,SUBJECT_CODE,SUBJECT_NAME)" + "VALUES (" + departmentId
					+ "," + subjectCode + "," + subjectName + ")";
			stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}


	static void updateSubject(int departmentId, String subjectCode, String subjectName) {
	Connection con = getConnection();
	try {
		Statement stmt = con.createStatement();
		String query = "(Update Subject setsubjectName where subjectId =1)";
		query = "(Update Subject set subjectCode where subjectId =1)";
		stmt.execute(query);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeConnection(con);
	}
 }




	static void deleteSubject(int departmentId, String subjectCode, String subjectName) {
	Connection con = getConnection();
	try {
		Statement stmt = con.createStatement();
		String query = "(delete from subject where subjectId =1)";
		stmt.execute(query);
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeConnection(con);
	}
 }

}


