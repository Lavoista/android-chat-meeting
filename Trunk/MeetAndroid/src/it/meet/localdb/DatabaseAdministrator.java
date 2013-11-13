package it.meet.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdministrator extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "meet.db";
	private static final int SCHEMA_VERSION = 1;

	public DatabaseAdministrator(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE user (username TEXT PRIMARY KEY ,"
				+ " name TEXT NOT NULL,surname TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
