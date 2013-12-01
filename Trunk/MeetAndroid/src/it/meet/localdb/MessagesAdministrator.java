package it.meet.localdb;

import it.meet.service.messaging.ContentType;
import it.meet.service.messaging.Message;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessagesAdministrator {
	private DatabaseAdministrator databaseAdministrator;
	
	public MessagesAdministrator(DatabaseAdministrator databaseAdministrator) {
		this.databaseAdministrator = databaseAdministrator;
	}

	/*
	 * get messages sended ad received from username reading file in localUser
	 * path ordered by date
	 */
	public ArrayList<Message> getMessagesFromDb(String localUsername,
			String remoteUsername,String order) {
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		/*String[] columns = new String[] { "sender", "receiver", "text",
				"binary", "timestamp", "contenttype" };*/
		String sql = "SELECT sender,receiver,text,binary,timestamp,contenttype FROM messages"+
				" where (sender = '" + localUsername + "' AND receiver = '"
				+ remoteUsername + "') OR (receiver = '" + localUsername + "' AND sender = '"
				+ remoteUsername + "') ORDER BY "+order;
		Cursor cursor = db.rawQuery(sql, new String[] {});
		/*Cursor cursor = db.query("messages", columns, condition, null, null,
				null, order);*/
		ArrayList<Message> listaMessaggi = new ArrayList<Message>();
		cursor.moveToFirst();
		cursor.moveToPrevious();
		while(cursor.moveToNext()){
			Message tempMessage = new Message();
			tempMessage.setSender(cursor.getString(cursor
					.getColumnIndex("sender")));
			tempMessage.setReceiver(cursor.getString(cursor
					.getColumnIndex("receiver")));
			if(cursor.getString(cursor.getColumnIndex("text"))!=null){
				tempMessage.setMessage(cursor.getString(cursor.getColumnIndex("text")));
			}
					
			if(cursor.getBlob(cursor.getColumnIndex("binary"))!=null){
				tempMessage.setContent(cursor.getBlob(cursor.getColumnIndex("binary")));
			}	
			
			ContentType ttk = ContentType.valueOf(cursor.getString(cursor.getColumnIndex("contenttype")));
			tempMessage.setContentType(ttk);
			String timestampTemp = cursor.getString(cursor
					.getColumnIndex("timestamp"));
			tempMessage.setTimestamp(timestampTemp);
			listaMessaggi.add(tempMessage);
		}
		
		return listaMessaggi;
	}
	
	public void insertMessage(Message message){
		ContentValues v = new ContentValues();
		v.put("text", message.getMessage());
		v.put("sender", message.getSender());
		v.put("receiver",message.getReceiver());
		v.put("binary", message.getContent());
		v.put("timestamp", message.getTimestamp());
		v.put("contenttype", message.getContentType().getContentType());
		databaseAdministrator.getReadableDatabase().insert("message", null, v);
	}
	

}
