package it.meet.entity;

import it.meet.service.messaging.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversation implements Comparable<Object> {
	private byte[] remoteUserPhoto;
	private String remoteUsername;
	private Message lastMessageChat;
	private String remoteUserSex;

	public String getRemoteUser() {
		return remoteUsername;
	}

	public void setRemoteUser(String remoteUser) {
		this.remoteUsername = remoteUser;
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

	@Override
	public int compareTo(Object another) {
		Conversation other;
		if (another instanceof Conversation) {
			other = (Conversation) another;
		} else {
			return -1;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date thisDate = (Date) sdf.parse(this.getLastMessageChat()
					.getTimestamp());
			System.out.println("thisDate = " + thisDate);

			Date otherDate = (Date) sdf.parse(other.getLastMessageChat()
					.getTimestamp());
			System.out.println("otherDate = " + otherDate);
			return thisDate.compareTo(otherDate);
		} catch (Exception e) {
			e.printStackTrace();
			return -2;
		}
	}

	/**
	 * @return the remoteUserSex
	 */
	public String getRemoteUserSex() {
		return remoteUserSex;
	}

	/**
	 * @param remoteUserSex
	 *            the remoteUserSex to set
	 */
	public void setRemoteUserSex(String remoteUserSex) {
		this.remoteUserSex = remoteUserSex;
	}

}
