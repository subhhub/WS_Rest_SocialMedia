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

import edu.subh.ws.rs.messenger.model.Profile;
import edu.subh.ws.rs.messenger.service.ProfileService;
import edu.subh.ws.rs.messenger.service.ProfileServiceImpl;

/**
 * @author esubkew
 * 
 * All common annotations can be kept here.
 *
 */
@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})		//Content negotiation
public class ProfileResource {

	private ProfileService ps =new ProfileServiceImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)				//Content negotiation for singl method which accept Json
	public List<Profile> getJsonProfiles(){
		return ps.getAllProfilees();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)				//Content negotiation for singl method which accept Xml
	public List<Profile> getXmlProfiles(){
		return ps.getAllProfilees();
	}
	
	@GET
	@Path("{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName){
		return ps.getProfile(profileName);
	}
	
	@POST
	public Profile addProfile(Profile prf){
		return ps.addProfile(prf);
	}
	
	@PUT
	@Path("{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName,  Profile prf){
		prf.setProfileName(profileName);
		return ps.updateProfile(prf);
	}
	
	@DELETE
	@Path("{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName){
		ps.removeProfile(profileName);
	}
}
