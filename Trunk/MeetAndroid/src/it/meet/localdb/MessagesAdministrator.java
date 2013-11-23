package it.meet.localdb;

import it.meet.service.messaging.ContentType;
import it.meet.service.messaging.Message;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

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
			String remoteUsername) {
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		String[] columns = new String[] { "sender", "receiver", "text",
				"binary", "timestamp", "contenttype" };
		String condition = "(sender = '" + localUsername + "' AND receiver = '"
				+ remoteUsername + "') OR (receiver = '" + localUsername + "' AND sender = '"
				+ remoteUsername + "')";
		Cursor cursor = db.query("messages", columns, condition, null, null,
				null, "timestamp");
		ArrayList<Message> listaMessaggi = new ArrayList<Message>();
		for (int j = 0; j < cursor.getCount(); j++) {
			Message tempMessage = new Message();
			cursor.moveToPosition(j);
			tempMessage.setSender(cursor.getString(cursor
					.getColumnIndex("sender")));
			tempMessage.setReceiver(cursor.getString(cursor
					.getColumnIndex("receiver")));
			tempMessage.setMessage(cursor.getString(cursor
					.getColumnIndex("message")));
			tempMessage.setContent(cursor.getBlob(cursor
					.getColumnIndex("binary")));
			tempMessage.setContentType(ContentType.valueOf(cursor
					.getString(cursor.getColumnIndex("contenttype"))));
			tempMessage.setTimestamp(cursor.getString(cursor
					.getColumnIndex("timestamp")));
			listaMessaggi.add(tempMessage);
		}
		
		return listaMessaggi;
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
