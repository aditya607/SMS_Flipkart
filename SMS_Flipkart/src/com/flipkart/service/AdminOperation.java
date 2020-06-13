package com.flipkart.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;

public class AdminOperation {
	private static Logger logger =Logger.getLogger(SMSClient.class);
	Scanner sc=new Scanner(System.in);
	
	AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
	
	public void adminJob(){
		logger.info("select choice");logger.info("1.student opearation");logger.info("2.professor operation");
		logger.info("3.addNewcourse");logger.info("4.dropOldcourse");logger.info("5.report card generation");
		int choice =sc.nextInt();
		if(choice==1){
			studentOperation();}
		else if(choice==2){
			professorOperation();}
		else if(choice==3){
			addNewCourse();}
	}
// performing student operation..............................................................................................	
	public void studentOperation(){
		logger.info("select choice");logger.info("1.create Student");logger.info("2.delete Student");
		logger.info("3.update Student");
		int choice=sc.nextInt();
		if(choice==1){
			userCreateOp(1);
		}
		else if(choice==2){
			userDeleteOP(1);
		}
	}
	public void userCreateOp(int role){
		logger.info(" userID ,username and password");
		User user=new User();
		user.setUserId(sc.nextInt());
		user.setUserName(sc.next());
		user.setUserPassword(sc.next());
		user.setUserRole(role);
		adminDaoImpl.createUser(user);
		if(role==1)
		createStudent(user);
		if(role==2){
			createProfessor(user);
		}
	}
	public void userDeleteOP(int role){
		logger.info("enter the username of student to delete");
		String username=sc.next();
		adminDaoImpl.deleteUser(username,role);
	}
	
	public void createStudent(User user){
		logger.info("enter student details");
		sc.nextLine();
		Student student=new Student();
		student.setUsername(user.getUserName());
		logger.info("student name ");
		student.setStudentName(sc.nextLine());
		logger.info("student address");
		student.setStudentAddress(sc.nextLine());
		logger.info("student enrollment number");
		student.setStudentEnrollmentNO(sc.nextInt());
		logger.info("student email");
		student.setStudentEmail(sc.next());
		logger.info("student dob : date/month/year");
		student.setStudentDOB(sc.next());
		logger.info("student mobile number");
		student.setStudentMobileNO(sc.next());
		logger.info("student gender : 'male'/'female'" );
		student.setStudentGender(sc.next());
		adminDaoImpl.createStudent(student);
	}
	
	
	
	public void professorOperation(){
		logger.info("select choice");logger.info("1.create professor");logger.info("2.delete professor");
		logger.info("3.update professor"); //professor
		int choice=sc.nextInt();
		if(choice==1){
			userCreateOp(2);
		}else if(choice==2){
			userDeleteOP(2);
		}
	}
	
	public void createProfessor(User user){
		logger.info("enter professor details");
		sc.nextLine();
		Professor professor=new Professor();
		professor.setProfessorusername(user.getUserName());
		logger.info("professor name ");
		professor.setProfessorName(sc.nextLine());
		logger.info("professor address");
		professor.setProfessortAddress(sc.nextLine());
		logger.info("professor email");
		professor.setProfessorEmail(sc.next());
		logger.info("professor room number");
		professor.setProfessorRoomNo(sc.next());
		logger.info("professor mobile number");
		professor.setProfessortMobileNO(sc.next());
		logger.info("professor gender : 'male'/'female'" );
		professor.setProfessorGender(sc.next());
		adminDaoImpl.createProfessor(professor);
	}
	public void addNewCourse(){
		logger.info("give course details - CourseId , CourseName and catalogId");
		Course course=new Course();
		course.setCourseId(sc.nextInt());
		course.setCourseName(sc.next());
		course.setCatalogId(sc.nextInt());
		adminDaoImpl.addNewCourse(course);
	}
}
