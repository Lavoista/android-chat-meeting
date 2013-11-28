package it.meet.localdb;

import it.meet.entity.Conversation;
import it.meet.entity.User;
import it.meet.service.messaging.ContentType;
import it.meet.service.messaging.Message;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConversationsAdministrator {
	DatabaseAdministrator databaseAdministrator;
	
	public ConversationsAdministrator(DatabaseAdministrator dataBaseAdmnistrator){
		this.databaseAdministrator = dataBaseAdmnistrator;
	}
	/*
	 * get all conversations with localUser = localUsername
	 * ordered by date (date of last messageChat sended or received
	 * 
	 */
	public ArrayList<Conversation> getConversationsFromDb(String localUsername){
		//per ogni utente prendo il messaggio piu nuovo (inviato o ricevuto)
		ArrayList<Conversation> toReturn = new ArrayList<Conversation>();
		ArrayList<User> listaContatti;
		UsersAdministrator usersAdministrator = new UsersAdministrator(databaseAdministrator);
		listaContatti = usersAdministrator.getAllUsers("username !='"+localUsername+"'");
		//per ogni contatto prendo l'ultimo messaggio inviato o ricevuto da quel contatto
		Iterator<User> iteratore = listaContatti.iterator();
		while(iteratore.hasNext()){
			User remoteUser = iteratore.next();
			String remoteUsername = remoteUser.getUsername();
			MessagesAdministrator messagesAdministrator = new MessagesAdministrator(databaseAdministrator);
			ArrayList<Message> listaMessaggi = messagesAdministrator.getMessagesFromDb(localUsername, remoteUsername,"timestamp DESC");;
			if(listaMessaggi.size()>0){
				Message lastMessage  = messagesAdministrator.getMessagesFromDb(localUsername, remoteUsername,"timestamp DESC").get(0);
				Conversation temp = new Conversation();
				temp.setLastMessageChat(lastMessage);
				temp.setRemoteUser(remoteUsername);
				temp.setRemoteUserPhoto(remoteUser.getPhoto());
				toReturn.add(temp);
			}
			
		}
		return toReturn;
	}
	
}
