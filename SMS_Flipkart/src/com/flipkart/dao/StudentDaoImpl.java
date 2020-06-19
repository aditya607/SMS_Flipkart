package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.model.Course;
import com.flipkart.client.SMSClient;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class StudentDaoImpl implements StudentDao{
// creating statement variable and logger object...............................................
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(SMSClient.class);

	List<String> allCourses=new ArrayList<String>(); // for recording all tthe courses..............
	
// this method for recording all course , so that we don't need to call it again and again ...........
	@Override
	public void recordAllCourse() {
		Connection conn=DBUtil.getConnection();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.all_courses);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				allCourses.add(rs.getString("courseName"));
			}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		}catch(Exception e){
			   logger.error("exception"+e.getMessage());
		}
	}
	
// checking if course is taught in institute or not
	public int isCoursepresent(String course){
		int a=0;
		for(String s:allCourses){
			if(s.equals(course))
				a=1;
		}
		return a;
	}

// if student add any course for study........................................................
	@Override
	public void studentAddCourse(String course) {
		Connection conn=DBUtil.getConnection();
		List<String> courses=new ArrayList<String>();
		try{   	// for checking courses and its number selected by student
			stmt = conn.prepareStatement(SQLConstantQuaries.student_selected_course);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			int courseCount=0;
			while(rs.next()){
				courseCount++;
				courses.add(rs.getString("courseName"));
			}
			int checkEqual=0;
			if(courseCount==6){ //if he has already selected 6 courses
				logger.info("sorry you have already selected 6 courses. drop any course first");
			}else if(courseCount<6){
				for(String s:courses){  // checking if there is any course repetition
					if(s.equals(course))
						checkEqual=1;
				}
				if(checkEqual==1){ 
					logger.info("course is already selected. select other course");
				}else if(isCoursepresent(course)==1){ //if all fine,then course added
					stmt = conn.prepareStatement(SQLConstantQuaries.student_add_course);
					 stmt.setString(1, SMSDaoImpl.userName); 
					 stmt.setString(2,course);
					 stmt.setString(3,"pending");
					 stmt.setString(4,"not_updated");
					 stmt.setString(5,DateTimeUtil.viewDate());
					 int rows = stmt.executeUpdate();
					 if(rows==1){
						 logger.info(SMSDaoImpl.userName+ " has added "+course +"  on "+DateTimeUtil.TimeDateDay());
					 }
				}else{ // means course is not present in the database
					logger.info("sorry this course is not taught in college");
				}
			}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		}catch(Exception e){
			   logger.error("exception"+e.getMessage());
		}
	}

// if student wish to drop any course.................................................................
	@Override
	public void studentDropCourse(String course) {
		Connection conn=DBUtil.getConnection();
		List<String> courses=new ArrayList<String>();
		try{        // checking all the courses selected by the student...
			stmt = conn.prepareStatement(SQLConstantQuaries.student_selected_course);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				courses.add(rs.getString("courseName"));
			}
		int check=0;
		for(String s:courses){    // checking if dropping course is present in the selected course
			if(s.equals(course))
				check=1;
		}
		if(check==0)
			logger.info("this course is not selected , so you can't drop it");
		else{ // dropping course present in selected course
			stmt = conn.prepareStatement(SQLConstantQuaries.student_drop_course);
			 stmt.setString(1, SMSDaoImpl.userName); 
			 stmt.setString(2, course);
			 stmt.executeUpdate();
			 logger.info(course +" is dropped on "+DateTimeUtil.TimeDateDay());
		}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		}catch(Exception e){
			   logger.error("exception"+e.getMessage());
		}
		
	}
// return all the courses selected by the student......................................................
	@Override
	public HashMap<String,String> studentCourse() {
		Connection conn=DBUtil.getConnection();
		HashMap<String,String> courses=new HashMap<String,String>();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.student_selected_course);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				courses.put(rs.getString("courseName"), rs.getString("timeOfAddition"));
			}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		}catch(Exception e){
			   logger.error("exception"+e.getMessage());
		}	
		return courses;
	}

// for getting the registration status of student..............................................
	@Override
	public String checkRegistration() {
		Connection conn=DBUtil.getConnection();
		String ans="pending";
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.student_selected_course);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ans=rs.getString("registration");
			}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		}catch(Exception e){
			   logger.error("exception"+e.getMessage());
		}
		return ans;
	}

// for viewing the grades of student who have logged in the system.....................................
	@Override
	public HashMap<String, String> viewGrades() {
		Connection conn=DBUtil.getConnection();
		HashMap<String, String> grades=new HashMap<String, String>();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.student_selected_course);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				grades.put(rs.getString("courseName"), rs.getString("grade"));
			}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		}catch(Exception e){
			   logger.error("exception"+e.getMessage());
		}
		return grades;
	}
	


}
