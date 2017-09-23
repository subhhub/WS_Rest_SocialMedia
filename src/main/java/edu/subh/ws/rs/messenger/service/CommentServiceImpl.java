package edu.subh.ws.rs.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.subh.ws.rs.messenger.database.DatabaseClass;
import edu.subh.ws.rs.messenger.model.Comment;
import edu.subh.ws.rs.messenger.model.Message;

public class CommentServiceImpl implements  CommentService{
	
	private static Map<Long, Message> message = DatabaseClass.getMessage();
//	private static Map<Long, Comment> comments = DatabaseClass.getComment();
	
	public List<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments = message.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
		
//		return new ArrayList<Comment>(message.get(messageId).getComments().values());
	}
	
	public Comment getComment(long messageId, long commentId){
		Map<Long, Comment> comments = message.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment cmnt){
		Map<Long, Comment> comments = message.get(messageId).getComments();
		cmnt.setCommentId(comments.size()+1);
		comments.put(cmnt.getCommentId(), cmnt);
		return cmnt;
	}
	
	public Comment updateComment(long messageId, Comment cmnt){
		Map<Long, Comment> comments = message.get(messageId).getComments();
		if(cmnt.getCommentId() == 0)
			return null;
		comments.put(cmnt.getCommentId(), cmnt);
		return cmnt;
	}
	
	public Comment removeComment(long messageId, long commentId){
		Map<Long, Comment> comments = message.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
