package edu.subh.ws.rs.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.subh.ws.rs.messenger.database.DatabaseClass;
import edu.subh.ws.rs.messenger.model.Profile;

public class ProfileServiceImpl implements ProfileService {
	
	private static Map<String, Profile> profile = DatabaseClass.getProfile();
	
	public ProfileServiceImpl() {
		profile.put("subh", new Profile(1l, "subh", "subhash", "kewat"));
		profile.put("subhKewat", new Profile(2l, "subhKewat", "subh", "nishad"));
	}

	public List<Profile> getAllProfilees(){
		
		return new ArrayList<Profile>(profile.values());
	}
	
	public Profile getProfile(String profileName){
		return profile.get(profileName);
	}
	
	public Profile addProfile(Profile prf){
		prf.setProfileId(profile.size()+1);
		profile.put(prf.getProfileName(), prf);
		return prf;
	}
	
	public Profile updateProfile(Profile prf){
		if(prf.getProfileName().isEmpty())
			return null;
		profile.put(prf.getProfileName(), prf);
		return prf;
	}
	
	public Profile removeProfile(String profileName){
		return profile.remove(profileName);
	}
}
