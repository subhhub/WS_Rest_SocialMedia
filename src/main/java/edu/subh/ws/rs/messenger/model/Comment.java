package edu.subh.ws.rs.messenger.model;

import java.util.Date;

public class Comment {

	private long commentId;
	private String message;
	private Date created;
	private String author;
	
	public Comment() {
	}
	
	
	
	public Comment(long commentId, String message, String author) {
		this.commentId = commentId;
		this.message = message;
		this.created = new Date();
		this.author = author;
	}



	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
