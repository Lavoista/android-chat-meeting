package it.meet.entity;

import it.meet.service.messaging.Message;

import java.sql.Blob;


public class Conversation {
	private byte[] remoteUserPhoto;
	private String remoteUser;
	private Message lastMessageChat;
	public String getRemoteUser() {
		return remoteUser;
	}
	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}
	
	public Message getLastMessageChat() {
		return lastMessageChat;
	}
	public void setLastMessageChat(Message lastMessageChat) {
		this.lastMessageChat = lastMessageChat;
	}
	
	public byte[] getRemoteUserPhoto() {
		return remoteUserPhoto;
	}
	public void setRemoteUserPhoto(byte[] remoteUserPhoto) {
		this.remoteUserPhoto = remoteUserPhoto;
	}
	
}
