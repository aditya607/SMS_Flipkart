package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.CourseCatalog;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.utils.DateTimeUtil;

public class AdminOperation {
	private static Logger logger =Logger.getLogger(SMSClient.class);
	Scanner sc=new Scanner(System.in);
	
	AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
	
	public void adminJob(){
		logger.info("select choice");logger.info("1.student opearation");logger.info("2.professor operation");
		logger.info("3.addNewcourse");logger.info("4.dropOldcourse");logger.info("5.report card generation");
		logger.info("6.view all student");logger.info("7.view all professor");logger.info("8.view all course");logger.info("9.logout");
		int choice =sc.nextInt();
		if(choice==1){
			studentOperation();}
		else if(choice==2){
			professorOperation();
		}else if(choice==3){
			addNewCourse();
		}else if(choice==4){
			dropOldCourse();
		}else if(choice==6){
			viewStudents();
		}else if(choice==7){
			viewProfessor();
		}else if(choice==8){
			viewCourse();
		}else if(choice==9){
			logger.info("admin logging out at "+ DateTimeUtil.TimeDateDay());
			logger.info("WELCOME");
			SMSOperation smsopeartion=new SMSOperation();
			logger.info("enter username");
			String username1=sc.next();
			logger.info("enter password");
			String loginPassword1=sc.next();
			smsopeartion.CheckUser(username1,loginPassword1);
		}
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
		int duplicateUser;
		duplicateUser=adminDaoImpl.createUser(user);
		if(duplicateUser==1){
			userCreateOp(role);
		}
		else{
			if(role==1)
			createStudent(user);
			if(role==2){
				createProfessor(user);
			}
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
		adminJob();
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
		adminJob();
	}
	public void addNewCourse(){
		logger.info("give course details"); logger.info("CourseId , CourseName and catalogId");
		CourseCatalog course=new CourseCatalog();
		course.setCourseID(sc.next());
		course.setCourseName(sc.next());
		course.setCatalogId(sc.nextInt());
		int duplicateCourse = adminDaoImpl.addNewCourse(course);
		if(duplicateCourse==1){
			addNewCourse();
		}else{
			Course course1=new Course();
			course1.setCourseName(course.getCourseName());
			logger.info("enter course credit");
			course1.setCredit(sc.nextInt());
			logger.info("enter the course code");
			course1.setCoursecode(sc.next());
			logger.info("enter course info");
			sc.nextLine();
			course1.setCourseInfo(sc.nextLine());
			adminDaoImpl.addCourseDetail(course1);
		}
		adminJob();
	}
	public void dropOldCourse(){
		logger.info("enter the course name");
		String course=sc.next();
		adminDaoImpl.dropOldCourse(course);
		adminJob();
	}
	public void viewStudents(){

		List<Student> students=new ArrayList<Student>();
		students=adminDaoImpl.viewStudents();
		logger.info("student list");
		for(Student s: students){
			logger.info("NAME : "+s.getStudentName()+" USERNAME : "+s.getUsername()+" ENROLL NO. : "+s.getStudentEnrollmentNO()+" MOBILE NO. : "+s.getStudentMobileNO()+" EMAIL : "+s.getStudentEmail()+" ADDRESS : "+s.getStudentAddress());
		}
		adminJob();
	}
	public void viewProfessor(){
		List<Professor> professors=new ArrayList<Professor>();
		professors=adminDaoImpl.viewProfessor();
		logger.info("professor list");
		for(Professor s: professors){
			logger.info("NAME : "+s.getProfessorName()+" USERNAME : "+s.getProfessorusername()+" MOBILE NO. : "+s.getProfessortMobileNO()+" EMAIL : "+s.getProfessorEmail()+" ROOM NO. : "+s.getProfessorRoomNo());
		}
		adminJob();
	}
	public void viewCourse(){
		List<Course> courses=new ArrayList<Course>();
		courses=adminDaoImpl.viewCourse();
		logger.info("course list");
		for(Course s: courses){
			logger.info("COURSE CODE : "+s.getCoursecode()+" COURSE NAME : "+s.getCourseName()+" CREDIT : "+s.getCredit()+" PROFESSOR : "+s.getProfessor());
		}
		adminJob();
	}
}
