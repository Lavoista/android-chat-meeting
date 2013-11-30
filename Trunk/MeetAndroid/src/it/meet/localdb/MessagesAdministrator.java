package it.meet.localdb;

import it.meet.entity.User;
import it.meet.service.messaging.ContentType;
import it.meet.service.messaging.Message;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

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
	/*
	 * 
	 * ArrayList<Message> listaMessaggi= new ArrayList<Message>(); Message cm1 =
	 * new Message(); cm1.setSender(remoteUsername);
	 * cm1.setReceiver(localUsername); GregorianCalendar gc = new
	 * GregorianCalendar(); gc.set(2013, 4, 23, 12, 10, 0);
	 * cm1.setTimestap(gc.toString()); cm1.setMessage("ciao");
	 * 
	 * Message cm2 = new Message(); cm2.setSender(localUsername);
	 * cm2.setReceiver(remoteUsername); gc.set(2013, 4, 23, 12, 10, 50);
	 * cm1.setTimestap(gc.toString()); cm2.setMessage("ehi ciao"); Message cm3 =
	 * new Message(); cm3.setSender(localUsername);
	 * cm3.setReceiver(remoteUsername); gc.set(2013, 4, 24, 21, 00, 10);
	 * cm1.setTimestap(gc.toString()); cm3.setMessage(
	 * "dove sei? Non dirmi che sei andato\n a vederti la partita del milan?");
	 * 
	 * Message cm4 = new Message(); cm4.setSender(localUsername);
	 * cm4.setReceiver(remoteUsername); gc.set(2013, 4, 23, 12, 10, 50);
	 * cm4.setTimestap(gc.toString()); cm4.setMessage("vabbe"); Message cm5 =
	 * new Message(); cm5.setSender(localUsername);
	 * cm5.setReceiver(remoteUsername); gc.set(2013, 4, 24, 21, 00, 10);
	 * cm5.setTimestap(gc.toString());
	 * cm5.setMessage("tifa sempre forza napoli, capito??");
	 * 
	 * listaMessaggi.add(cm1); listaMessaggi.add(cm2); listaMessaggi.add(cm3);
	 * listaMessaggi.add(cm4); listaMessaggi.add(cm5); return
	 * listaMessaggi.iterator(); }
	 */

}
