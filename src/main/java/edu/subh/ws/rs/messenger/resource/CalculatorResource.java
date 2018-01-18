package edu.subh.ws.rs.messenger.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.subh.ws.rs.messenger.service.CalculatorFactory;
import edu.subh.ws.rs.messenger.service.CalculatorService;
import edu.subh.ws.rs.messenger.service.MessageService;
import edu.subh.ws.rs.messenger.service.MessageServiceImpl;

@Path("calculator")
public class CalculatorResource {

	 private static CalculatorService calculatorService =  CalculatorFactory.getCalculatorService();
	 private MessageService ms = new MessageServiceImpl();
	
	 @GET
	 @Path("/{v1}/{v2}")
//	 @Produces(MediaType.TEXT_PLAIN)
	 @Produces(MediaType.APPLICATION_JSON) 
//	 public List<Message> add(@PathParam("v1") int v1, @PathParam("v2") int v2){
	 public String add(@PathParam("v1") int v1, @PathParam("v2") int v2){
		 System.out.println("addition "+v1+" "+v2);
		 int c = v1+v2;
		 
//		 return ms.getAllMessages();
		 return "ubh";
	 }
	 
	 @GET
	 @Path("subh/{v1}/{v2}")
	 public String addOutput(@PathParam("v1") int v1, @PathParam("v2") int v2){
		 System.out.println("addition Output "+v1+" "+v2);
		 int c = v1+v2;
		 
//		 return ms.getAllMessages();
		 return "ubh";
	 }
	 
	 @POST
	 @Consumes(MediaType.TEXT_PLAIN)
	 @Produces(MediaType.TEXT_PLAIN) 
	 public Response addInput(String msg){
		 System.out.println("addition Input "+msg);
		 
		 return Response.status(Status.ACCEPTED).entity("hello Subh").build();
		 
//		 return "Post Subh=> "+msg;
	 }
	 
	 @POST
	 @Path("input2")
	 @Consumes(MediaType.TEXT_PLAIN)
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String addInput2(){
		 System.out.println("addition Input 2 ");
		 
		 return "Post Subh 2";
	 }
	 
	 /*public static void main(String[] args) {
		 System.out.println("ok");
		 int d = calculatorService.add(100, 200);
		System.out.println(" calculatorService.add(100, 200) "+d);
	 }*/
	 
}
