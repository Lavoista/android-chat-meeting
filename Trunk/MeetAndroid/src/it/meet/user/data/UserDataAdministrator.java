package it.meet.user.data;

import it.meet.entity.BlackContact;
import it.meet.entity.Conversation;
import it.meet.entity.Friend;
import it.meet.entity.FriendRequest;
import it.meet.entity.PreferedSite;
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
	private static UserDataAdministrator instance;
	private String remoteUsername;
	private ArrayDeque<Conversation> conversationsDeque;
	private ArrayList<Message> lastChatMessages;
	private ArrayList<Friend> friendsList;
	private ArrayList<BlackContact> blackList;
	private ArrayList<FriendRequest> friendRequestsList;
	private ArrayList<PreferedSite> preferedSitesList;
	private UsersAdministrator usersAdministrator;
	private MessagesAdministrator messageAdministrator;
	private ConversationsAdministrator conversationsAdministrator;
	String PREFS_NAME = "MeetPreferFile";

	public static UserDataAdministrator getInstance(Context context) {
		if (instance == null) {
			instance = new UserDataAdministrator(context);
		}
		return instance;
	}

	

	private UserDataAdministrator(Context context) {
		if (!context.getSharedPreferences(PREFS_NAME, 0)
				.getString("loggedUser", "").isEmpty()) {
			this.localUsername = context.getSharedPreferences(PREFS_NAME, 0)
					.getString("loggedUser", "");
		} else {
			this.localUsername = "";
		}
		databaseAdmnistrator = new DatabaseAdministrator(context);

		// Get All Friends
		usersAdministrator = new UsersAdministrator(databaseAdmnistrator);
		friendsList = usersAdministrator.getAllFriends(localUsername);

		// Get All Conversations
		conversationsAdministrator = new ConversationsAdministrator(
				databaseAdmnistrator);
		ArrayList<Conversation> conversazioni = conversationsAdministrator
				.getConversationsFromDb(localUsername);
		conversationsDeque = new ArrayDeque<Conversation>();
		conversationsDeque.addAll(conversazioni);

		// Get All Messages
		messageAdministrator = new MessagesAdministrator(databaseAdmnistrator);

		blackList = usersAdministrator.getBlackList(localUsername);

		// setFriendRequestsList
		// setPreferedSitesList
		// setUserProfile

	}

	public String getLocalUsername() {
		return localUsername;
	}

	public void setLocalUsername(String localUsername) {
		this.localUsername = localUsername;
	}
	
	// quando si riceve o si invia un nuovo messaggio si inserisce nel db e si
	// aggiorna la conversationsDeque
	public void newMessageReceivedOrSended(Message message) {
		messageAdministrator.insertMessage(message);
		Iterator<Conversation> iterator = this.conversationsDeque.iterator();
		Conversation tempConv, toRemove;
		while (iterator.hasNext()) {
			tempConv = iterator.next();
			if (tempConv.getRemoteUser().equals(message.getSender())
					|| tempConv.getRemoteUser().equals(message.getReceiver())) {
				toRemove = tempConv;
				this.conversationsDeque.remove(toRemove);
			}
		}
		Conversation toInsert = new Conversation();
		String remoteUsername;
		toInsert.setLastMessageChat(message);
		if (!message.getSender().equals(this.localUsername)) {
			remoteUsername = message.getSender();
			toInsert.setRemoteUser(remoteUsername);
		} else {
			remoteUsername = message.getReceiver();
			toInsert.setRemoteUser(remoteUsername);
		}
		byte[] photo = usersAdministrator.getUser(remoteUsername).getPhoto();
		toInsert.setRemoteUserPhoto(photo);
		this.conversationsDeque.addLast(toInsert);

	}

	public ArrayDeque<Conversation> getConversationsDeque() {
		return conversationsDeque;
	}

	// this method returns last message sended and received to remote user
	// if remoteUser is different to param "remoteUserName" read data from
	// database
	// else read from memory directly
	// to use in chat, when i must to refresh a conversation i read this from
	// memory
	public ArrayList<Message> getLastChatMessages(String remoteUserName) {
		if (remoteUserName.equals(this.remoteUsername)) {
			return lastChatMessages;
		} else {
			MessagesAdministrator chatMessagesAdministrator = new MessagesAdministrator(
					databaseAdmnistrator);
			this.lastChatMessages = chatMessagesAdministrator
					.getMessagesFromDb(localUsername, remoteUserName,
							"timestamp");
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

	public Friend getFriend(String remoteUsername2) {
		return this.usersAdministrator
				.getFriend(remoteUsername2, localUsername);
	}

}
