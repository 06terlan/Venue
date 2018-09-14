package bll.model;

public final class LoginUser {
	 private final String UserName;
	 private final String Password;
	public LoginUser(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}
	public String getUserName() {
		return UserName;
	}
	public String getPassword() {
		return Password;
	}
	 
}
