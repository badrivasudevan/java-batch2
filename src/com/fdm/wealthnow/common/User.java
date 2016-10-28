package com.fdm.wealthnow.common;

public class User {
	long userId;
	String userName;
	String fullName;
	
	public User(long userId, String userName, String fullName) {
		this.userId = userId;
		this.userName = userName;
		this.fullName = fullName;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userId:" + userId + " userName:"+ userName +" fullName:"+fullName;
	}
}
