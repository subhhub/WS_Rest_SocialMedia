package edu.subh.ws.rs.messenger.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("SingleResource")
@Singleton
public class SingletonResource {

	private int count;
	
	@GET
	public String getMessage(){
		
		return "Single resource message "+count++;
	}
	
	
}
