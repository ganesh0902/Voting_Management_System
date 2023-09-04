package com.vot.entities;

public class List_of_vote {

	private long userId;
	private String username;
	private String email;
	private String phone;
	private String candidateName;
	public List_of_vote() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List_of_vote(long userId, String username, String email, String phone, String candidateName) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.candidateName = candidateName;
	}
	
	public List_of_vote( String username, String email, String phone, String candidateName) {
		super();
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.candidateName = candidateName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	@Override
	public String toString() {
		return "List_of_vote [userId=" + userId + ", username=" + username + ", email=" + email + ", phone=" + phone
				+ ", candidateName=" + candidateName + "]";
	}
		
}
