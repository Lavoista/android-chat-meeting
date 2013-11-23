package it.meet.user.data;

import it.meet.entity.Conversation;
import it.meet.service.messaging.Message;

import java.util.ArrayList;

public class UserData {
	private String username;
	private ArrayList<Conversation> conversationsList;
	private ArrayList<Message> lastMessagesList;
	private ArrayList<Friend> friendsList;
	private ArrayList<BlackContact> blackList;
	private ArrayList<FriendRequest> friendRequestsList;
	private ArrayList<PreferedSite> preferedSitesList;
	private UserProfile userProfile;

	public UserData(String username) {
		this.username = username;
		//to do set all variables
		
		//setConversationsList
		//setLastMessagesList
		//setFriendsList
		//setFriendRequestsList
		//setBlackList
		//setPreferedSitesList
		//setUserProfile
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<Conversation> getConversationsList() {
		return conversationsList;
	}

	public void setConversationsList(ArrayList<Conversation> conversationsList) {
		this.conversationsList = conversationsList;
	}

	public ArrayList<Message> getLastMessagesList() {
		return lastMessagesList;
	}

	public void setLastMessagesList(ArrayList<Message> lastMessagesList) {
		this.lastMessagesList = lastMessagesList;
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
