package com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserBean {

	private int id;
	private String user_name;
	private String gender;
	private String userId;
	private String status;
	
	@Override
	public String toString() {
		return "UserBean [ id=" + id + ", name=" + user_name + ",id=" + userId + ",  gender=" + gender + "	+ status=" + status + "]\n";
	}

	
	public UserBean(String user_name,String userId,String gender) {
		super();
		this.user_name = user_name;
		this.gender = gender;
		this.userId = userId;
	}
	
}
