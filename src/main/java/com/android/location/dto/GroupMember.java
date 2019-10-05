package com.android.location.dto;

public class GroupMember {
	
	private String userName;
	
	private String token;
	
	private String headPhotoAddr;
	
	private boolean isteacher;
	
	private boolean isSuccessfulToSignin;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getHeadPhotoAddr() {
		return headPhotoAddr;
	}

	public void setHeadPhotoAddr(String headPhotoAddr) {
		this.headPhotoAddr = headPhotoAddr;
	}

	public boolean isIsteacher() {
		return isteacher;
	}

	public void setIsteacher(boolean isteacher) {
		this.isteacher = isteacher;
	}

	public boolean isSuccessfulToSignin() {
		return isSuccessfulToSignin;
	}

	public void setSuccessfulToSignin(boolean isSuccessfulToSignin) {
		this.isSuccessfulToSignin = isSuccessfulToSignin;
	}
	

}
