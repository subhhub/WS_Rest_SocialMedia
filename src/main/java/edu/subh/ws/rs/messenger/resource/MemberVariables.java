package edu.subh.ws.rs.messenger.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("memberVariables/{name}")
public class MemberVariables {

	@PathParam("name") private String name;
	@QueryParam("age") private int age;
	
	@GET
	public String getMessage(){
		
		return "Member variables values are name=> "+name+" and age=> "+age;
	}
	
}
