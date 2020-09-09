package com.epam.customcollection.test;

import java.util.Comparator;

public class Student implements Comparator<Object> {

	int roll;
	String name;
	
	public Student(int roll, String name) {
		super();
		this.roll = roll;
		this.name = name;
	}
	
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [roll=" + roll + ", name=" + name + "]";
	}

	public int compare(Object o1, Object o2) {
		return ((Student)o1).getRoll()-((Student)o2).getRoll();
	}
	
	
	
}
