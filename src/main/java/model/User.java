package model;

import java.io.Serializable;

public class User implements Serializable{
	private String userId;
	private String password;
	private String name;
	private String email;
	private String birth;
	private String phoneNumber;


	public User() { }	
	
	public User(String userId, String password, String name, String email, String birth, String phone) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phoneNumber = phone;
		this.birth = birth;

	}

	public User(String userId, String name, String email, String birth, String phone) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.birth = birth;
		this.phoneNumber = phone;		
	}
	
	/*public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.email = updateUser.email;
        this.phone = updateUser.phone;
    }*/
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}
	//-------------------------------------------
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password +", birth="
				+ birth + ", phoneNumber=" + phoneNumber + "]";
	}


}
