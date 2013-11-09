package it.meet.localdb;

import it.meet.chat.classes.ChatMessage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class ConversationsAdministrator {
	
	/*
	 * get messages sended ad received from username
	 * reading file in localUser path
	 * ordered by date
	 * 
	 */
	public Iterator<ChatMessage> getMessagesFromDb(String localUsername,String remoteUsername){
		ArrayList<ChatMessage> listaMessaggi= new ArrayList<ChatMessage>();
		ChatMessage cm1 = new ChatMessage();
		cm1.setFromUsername(remoteUsername);
		cm1.setToUsername(localUsername);
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2013, 4, 23, 12, 10, 0);
		cm1.setData(gc);
		cm1.setText("ciao");
		
		ChatMessage cm2 = new ChatMessage();
		cm2.setFromUsername(localUsername);
		cm2.setToUsername(remoteUsername);
		gc.set(2013, 4, 23, 12, 10, 50);
		cm1.setData(gc);
		cm2.setText("ehi ciao");
		ChatMessage cm3 = new ChatMessage();
		cm3.setFromUsername(localUsername);
		cm3.setToUsername(remoteUsername);
		gc.set(2013, 4, 24, 21, 00, 10);
		cm1.setData(gc);
		cm3.setText("dove sei? Non dirmi che sei andato\n a vederti la partita del milan?");
		
		ChatMessage cm4 = new ChatMessage();
		cm4.setFromUsername(localUsername);
		cm4.setToUsername(remoteUsername);
		gc.set(2013, 4, 23, 12, 10, 50);
		cm4.setData(gc);
		cm4.setText("vabbe");
		ChatMessage cm5 = new ChatMessage();
		cm5.setFromUsername(localUsername);
		cm5.setToUsername(remoteUsername);
		gc.set(2013, 4, 24, 21, 00, 10);
		cm5.setData(gc);
		cm5.setText("tifa sempre forza napoli, capito??");
		
		listaMessaggi.add(cm1);
		listaMessaggi.add(cm2);
		listaMessaggi.add(cm3);
		listaMessaggi.add(cm4);
		listaMessaggi.add(cm5);
		return listaMessaggi.iterator();
	}
	
}
