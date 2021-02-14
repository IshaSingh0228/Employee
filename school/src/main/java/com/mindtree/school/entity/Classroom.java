package com.mindtree.school.entity;

public class Classroom {
private int classId;
private String name;
private int nStudents=0;
public int getClassId() {
	return classId;
}

public Classroom(int classId) {
	super();
	this.classId = classId;
}

public Classroom(int classId, String name) {
	super();
	this.classId = classId;
	this.name = name;
}

public Classroom(int classId, String name, int nStudents) {
	super();
	this.classId = classId;
	this.name = name;
	this.nStudents = nStudents;
}

public void setClassId(int classId) {
	this.classId = classId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getnStudents() {
	return nStudents;
}
public void setnStudents(int nStudents) {
	this.nStudents = nStudents;
}

}
