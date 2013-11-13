package it.meet.localdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersAdministrator {
	private SQLiteDatabase db;

	public UsersAdministrator(SQLiteDatabase db) {
		this.db = db;
	}

	public void insertUser(String username, String name,
			String surname) {
		ContentValues v = new ContentValues();
		v.put("username", username);
		v.put("name", name);
		v.put("surname", surname);
		db.insert("user", null, v);
	}
	
	public Cursor getUser()
	{
		return (this.db.query(
			"user", 
			new String[]{ "username", "name", "surname" }, 
			null, 
			null,
			null, 
			null, 
			null));
	}

	
}
