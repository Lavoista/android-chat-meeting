package it.meet.entity;

import it.meet.service.common.util.DateUtils;
import it.meet.service.messaging.Message;

import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.android.gms.R;

import android.util.Log;


public class Conversation implements Comparable<Object>{
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
	
	@Override
	public int compareTo(Object another){
		Conversation other;
		if(another instanceof Conversation ){
			other = (Conversation)another;
		}else{
			return -1;
		}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			

			Date thisDate = (Date)sdf.parse(this.getLastMessageChat().getTimestamp());
			System.out.println("thisDate = "+thisDate);
			
			Date otherDate =  (Date)sdf.parse(other.getLastMessageChat().getTimestamp());
			System.out.println("otherDate = "+otherDate);
			return thisDate.compareTo(otherDate);
		}
		catch(Exception e){
			e.printStackTrace();
			return -2;
		}
	}
	
}
