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
import com.flipkart.model.Course;
import com.flipkart.utils.DateTimeUtil;

public class ProfessorOperation {
	private static Logger logger =Logger.getLogger(StudentOperation.class);
	Scanner sc=new Scanner(System.in);
	
	StudentDaoImpl studentdaoImpl=new StudentDaoImpl();
	AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
	ProfessorDaoImpl professorDaoImpl=new ProfessorDaoImpl();
	HashMap<Integer,String> allCourses=new HashMap<Integer,String>();
	
	
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
	public void viewCourse(){
		List<Course> courses=new ArrayList<Course>();
		courses=adminDaoImpl.viewCourse();
		for(Course s: courses){
			logger.info("COURSE CODE : "+s.getCoursecode()+" COURSE NAME : "+s.getCourseName()+" CREDIT : "+s.getCredit()+" PROFESSOR : "+s.getProfessor());
		}
		professorJob();
	}
	public void selectCourse(){
		logger.info("enter the course you going to teach");
		String course=sc.next();
		professorDaoImpl.selectProfCourse(course);
		professorJob();
	}
	public void viewSelectedCourse(){
		List<Course> courses=new ArrayList<Course>();
		courses=professorDaoImpl.viewSelectedCourse();
		for(Course s: courses){
			logger.info("COURSE CODE : "+s.getCoursecode()+" COURSE NAME : "+s.getCourseName()+" CREDIT : "+s.getCredit()+" PROFESSOR : "+s.getProfessor());
		}
		professorJob();
	}
	
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
	public void uploadGrade(){
		
	}
	public void logout(){
		logger.info("professor logging out at "+ DateTimeUtil.TimeDateDay());
		logger.info("WELCOME");
		SMSOperation smsopeartion=new SMSOperation();
		logger.info("enter username");
		String username1=sc.next();
		logger.info("enter password");
		String loginPassword1=sc.next();
		smsopeartion.CheckUser(username1,loginPassword1);
	}
}
