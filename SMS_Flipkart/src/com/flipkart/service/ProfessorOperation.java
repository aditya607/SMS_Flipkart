package com.flipkart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.dao.ProfessorDaoImpl;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.exception.InvalidCredential;
import com.flipkart.model.Course;
import com.flipkart.utils.DateTimeUtil;

public class ProfessorOperation implements ProfessorInterface,defaultInterface{
	
// creating objects of dao classes and logger,scanner....................................................
	private static Logger logger =Logger.getLogger(StudentOperation.class);
	Scanner sc=new Scanner(System.in);
	StudentDaoImpl studentdaoImpl=new StudentDaoImpl();
	AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
	static String role="professor";
	ProfessorDaoImpl professorDaoImpl=new ProfessorDaoImpl();
	
	HashMap<Integer,String> allCourses=new HashMap<Integer,String>(); // for storing all courses

// this method gives option to professor to all the opeartion he can do..................................................................
	public void professorJob(){
		professorDaoImpl.recordAllCourse();
		logger.info("select choices");logger.info("1.view all courses");logger.info("2.select courses");logger.info("3.view selected courses");
		logger.info("4.view student in course");logger.info("5.upload grades");logger.info("6.logout");
		int choice=sc.nextInt();
		if(choice==1){
			viewCourse();
		}else if(choice==2){
			selectCourse();
		}else if(choice==3){
			viewSelectedCourse();
		}else if(choice==4){
			studentInCourse();
		}else if(choice==5){
			uploadGrade();
		}else if(choice==6){
			logout();
		}else{
			logger.info("wrong selection");
			professorJob();
		}
	}
// this method ahows all yhe courses taught in institute.............................................
	public void viewCourse(){
		List<Course> courses=new ArrayList<Course>();
		courses=adminDaoImpl.viewCourse();
		for(Course s: courses){
			logger.info("COURSE CODE : "+s.getCoursecode()+" COURSE NAME : "+s.getCourseName()+" CREDIT : "+s.getCredit()+" PROFESSOR : "+s.getProfessor());
		}
		professorJob();
	}
// this method lets professor select the courses he want to teach in institute..................................
	public void selectCourse(){
		logger.info("enter the course you going to teach");
		String course=sc.next();
		professorDaoImpl.selectProfCourse(course);
		professorJob();
	}
// professor can view which courses he has selected to teach in institute..................................
	public void viewSelectedCourse(){
		List<Course> courses=new ArrayList<Course>();
		courses=professorDaoImpl.viewSelectedCourse();
		for(Course s: courses){
			logger.info("COURSE CODE : "+s.getCoursecode()+" COURSE NAME : "+s.getCourseName()+" CREDIT : "+s.getCredit()+" PROFESSOR : "+s.getProfessor());
		}
		professorJob();
	}
// viewing all the student who have selected the course..............................................................	
	public void studentInCourse(){
		logger.info("enter the course whose detail you want");
		String course=sc.next();
		List<String> users=new ArrayList<String>();
		users=professorDaoImpl.studentByCourse(course);
		logger.info("all student in course "+course);
		for(String s: users){
			logger.info("USERNAME : "+s);
		}
		professorJob();
	}
// if teacher wants to upload the grade of student......................................................
	public void uploadGrade(){
		logger.info("enter the course whose grade you want to upload");
		String course=sc.next();
		professorDaoImpl.uploadGrade(course);
		professorJob();
	}
// methos lets professor logout of the site.................................................
	public void logout(){
		logout1(role);
	}
}
