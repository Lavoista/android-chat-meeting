package it.meet.user.data;

import it.meet.entity.Conversation;
import it.meet.localdb.ConversationsAdministrator;
import it.meet.localdb.DatabaseAdministrator;
import it.meet.localdb.MessagesAdministrator;
import it.meet.service.messaging.Message;

import java.util.ArrayList;

import android.content.Context;

public class UserDataAdministrator {
	private DatabaseAdministrator databaseAdmnistrator;
	private String localUsername;
	private String remoteUsername;
	private ArrayList<Conversation> conversationsList;
	private ArrayList<Message> lastChatMessages;
	private ArrayList<Friend> friendsList;
	private ArrayList<BlackContact> blackList;
	private ArrayList<FriendRequest> friendRequestsList;
	private ArrayList<PreferedSite> preferedSitesList;
	private UserProfile userProfile;

	public UserDataAdministrator(String localUsername,Context context) {
		this.localUsername = localUsername;
		databaseAdmnistrator = new DatabaseAdministrator(context);
		//to do set all variables
		ConversationsAdministrator ConversationsAdministrator = new ConversationsAdministrator(databaseAdmnistrator);
		setConversationsList(ConversationsAdministrator.getConversationsFromDb(localUsername));
		//setFriendsList
		//setFriendRequestsList
		//setBlackList
		//setPreferedSitesList
		//setUserProfile
		
		
	}

	public String getUsername() {
		return localUsername;
	}

	public void setUsername(String username) {
		this.localUsername = username;
	}

	public ArrayList<Conversation> getConversationsList() {
		return conversationsList;
	}

	public void setConversationsList(ArrayList<Conversation> conversationsList) {
		this.conversationsList = conversationsList;
	}

	//this method returns last message sended and received to remote user
	//if remoteUser is different to params read data from databases
	//else read from memory directly
	public ArrayList<Message> getLastChatMessages(String remoteUserName) {
		if(remoteUserName.equals(this.remoteUsername)){
			return lastChatMessages;
		}
		else{
			MessagesAdministrator chatMessagesAdministrator = new MessagesAdministrator(databaseAdmnistrator);
			this.lastChatMessages = chatMessagesAdministrator.getMessagesFromDb(localUsername,remoteUserName);
			this.remoteUsername = remoteUserName;
			return lastChatMessages;
		}
	}

	public ArrayList<Friend> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(ArrayList<Friend> friendsList) {
		this.friendsList = friendsList;
	}

	public ArrayList<BlackContact> getBlackList() {
		return blackList;
	}

	public void setBlackList(ArrayList<BlackContact> blackList) {
		this.blackList = blackList;
	}

	public ArrayList<FriendRequest> getFriendRequestsList() {
		return friendRequestsList;
	}

	public void setFriendRequestsList(
			ArrayList<FriendRequest> friendRequestsList) {
		this.friendRequestsList = friendRequestsList;
	}

	public ArrayList<PreferedSite> getPreferedSitesList() {
		return preferedSitesList;
	}

	public void setPreferedSitesList(ArrayList<PreferedSite> preferedSitesList) {
		this.preferedSitesList = preferedSitesList;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
