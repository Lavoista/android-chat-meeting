package it.meet.localdb;

import java.util.ArrayList;

import it.meet.entity.BlackContact;
import it.meet.entity.Friend;
import it.meet.entity.User;
import it.meet.utils.DateUtil;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UsersAdministrator {
	private DatabaseAdministrator databaseAdministrator;

	public UsersAdministrator(DatabaseAdministrator databaseAdministrator) {
		this.databaseAdministrator = databaseAdministrator;
	}

	//insert a user into database
	public void insertUser(User user) {
		ContentValues v = new ContentValues();
		v.put("username", user.getUsername());
		v.put("name", user.getName());
		v.put("surname", user.getSurname());
		v.put("photo", user.getPhoto());
		v.put("sex", user.getSex());
		v.put("phrase", user.getPhrase());
		v.put("birthdate", DateUtil.getString(user.getBirthdate()));
		v.put("city", user.getCity());
		v.put("province",user.getProvince());
		v.put("nation", user.getNation());
		v.put("phone", user.getPhone());
		v.put("favouritesex", user.getFavouriteSex());
		
		databaseAdministrator.getReadableDatabase().insert("user", null, v);
	}

	public ArrayList<User> getAllUsers(String condition) {
		ArrayList<User> toReturn = new ArrayList<User>();
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		User user = new User();
		String sql = "SELECT username,name,surname,photo,sex,phrase,birthdate,city,province,nation,phone,favouritesex FROM users where 1 = 1 and "
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
			user.setPhrase(cursor.getString(5));
			user.setBirthdate(DateUtil.getDate(cursor.getString(6)));
			user.setCity(cursor.getString(7));
			user.setProvince(cursor.getString(8));
			user.setNation(cursor.getString(9));
			user.setPhone(cursor.getString(10));
			user.setFavouriteSex(cursor.getString(11));
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
		String sql = "SELECT username,name,surname,photo,phrase,birthdate,city,province,nation,phone,favouritesex FROM users where username = '"
				+ username + "'";
		Cursor cursor = db.rawQuery(sql, new String[] {});
		if (cursor.moveToFirst()) {
			toReturn.setUsername(cursor.getString(0));
			toReturn.setName(cursor.getString(1));
			toReturn.setSurname(cursor.getString(2));
			toReturn.setPhoto(cursor.getBlob(3));
			toReturn.setSex(cursor.getString(4));
			toReturn.setPhrase(cursor.getString(5));
			toReturn.setBirthdate(DateUtil.getDate(cursor.getString(7)));
			toReturn.setCity(cursor.getString(8));
			toReturn.setProvince(cursor.getString(9));
			toReturn.setNation(cursor.getString(10));
			toReturn.setPhone(cursor.getString(11));
			toReturn.setFavouriteSex(cursor.getString(12));
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
		String sql = "SELECT username,name,surname,photo,sex,phrase,birthdate,city,province,nation,phone,favouritesex FROM users,friends WHERE friends.friend = users.username"
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
			friend.setPhrase(cursor.getString(5));
			friend.setBirthdate(DateUtil.getDate(cursor.getString(6)));
			friend.setCity(cursor.getString(7));
			friend.setProvince(cursor.getString(8));
			friend.setNation(cursor.getString(9));
			friend.setPhone(cursor.getString(10));
			friend.setFavouriteSex(cursor.getString(11));
			toReturn.add(friend);
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		db.close();
		return toReturn;
	}

	public ArrayList<BlackContact> getBlackList(String localUsername) {
		ArrayList<BlackContact> toReturn = new ArrayList<BlackContact>();
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		BlackContact blockedContact;
		String sql = "SELECT users.username,users.name,users.surname,users.photo,users.sex,users.phrase,users.birthdate,blacklist.blockedDate,city,province,nation,phone,favouritesex FROM users,blacklist WHERE "+
				"blacklist.user = '"+localUsername+"' AND users.username = blacklist.blocked"+
				" ORDER BY username";
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
			blockedContact.setPhrase(cursor.getString(5));
			blockedContact.setBirthdate(DateUtil.getDate(cursor.getString(6)));
			blockedContact.setBlockedDate(DateUtil.getDate(cursor.getString(7)));
			blockedContact.setCity(cursor.getString(8));
			blockedContact.setProvince(cursor.getString(9));
			blockedContact.setNation(cursor.getString(10));
			blockedContact.setPhone(cursor.getString(11));
			blockedContact.setFavouriteSex(cursor.getString(12));
			toReturn.add(blockedContact);
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		db.close();
		return toReturn;

	}

	public Friend getFriend(String remoteUsername,String localUsername) {
		SQLiteDatabase db = databaseAdministrator.getReadableDatabase();
		Friend toReturn = new Friend();
		String sql = "SELECT username,name,surname,photo,sex,phrase,birthdate,addedDate,city,province,nation,phone,favouritesex FROM users,friends where friends.friend = users.username AND" +
				" friends.friend = '"+remoteUsername+"' AND friends.user = '"+localUsername+"'";
		System.out.println("sql = "+sql);
		Cursor cursor = db.rawQuery(sql, new String[] {});
		if (cursor.moveToFirst()) {
			toReturn.setUsername(cursor.getString(0));
			toReturn.setName(cursor.getString(1));
			toReturn.setSurname(cursor.getString(2));
			toReturn.setPhoto(cursor.getBlob(3));
			toReturn.setSex(cursor.getString(4));
			toReturn.setPhrase(cursor.getString(5));
			toReturn.setBirthdate(DateUtil.getDate(cursor.getString(6)));
			toReturn.setAddedDate(DateUtil.getDate(cursor.getString(7)));
			toReturn.setCity(cursor.getString(8));
			toReturn.setProvince(cursor.getString(9));
			toReturn.setNation(cursor.getString(10));
			toReturn.setPhone(cursor.getString(11));
			toReturn.setFavouriteSex(cursor.getString(12));
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

}
