package com.android.location.websocket.pojo;

public class UserInfo {
	
	private String username;
	private String token;
	private String headAddr;
	
	public UserInfo(String username, String token, String headAddr) {
		super();
		this.username = username;
		this.token = token;
		this.headAddr = headAddr;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return token;
	}

	public String getHeadAddr() {
		return headAddr;
	}
	
	

}
