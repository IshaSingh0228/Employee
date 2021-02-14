package com.mindtree.school.entity;

public class Student {
private int id;
private int age;
private String name;
private Classroom classroom;
public Student() {
	super();
}
public Student(int id, int age, String name, Classroom classroom) {
	super();
	this.id = id;
	this.age = age;
	this.name = name;
	this.classroom = classroom;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Classroom getClassroom() {
	return classroom;
}
public void setClassroom(Classroom classroom) {
	this.classroom = classroom;
}

}
