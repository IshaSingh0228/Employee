package com.mindtree.school.dao;

import java.util.List;

import com.mindtree.school.entity.Classroom;
import com.mindtree.school.entity.Student;
import com.mindtree.school.exception.daoException.StudentDaoException;
public interface StudentDao {
	boolean insertClassroom(Classroom c) throws StudentDaoException;
	boolean insertStudent(Student s) throws StudentDaoException;
	List<Student> displayStudentById(int id) throws StudentDaoException;
	boolean isUpdatedById(int id,int age) throws StudentDaoException;
}
