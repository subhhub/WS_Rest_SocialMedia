package edu.subh.ws.rs.messenger.database;

import java.util.HashMap;
import java.util.Map;

import edu.subh.ws.rs.messenger.model.Comment;
import edu.subh.ws.rs.messenger.model.Message;
import edu.subh.ws.rs.messenger.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message> message = new HashMap<Long, Message>();
	private static Map<String, Profile> profile = new HashMap<String, Profile>();
	private static Map<Long, Comment> Comment = new HashMap<Long, Comment>();
	
	
	public static Map<Long, Message> getMessage() {
		return message;
	}
	public static Map<String, Profile> getProfile() {
		return profile;
	}
	public static Map<Long, Comment> getComment() {
		return Comment;
	}
	
}
