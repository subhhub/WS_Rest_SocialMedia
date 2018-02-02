package edu.subh.ws.rs.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.subh.ws.rs.messenger.database.DatabaseClass;
import edu.subh.ws.rs.messenger.exception.DataNotFoundException;
import edu.subh.ws.rs.messenger.model.ErrorMessage;
import edu.subh.ws.rs.messenger.model.Message;

public class MessageServiceImpl implements MessageService {
	
	private static Map<Long, Message> message = DatabaseClass.getMessage();
	
	public MessageServiceImpl() {
		message.put(1l, new Message(1, "hello service 10", "subh"));
		message.put(2l, new Message(2, "hello service 12", "subhKewat"));
		message.put(3l, new Message(3, "hello service 13", "job"));
		message.put(4l, new Message(4, "hello service 14", "poo"));
		message.put(5l, new Message(5, "hello service 15", "pri"));
	}

	public List<Message> getAllMessages(){
		return new ArrayList<Message>(message.values());
	}
	
	public List<Message> getAllMessageForYear(int year){
		List<Message> msgForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for(Message msg : message.values()){
			cal.setTime(msg.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				msgForYear.add(msg);
			}
		}
		return msgForYear;
	}

	public List<Message> getAllMessagePaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(message.values());
		if(start+size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start+size);
	}
	
	/*public Message getMessage(long id){
		return message.get(id);
	}*/
	
	public Message getMessage(long id){			//Exception Handling by General Exception
		Message msg =  message.get(id);
		if(msg == null)
			throw new DataNotFoundException("Message with Id "+id+" Not Found");
		
		return msg;
	}
	
	/*public Message getMessage(long id){			//Exception Handling by WebApplicationException 
		Message msg =  message.get(id);
		
		//This approach is not recommended to handle exception code in Service classes
		//Slitly better approach is write it in Rest resource classes.
		ErrorMessage em = new ErrorMessage("Data Not Available", 404,"http://localhost:1010/WS_Rest_SocialMedia");
		Response resp = Response.status(Status.NOT_FOUND)
				.entity(em)
				.build();
		
		if(msg == null)
			throw new WebApplicationException(resp);
//			throw new WebApplicationException(Status.NOT_ACCEPTABLE);
		
		return msg;
	}*/
	
	public Message addMessage(Message msg){
		msg.setId(message.size()+1);
		message.put(msg.getId(), msg);
		return msg;
	}
	
	public Message updateMessage(Message msg){
		if(msg.getId() == 0)
			return null;
		message.put(msg.getId(), msg);
		return msg;
	}
	
	public Message removeMessage(long id){
		return message.remove(id);
	}
	
	public static void main(String[] args) {
		System.out.println("heelo");
		
		System.out.println(new MessageServiceImpl().getAllMessageForYear(2017));
	}
}
