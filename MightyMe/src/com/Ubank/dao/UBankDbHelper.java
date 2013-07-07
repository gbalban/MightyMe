/**
 * 
 */
package com.Ubank.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author gayasuddin
 * 
 */
public class UBankDbHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "UBanking.db";
	protected static SQLiteDatabase db;
	static final int DATABASE_VERSION = 1;
	Context context;

	public UBankDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE User_Info_Table (local_id INTEGER PRIMARY KEY AUTOINCREMENT, user_id, user_password, user_uuid)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS User_info_Table");
		onCreate(db);

	}

}
