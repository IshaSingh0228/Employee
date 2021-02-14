package com.mindtree.school.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mindtree.school.entity.Classroom;
import com.mindtree.school.entity.Student;
import com.mindtree.school.exception.serviceException.StudentServiceException;
import com.mindtree.school.service.StudentService;
import com.mindtree.school.service.StudentServiceImpl;

public class App {
	private static Scanner sc = new Scanner(System.in);
	private static StudentService service = new StudentServiceImpl();

	public static void main(String[] args) {
		do {
		System.out.println("1.Enter Class Details\n" + "2.Enter Student \n" + "3.Display all details by Id\n"
				+ "4.Update age by giving Id\n" + "5.Exit");
		System.out.println("Enter your option\n");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			boolean isInserted = false;
			Classroom classroom = getClassroom();
			try {
				isInserted = service.insertClassroom(classroom);
			} catch (StudentServiceException e) {
				e.printStackTrace();
			}
			if (isInserted) {
				System.out.println("New Class got created!!");
			} else
				System.out.println("Somthing went wrong!!");
			break;
		case 2:
			boolean isInsert = false;
			Student student = getStudent();
			try {
				isInsert = service.insertStudent(student);
			} catch (StudentServiceException e) {
				e.printStackTrace();
			}
			if (isInsert) {
				System.out.println("New Studnet got created!!");
			} else
				System.out.println("Somthing went wrong!!");
			break;
		case 3:
			System.out.println("Enter id");
			int id = sc.nextInt();
			List<Student> stu = new ArrayList<Student>();
			try {
				stu = service.displayStudentById(id);
			} catch (StudentServiceException e) {
				System.out.println(e);;
			}
			display(stu);
			break;
		case 4:
			boolean isUpdated = false;
			System.out.println("Enter Id you wanna Update");
			int updateId = sc.nextInt();
			System.out.println("Enter age");
			int updateAge = sc.nextInt();
			try {
				isUpdated = service.isUpdatedById(updateId, updateAge);
			} catch (StudentServiceException e) {
				System.out.println(e);
			}if (isUpdated) {
				System.out.println(" Student got Updated!!");
			} else
				System.out.println("Somthing went wrong!!");
			break;
		case 5:
			sc.close();
			return;
		default:
			System.out.println("Invalid Option!");
			break;
		}
		}while(true);
	}

	private static void display(List<Student> stu) {
		// TODO Auto-generated method stub
		System.out.println("StuId\t"+"StuName\t"+"ClassId\t"+"ClassName");
for(Student s:stu) {
	System.out.println(s.getId()+"\t"+s.getName()+"\t"+s.getClassroom().getClassId()
			+"\t"+s.getClassroom().getName());
}
	}

	private static Student getStudent() {
		sc.nextLine();
		System.out.println("Enter Id for Student");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter name");
		String name = sc.nextLine();
		sc.nextLine();
		System.out.println("Enter Age");
		int age = sc.nextInt();
		System.out.println("Enter classroom Id");
		int classId = sc.nextInt();
		Classroom classroom = new Classroom(classId);
		Student student = new Student(id, age, name, classroom);
		return student;
	}

	private static Classroom getClassroom() {
		System.out.println("Enter class id");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter class name(A,B..)");
		String name=sc.nextLine();
		//String name=Character.toString(name1);
		Classroom classroom = new Classroom(id, name);
		return classroom;
	}
	
}
