package com.mindtree.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mindtree.school.dbUtil.DBUtil;
import com.mindtree.school.entity.Classroom;
import com.mindtree.school.entity.Student;
import com.mindtree.school.exception.daoException.DatabaseFailedException;
import com.mindtree.school.exception.daoException.NoDataFoundException;
import com.mindtree.school.exception.daoException.StudentDaoException;

public class StudentDaoImpl implements StudentDao {
	private DBUtil dbUtil = new DBUtil();

	public boolean insertClassroom(Classroom c) throws StudentDaoException {
		boolean isInserted = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dbUtil.getConnection();
			String sql = "insert into classroom values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, c.getClassId());
			ps.setString(2, c.getName());
			ps.setInt(3, c.getnStudents());
			int rs = ps.executeUpdate();
			if (rs == 0)
				isInserted = false;
			else
				isInserted = true;

		} catch (DatabaseFailedException e) {
			// TODO Auto-generated catch block
			throw new StudentDaoException("Connection error");
		} catch (SQLException e) {
			throw new StudentDaoException("SQL error", e);
		} finally {
			dbUtil.closeConnection(con);
			dbUtil.closeConnection(ps);
		}

		return isInserted;
	}

	public boolean insertStudent(Student s) throws StudentDaoException {
		boolean isInserted = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			int classId = s.getClassroom().getClassId();
			String sql1 = "select classId from classroom where classId=?";
			ps = con.prepareStatement(sql1);
			ps.setInt(1, classId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String sql2 = "insert into student values(?,?,?,?)";
				ps = con.prepareStatement(sql2);
				ps.setInt(1, s.getId());
				ps.setString(2, s.getName());
				ps.setInt(3, s.getAge());
				ps.setInt(4, s.getClassroom().getClassId());
				int rs1 = ps.executeUpdate();
				if (rs1 == 0)
					isInserted = false;
				else
					isInserted = true;
				updateClassroom(classId);
			}
		} catch (DatabaseFailedException e) {
			// TODO Auto-generated catch block
			throw new StudentDaoException("Connection error");
		} catch (SQLException e) {
			throw new StudentDaoException("SQL error", e);
		} finally {
			dbUtil.closeConnection(con);
			dbUtil.closeConnection(ps);
			dbUtil.closeConnection(rs);
		}
		return isInserted;
	}

	private void updateClassroom(int classId) throws StudentDaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			String sql1 = "select nStudent from classroom where classId=?";
			ps = con.prepareStatement(sql1);
			ps.setInt(1, classId);
			rs = ps.executeQuery();
			rs.next();
			int count = (rs.getInt(1) + 1);
			dbUtil.closeConnection(ps);
			String sql2 = "Update classroom set nStudent=? where classId=?";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, count);
			ps.setInt(2, classId);
			ps.executeUpdate();
		} catch (DatabaseFailedException e) {
			// TODO Auto-generated catch block
			throw new StudentDaoException("Connection error");
		} catch (SQLException e) {
			throw new StudentDaoException("SQL error", e);
		} finally {
			dbUtil.closeConnection(con);
			dbUtil.closeConnection(ps);
			dbUtil.closeConnection(rs);
		}
	}

	public List<Student> displayStudentById(int id) throws StudentDaoException {
		List<Student> stu = new ArrayList<Student>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from student inner join classroom on student.classId=classroom.classId where stuId=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Classroom classroom = new Classroom(rs.getInt("classId"), rs.getString("className"),
						rs.getInt("nStudent"));
				Student student = new Student(rs.getInt("stuId"), rs.getInt("age"), rs.getString("stuName"), classroom);
				stu.add(student);
			}
			try {
				if (stu.isEmpty()) {
					throw new NoDataFoundException(" Student ID is not present");
				}
			} catch (NoDataFoundException e) {
				throw new StudentDaoException("No Data Found", e);
			}
		} catch (DatabaseFailedException e) {
			// TODO Auto-generated catch block
			throw new StudentDaoException("Connection error");
		} catch (SQLException e) {
			throw new StudentDaoException("SQL error", e);
		} finally {
			dbUtil.closeConnection(con);
			dbUtil.closeConnection(ps);
			dbUtil.closeConnection(rs);
		}
		return stu;
	}

	public boolean isUpdatedById(int id, int age) throws StudentDaoException {
		boolean isUpdated = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dbUtil.getConnection();
			String sql2 = "Update student set age=? where stuId=?";
			ps = con.prepareStatement(sql2);
			ps.setInt(1, age);
			ps.setInt(2, id);
			int rs = ps.executeUpdate();
			if (rs == 1) {
				isUpdated = true;
			} else {
				throw new NoDataFoundException(" Student ID is not present");
			}
		} catch (NoDataFoundException e) {
			throw new StudentDaoException("No Data Found", e);
		} catch (DatabaseFailedException e) {
			// TODO Auto-generated catch block
			throw new StudentDaoException("Connection error");
		} catch (SQLException e) {
			throw new StudentDaoException("SQL error", e);
		} finally {
			dbUtil.closeConnection(con);
			dbUtil.closeConnection(ps);
		}
		return isUpdated;
	}
}
