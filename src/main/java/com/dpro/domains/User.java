package com.dpro.domains;

import java.util.Date;

public class User {
	private long id;
	private String username;
	private String password;
	private boolean enabled;
	private String email;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return String.format(
				"ID: %d, Username: %s, Password: %s, Enabled: %s, Email: %s, First Name: %s, Last Name: %s, Date Of Birth: %s, Addresses: %s",
				getId(), getUsername(), isEnabled(), getEmail(), getFirstName(), getLastName(), getDateOfBirth());
	}
}
