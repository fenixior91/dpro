package com.dpro.domains;

public class FieldOfStudy {
	private String fieldOfStudy;

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	@Override
	public String toString() {
		return String.format("Field Of Study: %s", getFieldOfStudy());
	}
}
