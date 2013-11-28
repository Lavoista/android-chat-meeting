package it.meet.user.data;

import it.meet.entity.BlackContact;
import it.meet.entity.Conversation;
import it.meet.entity.Friend;
import it.meet.entity.FriendRequest;
import it.meet.entity.PreferedSite;
import it.meet.entity.UserProfile;
import it.meet.localdb.ConversationsAdministrator;
import it.meet.localdb.DatabaseAdministrator;
import it.meet.localdb.MessagesAdministrator;
import it.meet.localdb.UsersAdministrator;
import it.meet.service.messaging.Message;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;

public class UserDataAdministrator {
	private DatabaseAdministrator databaseAdmnistrator;
	private String localUsername;
	private String remoteUsername;
	private ArrayDeque<Conversation> conversationsDeque;
	private ArrayList<Message> lastChatMessages;
	private ArrayList<Friend> friendsList;
	private ArrayList<BlackContact> blackList;
	private ArrayList<FriendRequest> friendRequestsList;
	private ArrayList<PreferedSite> preferedSitesList;
	private UserProfile userProfile;
	private UsersAdministrator usersAdministrator;
	private MessagesAdministrator messageAdministrator;
	private ConversationsAdministrator conversationsAdministrator;

	public UserDataAdministrator(String localUsername,Context context) {
		this.localUsername = localUsername;
		databaseAdmnistrator = new DatabaseAdministrator(context);
		usersAdministrator = new UsersAdministrator(databaseAdmnistrator);
		//to do set all variables
		conversationsAdministrator = new ConversationsAdministrator(databaseAdmnistrator);
		messageAdministrator = new MessagesAdministrator(databaseAdmnistrator);
		conversationsDeque.addAll(conversationsAdministrator.getConversationsFromDb(localUsername));
		//setFriendsList
		//setFriendRequestsList
		//setBlackList
		//setPreferedSitesList
		//setUserProfile
		
		
	}

	//quando si riceve o si invia un nuovo messaggio si inserisce nel db e si aggiorna la conversationsDeque
	public void newMessageReceivedOrSended(Message message){	
		messageAdministrator.insertMessage(message);
		Iterator<Conversation> iterator = this.conversationsDeque.iterator();
		Conversation tempConv,toRemove;
		while(iterator.hasNext()){
			tempConv = iterator.next();
			if(tempConv.getRemoteUser().equals(message.getSender()) || tempConv.getRemoteUser().equals(message.getReceiver())){
				toRemove = tempConv;
				this.conversationsDeque.remove(toRemove);
			}
		}
		Conversation toInsert = new Conversation();
		String remoteUsername;
		toInsert.setLastMessageChat(message);
		if(!message.getSender().equals(this.localUsername)){
			remoteUsername = message.getSender();
			toInsert.setRemoteUser(remoteUsername);
		}
		else{
			remoteUsername = message.getReceiver();
			toInsert.setRemoteUser(remoteUsername);
		}
		byte[] photo = usersAdministrator.getUser(remoteUsername).getPhoto();
		toInsert.setRemoteUserPhoto(photo);
		this.conversationsDeque.addFirst(toInsert);
		
	}
	

	public ArrayDeque<Conversation> getConversationsDeque() {
		return conversationsDeque;
	}

	//this method returns last message sended and received to remote user
	//if remoteUser is different to param "remoteUserName" read data from database
	//else read from memory directly
	//to use in chat, when i must to refresh a conversation i read this from memory
	public ArrayList<Message> getLastChatMessages(String remoteUserName) {
		if(remoteUserName.equals(this.remoteUsername)){
			return lastChatMessages;
		}
		else{
			MessagesAdministrator chatMessagesAdministrator = new MessagesAdministrator(databaseAdmnistrator);
			this.lastChatMessages = chatMessagesAdministrator.getMessagesFromDb(localUsername,remoteUserName,"timestamp");
			this.remoteUsername = remoteUserName;
			return lastChatMessages;
		}
	}

	public ArrayList<Friend> getFriendsList() {
		return friendsList;
	}


	public ArrayList<BlackContact> getBlackList() {
		return blackList;
	}


	public ArrayList<FriendRequest> getFriendRequestsList() {
		return friendRequestsList;
	}


	public ArrayList<PreferedSite> getPreferedSitesList() {
		return preferedSitesList;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}


}
