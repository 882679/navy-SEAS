package com.example.navyseas.database.Model;

import org.bson.types.ObjectId;

import java.util.List;

public class Student {
	private ObjectId id;
	private Family family;
	private String name;
	private List<Activity> activities;

	public Student() {
	}

	public Student(ObjectId id, Family family, String name, List<Activity> activities) {
		this.id = id;
		this.family = family;
		this.name = name;
		this.activities = activities;
	}

	public ObjectId getId() {
		return id;
	}

	public Family getFamily() {
		return family;
	}

	public String getName() {
		return name;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", family=" + family +
				", name='" + name + '\'' +
				", activities=" + activities +
				'}';
	}
}
