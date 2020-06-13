package com.flipkart.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.dao.SMSDaoImpl;
import com.flipkart.dao.StudentDaoImpl;

public class StudentOperation {
	private static Logger logger =Logger.getLogger(StudentOperation.class);
	Scanner sc=new Scanner(System.in);
	
	StudentDaoImpl studentdaoImpl=new StudentDaoImpl();
	HashMap<Integer,String> allCourses=new HashMap<Integer,String>();
	
//  getting all the details of course eg courseId and CourseName.............................................................  
		public void getCourseDetails(){
			allCourses=studentdaoImpl.getCourseDetails();
			logger.info("showing all courses");
			/*for(Map.Entry m:allCourses.entrySet()){  
				   logger.info(m.getKey()+" "+m.getValue());  
				  }*/
			allCourses.forEach((key,value)->System.out.println(key+"  "+value));
			logger.info("now choosing the course");
			CourseOperation courseOperation=new CourseOperation();
			courseOperation.chooseCourse(allCourses);
		}


}
