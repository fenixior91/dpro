package com.dpro.domains;

import java.util.Date;
import java.util.List;

public class User {
	private long id;
	private String pesel;
	private String password;
	private boolean enabled;
	private String email;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	private List<Address> addresses;

	public long getId() {
		return id;
	}

	public String getPesel() {
		return pesel;
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
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

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return String.format(
				"ID: %d, PESEL: %s, Password: %s, Enabled: %s, Email: %s, First Name: %s, Last Name: %s, Date Of Birth: %s, Addresses: %s",
				getId(), getPesel(), isEnabled(), getEmail(), getFirstName(), getLastName(), getDateOfBirth(),
				getAddresses());
	}
}
