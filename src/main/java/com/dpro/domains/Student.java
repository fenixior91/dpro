package com.dpro.domains;

import java.util.List;

public class Student extends User {
	private String album;

	private Group group;
	private List<FieldOfStudy> fieldOfStudies;

	public String getAlbum() {
		return album;
	}

	public Group getGroup() {
		return group;
	}

	public List<FieldOfStudy> getFieldOfStudies() {
		return fieldOfStudies;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setFieldOfStudies(List<FieldOfStudy> fieldOfStudies) {
		this.fieldOfStudies = fieldOfStudies;
	}

	@Override
	public String toString() {
		return String.format("[%s, album: %s]", super.toString(), getAlbum());
	}
}
