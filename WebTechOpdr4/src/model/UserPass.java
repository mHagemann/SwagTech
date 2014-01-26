package model;

/**
 * object om een Nickname/Password paar te maken
 * @author Hagemann
 *
 */
public class UserPass {
	
	private String nickname, password;
	
	public UserPass() {}
	
	public UserPass(String nickname, String password) {
		this.nickname = nickname;
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
