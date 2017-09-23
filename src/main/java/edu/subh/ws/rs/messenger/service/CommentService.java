package edu.subh.ws.rs.messenger.service;

import java.util.List;

import edu.subh.ws.rs.messenger.model.Comment;

public interface CommentService {

	public List<Comment> getAllComments(long messageId);
	
	public Comment getComment(long messageId, long commentId);
	
	public Comment addComment(long messageId, Comment cmnt);
	
	public Comment updateComment(long messageId, Comment cmnt);
	
	public Comment removeComment(long messageId, long commentId);
}
