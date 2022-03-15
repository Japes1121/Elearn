package com.elearn.storage.model;

import java.sql.Blob;

public class Discussion {
	private int noteId;
	private int discussionId;
	private Blob content;
	private int postedBy;
	private String createdTime;
	private String modifiedTime;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public int getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(int discussionId) {
		this.discussionId = discussionId;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
