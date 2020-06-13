package com.flipkart.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.dao.CourseDaoImpl;
import com.flipkart.dao.SMSDaoImpl;

public class CourseOperation {
	private static Logger logger =Logger.getLogger(StudentOperation.class);
	Scanner sc=new Scanner(System.in);
	
	CourseDaoImpl courseDaoImpl=new CourseDaoImpl();
	public int checkDuplicate(List<Integer> ls){
		Collections.sort(ls);
		int ans=1;
		for(int i=0;i<ls.size()-1;i++){
			if(ls.get(i).equals(ls.get(i+1)) ){
				ans=0;
			}
		}
		return ans;
	}
//for choosing the course out of all viewed course.....................................................................
	public void chooseCourse(HashMap<Integer,String> allCourses){
		logger.info("enter the courseId of all 4 courses");
		List<Integer> selectedCourse=new ArrayList<Integer>();
		selectedCourse.add(SMSDaoImpl.userid);
		for(int i=0;i<4;i++){
			selectedCourse.add(sc.nextInt());
		}
		logger.info("enter the courseId of 2 alternate course");
		selectedCourse.add(sc.nextInt());
		selectedCourse.add(sc.nextInt());
		int a=checkDuplicate(selectedCourse);
		if(a==0){
			logger.info("duplicate course selected");
			StudentOperation studentOperation=new StudentOperation();
			studentOperation.getCourseDetails();
		}
		else{
			courseDaoImpl.selectCourse(selectedCourse);
			logger.info("choose\n 1.for registe\n2.for adding courses\n3.for dropping course");
		}
		
	}
}
