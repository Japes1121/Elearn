package com.elearn.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.elearn.storage.model.Notes;

public class NotesStorage {

	private static Connection getConnection() {
		return MySQLConnector.getMySQLConnection();
	}

	private static void closeConnection(Connection con) {
		MySQLConnector.closeConnection(con);
	}

	static void addNotes(int subjectId, int postedBy, String uploadedTime) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "INSERT INRO NOTES (SUBJECT_ID,POSTED_BY, UPLOADED_TIME)" + "VALUES (" + subjectId + " , "
					+ postedBy + ", '" + uploadedTime + "')";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	static void updateNotes(int subjectId, int postedBy, String uploadedTime, String updatedTime, int updatedBy) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			StringBuilder bldr = new StringBuilder("UPDATE NOTES SET ");
			if (uploadedTime != null) {
				bldr.append("UPLOADED_TIME= '").append(uploadedTime).append("' ");
			}
			if (updatedTime != null) {
				bldr.append("UPDATED_TIME= '").append(updatedTime).append("' ");
			}
			if (updatedBy > 0) {
				bldr.append("UPLOADED_BY= ").append(updatedBy);
			}

			bldr.append(" WHERE SUBJECT_ID = ").append(subjectId);
			stmt.executeUpdate(bldr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	static void deleteNotes(int subjectId) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE FROM NOTES WHERE SUBJECT_ID = " + subjectId;
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	static List<Notes> getNotes(int subjectId) {
		Connection con = getConnection();
		List<Notes> notes = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM NOTES";
			ResultSet rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Notes d = new Notes();
					d.setSubjectId(rs.getInt("SUBJECT_ID"));
					d.setNotesId(rs.getInt("NOTES_ID"));
					d.setContent(rs.getBlob("CONTENT"));
					d.setPostedBy(rs.getInt("POSTEDBY"));
					d.setUpdatedTime(rs.getString("UPDATED_TIME"));
					d.setUploadedTime(rs.getString("UPLOADED_TIME"));
					d.setUploadedBy(rs.getString("UPLOADED_BY"));
					notes.add(d);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return notes;
	}
}
