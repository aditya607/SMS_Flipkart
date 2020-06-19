package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.model.Course;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class ProfessorDaoImpl implements ProfessorDao{
// creating statement variable ,logger and scanner object......................
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(SMSClient.class);
	Scanner sc=new Scanner(System.in);
	
	List<String> allCourses=new ArrayList<String>(); // for storing all courses.........
// recording all the courses in list all courses.......................................................
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
// cheking if course is present in student list of course..............................................
	public int isCoursepresent(String course){
		int a=0;
		for(String s:allCourses){
			if(s.equals(course))
				a=1;
		}
		return a;
	}
	
// method for professor, for selecting the course ......................................................	
	@Override
	public void selectProfCourse(String course) {
		
		Connection conn=DBUtil.getConnection();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.check_professor);
			stmt.setString(1,course);
			ResultSet rs = stmt.executeQuery();
			String check="error";
			while(rs.next()){		// for checking if another professor already teach the course
				 check=rs.getString("professor");
			}
			if(check.equals("error")){   // checking if course exist
				 logger.info("no course by that name, please select properly");
			}else if(check.equals("null")){
				 stmt = conn.prepareStatement(SQLConstantQuaries.prof_courseSelection);
				 stmt.setString(1,SMSDaoImpl.userName ); //professor
				 stmt.setString(2,course);
				 int rows = stmt.executeUpdate();					// executing the sql update query
			     logger.info("in "+ course+" professor is added at "+DateTimeUtil.TimeDateDay());
			}else{
				 logger.info("another professor has already been select ");
			}
		}catch(SQLException se){
		       logger.info("sql exceptio"+se.getMessage());
		 }catch(Exception e){
		       logger.info("Exception "+e.getMessage());
		 }
	}

// viewing the courses seected by the professor........................................................	
	@Override
	public List<Course> viewSelectedCourse() {
		Connection conn=DBUtil.getConnection();
		List<Course> courses=new ArrayList<Course>();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.view_selectedCourse);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Course course=new Course();
				course.setCoursecode(rs.getString("coursecode"));
				course.setCourseName(rs.getString("courseName"));
				course.setCredit(rs.getInt("course_credit"));
				course.setProfessor(rs.getString("professor"));
				courses.add(course);
			}
			
		}catch(SQLException se){
		       logger.info("sql exceptio"+se.getMessage());
		 }catch(Exception e){
		       logger.info("Exception "+e.getMessage());
		 }
		return courses;
	}

// viewing all the student present in the course.....................................................
	@Override
	public List<String> studentByCourse(String course) {
		Connection conn=DBUtil.getConnection();
		List<String> users=new ArrayList<String>();
		try{
			if(isCoursepresent(course)==1){
			stmt = conn.prepareStatement(SQLConstantQuaries.student_in_course);
			stmt.setString(1,course);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				users.add(rs.getString("userName"));
			}
			}else{
				logger.info("sorry this is not a course in school");
			}
		}catch(SQLException se){
		       logger.info("sql exceptio"+se.getMessage());
		 }catch(Exception e){
		       logger.info("Exception "+e.getMessage());
		 }
		return users;
	}
	
// uploading the grade of student in a course.........................................................
	@Override
	public void uploadGrade(String course) {
		Connection conn=DBUtil.getConnection();
		List<String> users=new ArrayList<String>();
		try{
			if(isCoursepresent(course)==1){   // getting all the student in the course.............
				stmt = conn.prepareStatement(SQLConstantQuaries.student_in_course1);
				stmt.setString(1,course);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					users.add(rs.getString("userName"));
				}
				for(String s:users){   // assigning grades to each users
					logger.info("Grade of "+s +" :: " ); 
					String grade=sc.next();
					stmt = conn.prepareStatement(SQLConstantQuaries.upload_grade);
					stmt.setString(1,grade);
					stmt.setString(2,s );
					stmt.setString(3, course);
					int rows = stmt.executeUpdate();
				}
			}else{
				logger.info("sorry this is not a course in school");
			}
		}catch(SQLException se){
		       logger.info("sql exceptio"+se.getMessage());
		 }catch(Exception e){
		       logger.info("Exception "+e.getMessage());
		 }
		
	}

}
