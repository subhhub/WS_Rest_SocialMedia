package edu.subh.ws.rs.messenger.service;

import java.util.List;

import edu.subh.ws.rs.messenger.model.Message;

public interface MessageService {

	public List<Message> getAllMessages();
	
	public List<Message> getAllMessageForYear(int year);

	public List<Message> getAllMessagePaginated(int start, int size);
	
	public Message getMessage(long id);
	
	public Message addMessage(Message msg);
	
	public Message updateMessage(Message msg);
	
	public Message removeMessage(long id);
}
