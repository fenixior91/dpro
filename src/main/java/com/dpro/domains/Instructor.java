package com.dpro.domains;

public class Instructor extends User {
	private String scienceDegree;

	public String getScienceDegree() {
		return scienceDegree;
	}

	public void setScienceDegree(String scienceDegree) {
		this.scienceDegree = scienceDegree;
	}

	@Override
	public String toString() {
		return String.format("%s, Science Degree: %s", super.toString(), getScienceDegree());
	}
}
