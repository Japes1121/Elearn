package com.elearn.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.elearn.storage.model.Category;
import com.elearn.storage.model.Department;
import com.elearn.storage.model.Subject;

public class ApplicationDataStorage {

	private static Connection getConnection() {
		return MySQLConnector.getMySQLConnection();
	}

	private static void closeConnection(Connection con) {
		MySQLConnector.closeConnection(con);
	}

	public static void addCategory(String categoryName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "insert into CATEGORY (CATEGORY_NAME)" + "VALUES ('" + categoryName + "')";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static void updateCategory(int categoryId, String categoryName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "UPDATE CATEGORY SET CATEGORY_NAME = '" + categoryName + "' WHERE CATEGORY_ID = "
					+ categoryId;
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static void deleteCategory(int categoryId) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE FROM CATEGORY where CATEGORY_ID = " + categoryId;
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static void addDepartment(int categoryId, String departmentName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "INSERT INTO DEPARTMENT (CATEGORY_ID,DEPARTMENT_NAME)" + "VALUES (" + categoryId + ",'"
					+ departmentName + "')";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static void updateDepartment(int departmentId, String departmentName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "UPDATE DEPARTMENT SET DEPARTMENT_NAME = '" + departmentName + "' WHERE DEPARTMENT_ID = "
					+ departmentId;
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static void deleteDepartment(int departmentId) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE FROM DEPARTMENT WHERE DEPARTMENT_ID = " + departmentId;
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static void addSubject(int departmentId, String subjectCode, String subjectName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "insert into SUBJECT (DEPARTMENT_ID,SUBJECT_CODE,SUBJECT_NAME)" + "VALUES (" + departmentId
					+ ",'" + subjectCode + "','" + subjectName + "')";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static void updateSubject(int subjectId, String subjectCode, String subjectName) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			StringBuilder bldr = new StringBuilder("UPDATE SUBJECT SET ");
			if (subjectCode != null) {
				bldr.append("SUBJECT_CODE = '").append(subjectCode).append("' ");
			}
			if (subjectName != null) {
				bldr.append("SUBJECT_NAME = '").append(subjectName).append("' ");
			}
			bldr.append(" WHERE SUBJECT_ID = ").append(subjectId);
			stmt.executeUpdate(bldr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static void deleteSubject(int subjectId) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE FROM SUBJECT WHERE SUBJECT_ID = " + subjectId;
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	public static List<Category> getAllCategories() {
		Connection con = getConnection();
		List<Category> catList = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM CATEGORY";
			ResultSet rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Category c = new Category();
					c.setCategoryId(rs.getInt("CATEGORY_ID"));
					c.setCategoryName(rs.getString("CATEGORY_NAME"));
					catList.add(c);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return catList;
	}

	public static List<Department> getAllDepartment() {
		Connection con = getConnection();
		List<Department> depttList = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM DEPARTMENT";
			ResultSet rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Department d = new Department();
					d.setCategoryId(rs.getInt("CATEGORY_ID"));
					d.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
					d.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
					depttList.add(d);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return depttList;
	}

	public static List<Subject> getAllSubjects() {
		Connection con = getConnection();
		List<Subject> subList = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM SUBJECT";
			ResultSet rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Subject s = new Subject();
					s.setSubjectId(rs.getInt("SUBJECT_ID"));
					s.setDepartmentId(rs.getInt("DEPARTMENT_ID"));
					s.setSubjectName(rs.getString("SUBJECT_NAME"));
					s.setSubjectCode(rs.getString("SUBJECT_CODE"));
					subList.add(s);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return subList;
	}
}
