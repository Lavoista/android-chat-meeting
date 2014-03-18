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

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE `users` (" +
				"`username` varchar(45) NOT NULL," +
				"`photo` blob," +
				"`name` varchar(45) DEFAULT NULL," +
				"`surname` varchar(45) DEFAULT NULL," +
				"`sex` varchar(1) DEFAULT NULL," +
				"`phrase` varchar(250) DEFAULT NULL," +
				"`birthdate` varchar(20) DEFAULT NULL," +
				"`city` varchar(100) DEFAULT NULL," +
				"`province` varchar(100) DEFAULT NULL," +
				"`nation` varchar(100) DEFAULT NULL," +
				"`phone` varchar(30) DEFAULT NULL," +
				"`favouritesex` varchar(1) DEFAULT NULL," +
				"PRIMARY KEY (`username`)" +
				");");
		db.execSQL("CREATE TABLE `messages` (" +
				"`idmessage` int(11) NOT NULL ," +
				"`text` varchar(200) DEFAULT NULL," +
				"`timestamp` varchar(20) NULL DEFAULT NULL," +
				"`contenttype` varchar(20) DEFAULT NULL," +
				"`binary` binary DEFAULT NULL," +
				"`sender` varchar(45) NOT NULL REFERENCES `users` " +
				"(`username`) ON DELETE CASCADE ON UPDATE CASCADE," +
				"`receiver` varchar(45) REFERENCES " +
				"`users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE," +
				"PRIMARY KEY (`idmessage`));");
		db.execSQL("CREATE TABLE `friends` ("+
				  "`user` varchar(45) NOT NULL REFERENCES `users`(`username`) ON DELETE CASCADE ON UPDATE CASCADE ,"+
				  "`friend` varchar(45) NOT NULL REFERENCES `users`(`username`) ON DELETE CASCADE ON UPDATE CASCADE,"+
				  "`addedDate` varchar(20) NULL DEFAULT NULL,"+
				  "PRIMARY KEY (`user`,`friend`));");
		db.execSQL("CREATE TABLE `blacklist` ("+
				  "`user` varchar(45) NOT NULL REFERENCES `users`(`username`) ON DELETE CASCADE ON UPDATE CASCADE ,"+
				  "`blocked` varchar(45) NOT NULL REFERENCES `users`(`username`) ON DELETE CASCADE ON UPDATE CASCADE,"+
				  "`blockedDate` varchar(20) DEFAULT NULL,"+
				  "PRIMARY KEY (`user`,`blocked`));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
