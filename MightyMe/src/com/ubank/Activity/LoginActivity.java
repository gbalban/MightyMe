package com.ubank.Activity;

import java.util.UUID;

import com.Ubank.dao.LoginDAO;
import com.ubank.dto.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitylogin);
	}

	public void loginAccount(View view) {
		EditText textLogin = (EditText) findViewById(R.id.logintext);
		EditText textPassword = (EditText) findViewById(R.id.txtpassword);
		String loginName = textLogin.getText().toString();
		String password = textPassword.getText().toString();
		String uuid = generateUUID();
		LoginDAO loginDAO = new LoginDAO(LoginActivity.this);
		Login login = null;
		login = loginDAO.lookUpLoginDetail();
		if (null != login && loginName.equals(login.getLoginId())
				&& password.equals(login.getPassword())) {
			Intent intent = new Intent(LoginActivity.this,
					DashBoardActivity.class);
			startActivity(intent);
		} else {
			login = new Login();
			login.setLoginId(loginName);
			login.setPassword(password);
			login.setUserUUID(uuid);
			loginDAO.insertLogin(login);

			openAlert(loginName, password);
		}
	}

	public String generateUUID() {
		String uuidNumber = UUID.randomUUID().toString();
		return uuidNumber;
	}

	public void openAlert(String loginName, String password) {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
				LoginActivity.this);
		alertBuilder.setTitle("Login Alert");
		alertBuilder
				.setMessage(
						"Your Login Name is " + loginName + " and password is "
								+ password + " Please click to confirm")
				.setCancelable(false)
				.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();

					}
				}).show();
	}
}
