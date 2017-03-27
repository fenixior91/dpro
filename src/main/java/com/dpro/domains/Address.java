package com.dpro.domains;

public class Address {
	private String city;
	private String address1;
	private String address2;
	private String zipCode;

	public String getCity() {
		return city;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
