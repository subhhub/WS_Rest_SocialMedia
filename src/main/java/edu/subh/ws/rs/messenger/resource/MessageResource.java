package edu.subh.ws.rs.messenger.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import edu.subh.ws.rs.messenger.model.Message;
import edu.subh.ws.rs.messenger.resource.bean.MessageFilterBean;
import edu.subh.ws.rs.messenger.service.MessageService;
import edu.subh.ws.rs.messenger.service.MessageServiceImpl;

/**
 * @author esubkew
 * 
 * All common annotations can be kept here.
 *
 */
@Path("/messages")
public class MessageResource {

	private MessageService ms = new MessageServiceImpl();
	
	/*@GET
//	@Produces(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages(){			//one way
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		return ms.getAllMessages();
	}*/
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,			//scond way
									@QueryParam("start") int start, 
									@QueryParam("size") int size){
		if(year > 0)
			return ms.getAllMessageForYear(year);
		if(start >= 0 &&  size > 0)
			return ms.getAllMessagePaginated(start, size);
		
		return ms.getAllMessages();
	}*/
	
	@GET
	/*Duplicate annotation @Produces. Repeated annotations are allowed only at source level 1.8 or above*/
//	@Produces(MediaType.APPLICATION_JSON)		//default produce annotation value is xml
//	@Produces(MediaType.APPLICATION_XML)		//default produce annotation value is xml
	
	//if there is no Produce annotable then by efault XML
//	@Produces (value ={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})	//content negotiation or multiple content type
	public List<Message> getMessages(@BeanParam MessageFilterBean msgFilterBean){
		if(msgFilterBean.getYear() > 0)
			return ms.getAllMessageForYear(msgFilterBean.getYear());
		if(msgFilterBean.getStart() >= 0 &&  msgFilterBean.getSize() > 0)
			return ms.getAllMessagePaginated(msgFilterBean.getStart(), msgFilterBean.getSize());
		
		System.out.println("hellkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		
		return ms.getAllMessages();
	}
	
	@GET
	@Path("messagesYear")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessagesByYear(@QueryParam("year") int year){
		if(year > 0)
			return ms.getAllMessageForYear(year);
		return null;
	}

	@GET
	@Path("messagesPage")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessagesPagination(@QueryParam("start") int start, @QueryParam("size") int size){
		if(start >= 0 &&  size > 0)
			return ms.getAllMessagePaginated(start, size);
		return null;
	}
		
	/*@GET
	@Path("{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long messageId){		//Basic method
		return ms.getMessage(messageId);
	}*/
	
	
	//HAETOS sharing links with response so that client can naviate further
	@GET
	@Path("{messageId}")
//	@Produces(MediaType.APPLICATION_JSON)
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo){
		Message message = ms.getMessage(messageId);
//		message.addLinks(uriInfo.getAbsolutePathBuilder().toString(), "self");
		
		/*String uri = uriInfo.getBaseUriBuilder()			//Replace this code with method "getUriForSelf"
				.path(MessageResource.class)
				.path(Long.toString(message.getId()))
				.build()
				.toString();*/
		
		String uri = getUriForSelf(uriInfo, message);
		String uriProfile = getUriForProfile(uriInfo, message);
		String uriComment = getUriForComments(uriInfo, message);
		
//		message.addLinks(uri, "self");
//		message.addLinks(uriProfile, "profile");
//		message.addLinks(uriComment, "comment");
		
		return message; 
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
			return uri;
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build()
				.toString();
			return uri;
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
			.path(MessageResource.class)
			.path(Long.toString(message.getId()))
			.build()
			.toString();
		return uri;
	}
	
	@GET
	@Path("/exception/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long messageId) {
		System.out.println(" messageId "+messageId);
		Message msg =  ms.getMessage(messageId);
		
		//Handling exception in resource is slitly better compare to handle exception in service area
		/*ErrorMessage em = new ErrorMessage("Data Not Available in Resouce", 404,"http://localhost:1010/WS_Rest_SocialMedia");
		Response resp = Response.status(Status.NOT_FOUND)
				.entity(em)
				.build();*/
		
//		if(msg == null)
//			throw new WebApplicationException(resp);	//repeare the response and pass to WebApplicationException
		
		
		return msg;
	}
	
	
	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message msg){			//message without changing response
		return ms.addMessage(msg);
	}*/
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message msg, @Context UriInfo uriInfo){
		Message addedMsg = ms.addMessage(msg);
//		return Response.status(Status.CREATED).entity(addedMsg).build();		//this is how we can change the response
		
		/*return Response.created(new URI("WS_Rest_SocialMedia/webapi/messages/"+addedMsg.getId()))
						.entity(addedMsg)
						.build();*/
		
		String newId = 	String.valueOf(addedMsg.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();		//adding status code and location using URI
		return Response.created(uri)
				.entity(addedMsg)
				.build();					//Response is using builder design pattern
	}
	
	@PUT
	@Path("{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long messageId,  Message msg){
		msg.setId(messageId);
		return ms.updateMessage(msg);
	}
	
	@DELETE
	@Path("{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long messageId){
		ms.removeMessage(messageId);
	}
	
	@Path ("/{messageId}/comments")			//sub source for message
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
