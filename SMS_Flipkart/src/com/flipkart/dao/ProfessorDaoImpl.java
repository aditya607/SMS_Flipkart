package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.model.Course;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class ProfessorDaoImpl implements ProfessorDao{
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(SMSClient.class);

	
	List<String> allCourses=new ArrayList<String>();
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
	public int isCoursepresent(String course){
		int a=0;
		for(String s:allCourses){
			if(s.equals(course))
				a=1;
		}
		return a;
	}
	
	
	@Override
	public void selectProfCourse(String course) {
		
		Connection conn=DBUtil.getConnection();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.check_professor);
			stmt.setString(1,course);
			ResultSet rs = stmt.executeQuery();
			String check="error";
			while(rs.next()){
				 check=rs.getString("professor");
			}
			if(check.equals("error")){
				 logger.info("no course by that name, please select properly");
			}else if(check.equals("null")){
				 stmt = conn.prepareStatement(SQLConstantQuaries.prof_courseSelection);
				 stmt.setString(1,SMSDaoImpl.userName ); //professor
				 stmt.setString(2,course);
				 int rows = stmt.executeUpdate();					// executing the sql update query
			     logger.info("in "+ course+"professor is added at "+DateTimeUtil.TimeDateDay());
				 logger.info("in nULL");
			}else{
				 logger.info("another professor has already been select ");
			}
		}catch(SQLException se){
		       logger.info("sql exceptio"+se.getMessage());
		 }catch(Exception e){
		       logger.info("Exception "+e.getMessage());
		 }
	}

	
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

}
