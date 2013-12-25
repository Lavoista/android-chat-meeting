package it.meet.localdb;

import java.util.ArrayList;

import it.meet.entity.BlackContact;
import it.meet.entity.Friend;
import it.meet.entity.User;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersAdministrator {
	private DatabaseAdministrator databaseAdministrator;

	public UsersAdministrator(DatabaseAdministrator databaseAdministrator) {
		this.databaseAdministrator = databaseAdministrator;
	}

	public void insertUser(User user) {
		ContentValues v = new ContentValues();
		v.put("username", user.getUsername());
		v.put("name", user.getName());
		v.put("surname", user.getSurname());
		v.put("photo", user.getPhoto());
		v.put("sex", user.getSex());
		databaseAdministrator.getReadableDatabase().insert("user", null, v);
	}

	public ArrayList<User> getAllUsers(String condition) {
		ArrayList<User> toReturn = new ArrayList<User>();
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		User user = new User();
		String sql = "SELECT username,name,surname,photo,sex FROM users where 1 = 1 and "
				+ condition;
		Cursor cursor = db.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		cursor.moveToPrevious();
		while (cursor.moveToNext()) {
			user = new User();
			user.setUsername(cursor.getString(0));
			user.setName(cursor.getString(1));
			user.setSurname(cursor.getString(2));
			user.setPhoto(cursor.getBlob(3));
			user.setSex(cursor.getString(4));
			toReturn.add(user);
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		db.close();
		return toReturn;
	}

	public User getUser(String username) {
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		User toReturn = new User();
		String sql = "SELECT username,name,surname,photo FROM users where username = '"
				+ username + "'";
		Cursor cursor = db.rawQuery(sql, new String[] {});
		if (cursor.moveToFirst()) {
			toReturn.setUsername(cursor.getString(0));
			toReturn.setName(cursor.getString(1));
			toReturn.setSurname(cursor.getString(2));
			toReturn.setPhoto(cursor.getBlob(3));
			toReturn.setSex(cursor.getString(4));
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		db.close();
		if (cursor.getCount() == 0) {
			return null;
		} else {
			return toReturn;
		}
	}

	public ArrayList<Friend> getAllFriends(String localUsername) {
		ArrayList<Friend> toReturn = new ArrayList<Friend>();
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		Friend friend;
		String sql = "SELECT username,name,surname,photo,sex FROM users,friends WHERE friends.friend = users.username"
				+ " AND friends.user = '"
				+ localUsername
				+ "' ORDER BY username";
		Cursor cursor = db.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		cursor.moveToPrevious();
		while (cursor.moveToNext()) {
			friend = new Friend();
			friend.setUsername(cursor.getString(0));
			friend.setName(cursor.getString(1));
			friend.setSurname(cursor.getString(2));
			friend.setPhoto(cursor.getBlob(3));
			friend.setSex(cursor.getString(4));
			toReturn.add(friend);
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		db.close();
		return toReturn;
	}

	public ArrayList<BlackContact> getBlackList(String username) {
		ArrayList<BlackContact> toReturn = new ArrayList<BlackContact>();
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		BlackContact blockedContact;
		String sql = "SELECT username,name,surname,photo,sex FROM users,blacklist WHERE "+
				"blacklist.user = '"+username+"' AND users.username = blacklist.blocked"+
				"' ORDER BY username";
		Cursor cursor = db.rawQuery(sql, new String[] {});
		cursor.moveToFirst();
		cursor.moveToPrevious();
		while (cursor.moveToNext()) {
			blockedContact = new BlackContact();
			blockedContact.setUsername(cursor.getString(0));
			blockedContact.setName(cursor.getString(1));
			blockedContact.setSurname(cursor.getString(2));
			blockedContact.setPhoto(cursor.getBlob(3));
			blockedContact.setSex(cursor.getString(4));
			toReturn.add(blockedContact);
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		db.close();
		return toReturn;

	}

}
