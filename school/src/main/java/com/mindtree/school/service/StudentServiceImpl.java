package com.mindtree.school.service;

import java.util.List;

import com.mindtree.school.dao.StudentDao;
import com.mindtree.school.dao.StudentDaoImpl;
import com.mindtree.school.entity.Classroom;
import com.mindtree.school.entity.Student;
import com.mindtree.school.exception.daoException.StudentDaoException;
import com.mindtree.school.exception.serviceException.StudentServiceException;

public class StudentServiceImpl implements StudentService {
private StudentDao studentDao= new StudentDaoImpl();
	public boolean insertClassroom(Classroom c) throws StudentServiceException {
		
		boolean isInserted;
		try {
			isInserted = studentDao.insertClassroom(c);
		} catch (StudentDaoException e) {
			throw new StudentServiceException(e);
		}
		return isInserted;
	}

	public boolean insertStudent(Student s) throws StudentServiceException {
		// TODO Auto-generated method stub
		boolean isInserted;
		try {
			isInserted = studentDao.insertStudent(s);
		} catch (StudentDaoException e) {
			throw new StudentServiceException("something went wrong in db", e);
		}
		return isInserted;
	}

	public List<Student> displayStudentById(int id) throws StudentServiceException {
		try {
			return studentDao.displayStudentById(id);
		}catch (StudentDaoException e) {
			throw new StudentServiceException(e);
		}
	}

	public boolean isUpdatedById(int id, int age) throws StudentServiceException {
		boolean isUpdated;
		try {
			isUpdated =studentDao.isUpdatedById(id,age);
		} catch (StudentDaoException e) {
			throw new StudentServiceException("something went wrong in db", e);
		}
		return isUpdated;
	}

}
