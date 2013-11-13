package it.meet.entity;

import it.meet.service.messaging.Message;

import java.sql.Blob;


public class Conversation {
	private Blob remoteUserPhoto;
	private String localUser;
	private String remoteUser;
	private Message lastMessageChat;
	public String getRemoteUser() {
		return remoteUser;
	}
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}
	public String getLocalUser() {
		return localUser;
	}
	public void setLocalUser(String localUser) {
		this.localUser = localUser;
	}
	public Message getLastMessageChat() {
		return lastMessageChat;
	}
	public void setLastMessageChat(Message lastMessageChat) {
		this.lastMessageChat = lastMessageChat;
	}
	
	public Blob getRemoteUserPhoto() {
		return remoteUserPhoto;
	}
	public void setRemoteUserPhoto(Blob remoteUserPhoto) {
		this.remoteUserPhoto = remoteUserPhoto;
	}
	
}
