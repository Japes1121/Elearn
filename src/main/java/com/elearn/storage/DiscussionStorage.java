package com.elearn.storage;

import java.sql.Connection;
import java.sql.Statement;

public class DiscussionStorage {
	private static Connection getConnection() {
		return MySQLConnector.getMySQLConnection();
	}
	private static void closeConnection(Connection con) {
		MySQLConnector.closeConnection(con);
	}
	
	static void addDiscussion(int notesId, String postedBy) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			String query = "INSERT INTO DISCUSSION (NOTES_ID,POSTED_BY)" + "VALUES(" + notesId + ", " + postedBy + ")";		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
	}


	static void updateDiscussion(int discussionId, String postedBy, String createdTime, String modifiedTime) {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			StringBuilder bldr = new StringBuilder("UPDATE DISCUSSION SET");
			if(postedBy != null) {
				bldr.append("POSTED_BY = '").append(postedBy).append("' ");
			}
			if(createdTime != null) {
			bldr.append("CREATED_TIME = '").append(createdTime).append("' ");
			}
			if(modifiedTime != null) {
				bldr.append("MODIFIED_TIME = '").append(modifiedTime).append("' ");
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
	}
