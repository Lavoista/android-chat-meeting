package it.meet.localdb;

import it.meet.entity.Conversation;
import it.meet.service.messaging.Message;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class ConversationsAdministrator {
	DatabaseAdministrator dataBaseAdmnistrator;
	
	public ConversationsAdministrator(DatabaseAdministrator dataBaseAdmnistrator){
		this.dataBaseAdmnistrator = dataBaseAdmnistrator;
	}
	/*
	 * get all conversations with localUser = localUsername
	 * ordered by date (date of last messageChat sended or received
	 * 
	 */
	public ArrayList<Conversation> getConversationsFromDb(String localUsername){
		
		
		ArrayList<Conversation> conversationList= new ArrayList<Conversation>();
		Conversation conv1 = new Conversation();
		conv1.setLocalUser(localUsername);
		conv1.setRemoteUser("luigivorraro");
		Message cm5 = new Message();
		cm5.setSender(localUsername);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2013, 4, 24, 21, 00, 10);
		cm5.setTimestamp(gc.toString());
		cm5.setMessage("tifa sempre forza napoli, capito??");
		conv1.setLastMessageChat(cm5);
		Conversation conv2 = new Conversation();
		conv2.setLocalUser(localUsername);
		conv2.setRemoteUser("francescamiranda");
		Message cm6 = new Message();
		cm6.setSender("francescamiranda");
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.set(2013, 4, 27, 22, 00, 10);
		cm6.setTimestamp(gc2.toString());
		cm6.setMessage("ok sto al bar, ti aspetto!!");
		conv2.setLastMessageChat(cm6);
		return conversationList;
	}
	
}
