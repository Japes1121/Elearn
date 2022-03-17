package com.elearn.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.elearn.storage.model.Discussion;

public class DiscussionStorage {
	private static Connection getConnection() {
		return MySQLConnector.getMySQLConnection();
	}

	private static void closeConnection(Connection con) {
		MySQLConnector.closeConnection(con);
	}

	static void addDiscussion(int notesId, String postedBy, String createdTime) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "INSERT INTO DISCUSSION (NOTES_ID, POSTED_BY, CREATED_TIME)" + "VALUES(" + notesId + ", "
					+ postedBy + ",'" + createdTime + "')";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	static void updateDiscussion(int discussionId, String modifiedTime) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			StringBuilder bldr = new StringBuilder("UPDATE DISCUSSION SET ");
			if (modifiedTime != null) {
				bldr.append("MODIFIED_TIME = '").append(modifiedTime).append("'");
			}
			bldr.append(" WHERE DISCUSSION_ID = ").append(discussionId);
			stmt.executeUpdate(bldr.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	static void deleteDisussion(int discussionId) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "DELETE FROM DISCUSSION WHERE DISCUSSION_ID = " + discussionId;
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}

	static List<Discussion> getDiscussion(int noteId) {
		Connection con = getConnection();
		List<Discussion> discussions = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM DISCUSSION";
			ResultSet rs = stmt.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					Discussion d = new Discussion();
					d.setNoteId(rs.getInt("NOTES_ID"));
					d.setDiscussionId(rs.getInt("DISCUSSION_ID"));
					d.setContent(rs.getBlob("CONTENT"));
					d.setPostedBy(rs.getInt("POSTEDBY"));
					d.setCreatedTime(rs.getString("CREATED_TIME"));
					d.setModifiedTime(rs.getString("MODIFIED_TIME"));
					discussions.add(d);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return discussions;
	}
}
