package it.meet.localdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersAdministrator {
	private DatabaseAdministrator databaseAdministrator;
	
	public UsersAdministrator(DatabaseAdministrator databaseAdministrator){
		this.databaseAdministrator = databaseAdministrator;
	}

	public void insertUser(String username, String name,
			String surname) {
		ContentValues v = new ContentValues();
		v.put("username", username);
		v.put("name", name);
		v.put("surname", surname);
		databaseAdministrator.getReadableDatabase().insert("user", null, v);
	}
	
	public Cursor getUser()
	{
		return (databaseAdministrator.getReadableDatabase().query(
			"user", 
			new String[]{ "username", "name", "surname" }, 
			null, 
			null,
			null, 
			null, 
			null));
	}

	
}
