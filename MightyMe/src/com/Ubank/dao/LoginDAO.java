/**
 * 
 */
package com.Ubank.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.ubank.dto.Login;

/**
 * @author gayasuddin
 * 
 */
public class LoginDAO extends UBankDbHelper {

	private static final String LOGIN_TABLE = "User_Info_Table";
	private static final String INSERT_USER_INFO = "insert into User_Info_Table(user_id, user_password, user_uuid) values(?, ?, ?)";
	private static final String[] USER_INFO_COLUMNS = { "local_id", "user_id",
			"user_password", "user_uuid" };
	Context context;

	public LoginDAO(Context context) {
		super(context);
	}

	public long insertLogin(Login login) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(LOGIN_TABLE, null, null);
		SQLiteStatement insertLoginStatement = null;
		try {
			insertLoginStatement = db.compileStatement(INSERT_USER_INFO);
			insertLoginStatement.bindString(1, login.getLoginId());
			insertLoginStatement.bindString(2, login.getPassword());
			insertLoginStatement.bindString(3, login.getUserUUID());
			return insertLoginStatement.executeInsert();
		} finally {
			if (insertLoginStatement != null) {
				insertLoginStatement.close();
			}
		}
	}

	public Login lookUpLoginDetail() {
		Login login = new Login();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = null;
		try {
			cursor = db.query(LOGIN_TABLE, USER_INFO_COLUMNS, null, null, null,
					null, "local_id");
			if (cursor != null && cursor.moveToFirst()) {
				login = toLogin(cursor);
			}
		} finally {
			if (!cursor.isClosed()) {
				cursor.close();
			}
		}
		return login;
	}

	public Login toLogin(Cursor cursor) {
		Login login = new Login();
		if (cursor.moveToFirst()) {
			login.setLoginId(cursor.getString(1));
			login.setPassword(cursor.getString(2));
			login.setUserUUID(cursor.getString(3));
		}
		return login;
	}

	public void updateLoginDetails(String userId, String userUUID) {
		SQLiteStatement updateStatement = null;
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			String updateLoginStatement = "update " + LOGIN_TABLE
					+ " SET user_uuid =" + userUUID + " where user_id = '"
					+ userId + "'";
			updateStatement = db.compileStatement(updateLoginStatement);
			updateStatement.executeInsert();
		} finally {
			if (updateStatement != null) {
				updateStatement.close();
			}
		}
	}

}
