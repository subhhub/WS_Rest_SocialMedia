package edu.subh.ws.rs.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.subh.ws.rs.messenger.model.Comment;
import edu.subh.ws.rs.messenger.service.CommentService;
import edu.subh.ws.rs.messenger.service.CommentServiceImpl;

/**
 * @author esubkew
 * 
 * sub resouce for message
 *
 */
@Path("/")		//Sub resouce path
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService cs = new CommentServiceImpl();
	
	@GET
	public List<Comment> getAllComments(@PathParam ("messageId") long messageId){
		System.out.println("message id "+messageId);
		return cs.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam ("messageId") long messageId, Comment cmnt){
		return cs.addComment(messageId, cmnt);
	}
	
	@PUT
	@PathParam ("{commentId}")
	public Comment updateComment(@PathParam ("messageId") long messageId, 
									@PathParam ("commentId") long commentId,
									Comment cmnt){
		return cs.updateComment(messageId, cmnt);
	}
	
	@DELETE
	@PathParam ("{commentId}")
	public Comment deleteComment(@PathParam ("messageId") long messageId, 
								@PathParam ("commentId") long commentId){
		return cs.removeComment(messageId, commentId);
	}
	
	
	
	/*@GET
	public String test(){
		return "sub resouce new";
	}
	@GET
	@Path("{commentId}")
	public String test(@PathParam ("messageId") String messageId, @PathParam ("commentId") String commentId){
		return "Message id "+messageId+" sub resouce new "+commentId;
	}*/
}
