/**
 * 
 */
package com.Ubank.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * @author gayasuddin
 * 
 */
public abstract class UBankDAO {
	protected UBankDbHelper dbHelper;
	private String tableName;
	protected static SQLiteDatabase db;

	public UBankDAO(String table) {
		this.tableName = table;
	}

	protected void closeCursor(Cursor cursor) {
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
	}

	protected void closeStatement(SQLiteStatement sqLiteStatement) {
		if (sqLiteStatement != null) {
			sqLiteStatement.close();
		}

	}

	public SQLiteDatabase getDb() {
		return db;
	}

	public static void setDb(SQLiteDatabase db) {
		UBankDAO.db = db;
	}

	public static void clearAllData() {
		if (db != null) {
			db.delete("User_Info_Table", null, null);
		}
	}
}
