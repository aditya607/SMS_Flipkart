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
import com.flipkart.model.Course;

public class StudentOperation {
	private static Logger logger =Logger.getLogger(StudentOperation.class);
	Scanner sc=new Scanner(System.in);
	
	AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
	StudentDaoImpl studentDaoImpl=new StudentDaoImpl();
	HashMap<Integer,String> allCourses=new HashMap<Integer,String>();
	
	
	public void studentJob(){
		studentDaoImpl.recordAllCourse();
		logger.info("select choices");logger.info("1.view all courses");logger.info("2.add courses");logger.info("3.drop courses");
		logger.info("4.view selected course");logger.info("5.submit final registration");logger.info("6.view grades");logger.info("7.logout");
		int choice=sc.nextInt();
		if(choice==1){
			viewCourse();
		}else if(choice==2){
			studentAddCourse();
		}else if(choice==3){
			studentDropCourse();
		}
	}
	
	public void viewCourse(){
		List<Course> courses=new ArrayList<Course>();
		courses=adminDaoImpl.viewCourse();
		for(Course s: courses){
			logger.info("COURSE CODE : "+s.getCoursecode()+" COURSE NAME : "+s.getCourseName()+" CREDIT : "+s.getCredit()+" PROFESSOR : "+s.getProfessor());
		}
		studentJob();
	}
	
	public void studentAddCourse(){
		logger.info("enter the course name");
		String course=sc.next();
		studentDaoImpl.studentAddCourse(course);
		studentJob();
	}

	public void studentDropCourse(){
		logger.info("enter the course you want to drop");
		String course=sc.next();
		studentDaoImpl.studentDropCourse(course);
		studentJob();
	}

}
