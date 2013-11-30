package it.meet.localdb;

import java.util.ArrayList;

import it.meet.entity.User;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersAdministrator {
	private DatabaseAdministrator databaseAdministrator;
	
	public UsersAdministrator(DatabaseAdministrator databaseAdministrator){
		this.databaseAdministrator = databaseAdministrator;
	}

	public void insertUser(User user){
		ContentValues v = new ContentValues();
		v.put("username", user.getUsername());
		v.put("name", user.getName());
		v.put("surname",user.getSurname());
		v.put("photo", user.getPhoto());
		databaseAdministrator.getReadableDatabase().insert("user", null, v);
	}
	
	
	
	public ArrayList<User> getAllUsers(String condition){
		ArrayList<User> toReturn = new ArrayList<User>();
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		User user = new User();
		String sql = "SELECT username,name,surname,photo FROM users where 1 = 1 and "+condition;
		Cursor cursor = db.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		cursor.moveToPrevious();
		while(cursor.moveToNext()){
			user = new User();
			user.setUsername(cursor.getString(0));
			user.setName(cursor.getString(1));
			user.setSurname(cursor.getString(2));
			user.setPhoto(cursor.getBlob(3));
			toReturn.add(user);
	    }
		if (cursor != null && !cursor.isClosed()) {
	        cursor.close();
	    }
	    db.close();
	    return toReturn;
	}
	
	public User getUser(String username){
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		User toReturn = new User();
		String sql = "SELECT username,name,surname,photo FROM users where username = '"+username+"'";
		Cursor cursor = db.rawQuery(sql, new String[] {});
		if(cursor.moveToFirst()){
			toReturn.setUsername(cursor.getString(0));
			toReturn.setName(cursor.getString(1));
			toReturn.setSurname(cursor.getString(2));
			toReturn.setPhoto(cursor.getBlob(3));
	    }
		if (cursor != null && !cursor.isClosed()) {
	        cursor.close();
	    }
	    db.close();
	    if(cursor.getCount() == 0){
	        return null;
	    } else {
	        return toReturn;
	    }
	}

	
}
