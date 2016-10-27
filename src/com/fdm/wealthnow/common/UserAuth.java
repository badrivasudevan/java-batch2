package com.fdm.wealthnow.common;

public class UserAuth {
	boolean authenticationSuccessfull = false;
	User user;
	String errorMsg;
	
	public UserAuth(User user) {
		this.user = user;
		this.authenticationSuccessfull = true;
	}
	
	public UserAuth(String errorMsg) {
		this.authenticationSuccessfull = false;
		this.errorMsg = errorMsg;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setAuthenticationSuccess(boolean authenticationSuccess) {
		this.authenticationSuccessfull = authenticationSuccessfull;
	}

	public boolean isAuthenticationSuccessfull() {
		return authenticationSuccessfull;
	}

}
