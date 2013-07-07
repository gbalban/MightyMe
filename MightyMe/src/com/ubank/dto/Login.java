/**
 * 
 */
package com.ubank.dto;

import java.io.Serializable;

/**
 * @author gayasuddin
 * 
 */
public class Login implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int localId;
	private String loginId;
	private String password;
	private String userUUID;

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

}
