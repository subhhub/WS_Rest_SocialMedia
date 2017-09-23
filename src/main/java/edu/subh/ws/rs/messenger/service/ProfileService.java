package edu.subh.ws.rs.messenger.service;

import java.util.List;

import edu.subh.ws.rs.messenger.model.Profile;

public interface ProfileService {

	public List<Profile> getAllProfilees();
	
	public Profile getProfile(String profileName);
	
	public Profile addProfile(Profile prf);
	
	public Profile updateProfile(Profile prf);
	
	public Profile removeProfile(String profileName);
}
