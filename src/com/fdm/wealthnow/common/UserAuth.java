package src.com.fdm.wealthnow.common;

public class UserAuth {
	boolean authenticationSuccess = false;
	User user;
	String errorMsg;
	
	public UserAuth(User user) {
		this.user = user;
		this.authenticationSuccess = true;
	}
	
	public UserAuth(String errorMsg) {
		this.authenticationSuccess = false;
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
		this.authenticationSuccess = authenticationSuccess;
	}

	public boolean isAuthenticationSuccess() {
		return authenticationSuccess;
	}

}
