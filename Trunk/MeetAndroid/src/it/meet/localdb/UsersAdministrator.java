package it.meet.localdb;

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
	
	
	
	public User[] getAllUsers(){
		return null;
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
