package com.flipkart.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.dao.ProfessorDaoImpl;
import com.flipkart.dao.StudentDaoImpl;

public class ProfessorOperation {
	private static Logger logger =Logger.getLogger(StudentOperation.class);
	Scanner sc=new Scanner(System.in);
	
	StudentDaoImpl studentdaoImpl=new StudentDaoImpl();
	ProfessorDaoImpl professorDaoImpl=new ProfessorDaoImpl();
	HashMap<Integer,String> allCourses=new HashMap<Integer,String>();
	public void getCourseDetails(){
		allCourses=studentdaoImpl.getCourseDetails();
		logger.info("showing all courses");
		/*for(Map.Entry m:allCourses.entrySet()){  
			   logger.info(m.getKey()+" "+m.getValue());  
			  }*/
		allCourses.forEach((key,value)->System.out.println(key+"  "+value));
		logger.info("select\n1.for course selection\n2.upload graden\n3.for view course details with student");
		int a=sc.nextInt();
		if(a==1){
			selectCourse();
		}
		
	}
	public void selectCourse(){
		logger.info("enter the courseId you going to teach");
		int courseId=sc.nextInt();
		professorDaoImpl.selectProfCourse(courseId);
	}
	
}
