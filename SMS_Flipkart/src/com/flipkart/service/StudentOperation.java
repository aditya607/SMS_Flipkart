package com.flipkart.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.dao.SMSDaoImpl;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.exception.InvalidCredential;
import com.flipkart.model.Course;
import com.flipkart.utils.DateTimeUtil;

public class StudentOperation implements StudentInterface,defaultInterface{
//creating all the objects of dao classes,scanner and logger.......................................
	private static Logger logger =Logger.getLogger(StudentOperation.class);
	Scanner sc=new Scanner(System.in);
	AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
	StudentDaoImpl studentDaoImpl=new StudentDaoImpl();
	static String role1="Student";
	HashMap<Integer,String> allCourses=new HashMap<Integer,String>(); // for recording all the courses
	
// method provide student with all the operation he can do	
	public void studentJob(){
		studentDaoImpl.recordAllCourse();
		String registrationStatus=studentDaoImpl.checkRegistration();
		if(registrationStatus.equals("pending")){ // means user have not done either registration or payment...
			logger.info("select choices");logger.info("1.view all courses");logger.info("2.add courses");logger.info("3.drop courses");
			logger.info("4.view selected course");logger.info("5.submit final registration");logger.info("6.view grades");logger.info("7.logout");
			int choice=sc.nextInt();
			if(choice==1){
				viewCourse();
			}else if(choice==2){
				studentAddCourse();
			}else if(choice==3){
				studentDropCourse();
			}else if(choice==4){
				studentCourse();
			}else if(choice==5){
				finalRegistration();
			}else if(choice==6){
				viewGrades();
			}else if(choice==7){
				logout();
			}
		}
		if(registrationStatus.equals("partial")){ // registration done but payment not done......
			logger.info("select choices");logger.info("1.view selected course");logger.info("2. view grades");logger.info("3.make payment");
			logger.info("4.logout");
			int choice=sc.nextInt();
			if(choice==1){
				studentCourse();
			}else if(choice==2){
				viewGrades();
			}else if(choice==3){
				PaymentOpeartion paymentOpeartion=new PaymentOpeartion();
				paymentOpeartion.makePayment();
			}else if(choice==4){
				logout();
			}
		}
		if(registrationStatus.equals("complete")){  // payment and registration both done....
			logger.info("select choices");logger.info("1.view selected course");logger.info("2. view grades");logger.info("4.logout");
			int choice=sc.nextInt();
			if(choice==1){
				studentCourse();
			}else if(choice==2){
				viewGrades();
			}else if(choice==3){
				logout();
			}else if(choice==4){
				logout();
			}
		}
	}
// student can see all the courses in the institute ...................................................	
	public void viewCourse(){
		List<Course> courses=new ArrayList<Course>();
		courses=adminDaoImpl.viewCourse();
		for(Course s: courses){
			logger.info("COURSE CODE : "+s.getCoursecode()+" COURSE NAME : "+s.getCourseName()+" CREDIT : "+s.getCredit()+" PROFESSOR : "+s.getProfessor());
		}
		studentJob();
	}
// student can add the courses he want to study for furthur registration.....................................	
	public void studentAddCourse(){
		logger.info("enter the course name");
		String course=sc.next();
		studentDaoImpl.studentAddCourse(course);
		studentJob();
	}
// student can drop the course in case he wish to add another course instead of selected course...................
	public void studentDropCourse(){
		logger.info("enter the course you want to drop");
		String course=sc.next();
		studentDaoImpl.studentDropCourse(course);
		studentJob();
	}
// student can view which are the courses which he has selected ........................................
	public void studentCourse(){
		HashMap<String,String> courses=new HashMap<String,String>();
		logger.info("student selected courses");
		courses=studentDaoImpl.studentCourse();
		for(Map.Entry m:courses.entrySet()){  
			   logger.info(m.getKey()+" on "+m.getValue());  
		}
		studentJob();
	}
// after seecting all 6 courses he can do the final registration.......................................	
	public void finalRegistration(){
		logger.info("once you register , you will not be able to add/drop courses");
		logger.info("choose");logger.info("1. for do registration");logger.info("2. if you want to add/drop courses");
		int choice=sc.nextInt();
		if(choice==2)
			studentJob();
		else if(choice==1){
			CourseRegister courseRegister=new CourseRegister();
			courseRegister.finalRegistration();
		}
	}
	
// if student wid=sh ti logout from the site.......................................................
	public void logout(){
		logout1(role1);
	}
// will show the grade to student of his courses........................................................
	public void viewGrades(){
		HashMap<String,String> grades=new HashMap<String,String>();
		grades=studentDaoImpl.viewGrades();
		for(Map.Entry m:grades.entrySet()){  
			   logger.info("COURSE NAME : "+m.getKey()+" GRADE : "+m.getValue());  
		}
		studentJob();
	}
}
