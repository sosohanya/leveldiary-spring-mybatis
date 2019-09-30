package com.sosohanya.leveldiary.account;

public class Account {

	private long id;
	private String email;
	private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Account(long id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public Account() { }

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + "]";
	}	
}
