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
	
}
