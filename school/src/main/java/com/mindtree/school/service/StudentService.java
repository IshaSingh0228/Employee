package com.mindtree.school.service;

import java.util.List;

import com.mindtree.school.entity.Classroom;
import com.mindtree.school.entity.Student;
import com.mindtree.school.exception.serviceException.StudentServiceException;

public interface StudentService {

	boolean insertClassroom(Classroom c) throws StudentServiceException;
	boolean insertStudent(Student s) throws StudentServiceException;
	List<Student> displayStudentById(int id) throws StudentServiceException;
	boolean isUpdatedById(int id,int age) throws StudentServiceException;
}
