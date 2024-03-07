package Users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private String userID;
	private String userName;
	private String userPassword;
	private int userAge;
	private String userEmail;
	

	

	public User(String userID, String userName, String userPassword, int userAge, String userEmail) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userAge = userAge;
		this.userEmail = userEmail;
	}

	public void print() {
		System.out.printf("%-6s%-12s%-12s%-12s%-10s\n"
				, userID
				, userName
				, userPassword
				, userAge
				, userEmail);
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void printDetail() {
		System.out.println("아이디 : " + userID);
		System.out.println("이름 : " + userName);
		System.out.println("비밀번호  : " + userPassword);
		System.out.println("나이  : " + userAge);
		System.out.println("이메일  : " + userEmail);
	}

	
	

}
