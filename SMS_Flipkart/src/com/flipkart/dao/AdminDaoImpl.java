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
import com.flipkart.model.CourseCatalog;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class AdminDaoImpl implements AdminDao{

		// initializing logger and PreparedStatement.................................
		PreparedStatement stmt = null;
		private static Logger logger =Logger.getLogger(SMSClient.class);
		
		
// for creating a user ..................................................................................
	@Override
	public int createUser(User user) {
		int duplicateUser=0;
		Connection conn=DBUtil.getConnection();             //getting connection
		try{
			 stmt = conn.prepareStatement(SQLConstantQuaries.user_creation);
			 stmt.setInt(1, user.getUserId());
			 stmt.setString(2,user.getUserName());
			 stmt.setString(3,user.getUserPassword());
			 stmt.setInt(4,user.getUserRole());
			 int rows = stmt.executeUpdate();					// executing the sql update query
		     logger.info("user with username "+user.getUserName()+" is added");
		}catch(SQLException se){
			duplicateUser=1;
		     logger.error(se.getMessage());
		 }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		return duplicateUser;
	}
	
// for creating new student..................................................................................
	@Override
	public void createStudent(Student student) {
		Connection conn=DBUtil.getConnection();             //getting connection
		try{
			 stmt = conn.prepareStatement(SQLConstantQuaries.student_creation);
			 stmt.setString(1, student.getUsername());
			 stmt.setString(2,student.getStudentName());
			 stmt.setInt(3,student.getStudentEnrollmentNO());
			 stmt.setString(4,student.getStudentEmail());
			 stmt.setString(5,student.getStudentDOB());
			 stmt.setString(6,student.getStudentMobileNO());
			 stmt.setString(7,student.getStudentAddress());
			 stmt.setString(8,student.getStudentGender());
			 int rows = stmt.executeUpdate();					// executing the sql update query
		      logger.info("student with name "+ student.getStudentName()+" is added " +DateTimeUtil.TimeDateDay());
		}catch(SQLException se){
		     logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		
	}
// creating a new professor.................................................................................
	@Override
	public void createProfessor(Professor professor) {
			Connection conn=DBUtil.getConnection();             //getting connection
			try{
				 stmt = conn.prepareStatement(SQLConstantQuaries.professor_creation);
				 stmt.setString(1, professor.getProfessorusername()); //professor
				 stmt.setString(2,professor.getProfessorName());
				 stmt.setString(3,professor.getProfessorEmail());
				 stmt.setString(4,professor.getProfessortMobileNO());
				 stmt.setString(5,professor.getProfessortAddress());
				 stmt.setString(6,professor.getProfessorGender());
				 stmt.setString(7,professor.getProfessorRoomNo());
				 int rows = stmt.executeUpdate();					// executing the sql update query
			      logger.info("professor with name "+ professor.getProfessorName()+" is added "+DateTimeUtil.TimeDateDay());
			}catch(SQLException se){
			     logger.error("sql exception"+se.getMessage());
			   }catch(Exception e){
				   logger.error("exception"+e.getMessage());
			   }
		
	}
	
	
// deleting user from user data and professor data/student data...................................................
	@Override
	public void deleteUser(String username,int role) {
		Connection conn=DBUtil.getConnection();             //getting connection
		try{
			 stmt = conn.prepareStatement(SQLConstantQuaries.user_deletion);
			 stmt.setString(1, username); 
			 stmt.executeUpdate();
			 if(role==1)
				 stmt = conn.prepareStatement(SQLConstantQuaries.student_deletion);
			 else if(role==2)
				 stmt = conn.prepareStatement(SQLConstantQuaries.professor_deletion);

			 stmt.setString(1, username); 
			 stmt.executeUpdate();
			 if(role==1)
				 logger.info("student with username "+ username+" is deleted "+DateTimeUtil.TimeDateDay());
			 else if(role==2)
				 logger.info("professor with username "+ username+" is deleted "+DateTimeUtil.TimeDateDay());
		}catch(SQLException se){
		     logger.error("sql exception"+se.getMessage());
		}catch(Exception e){
			   logger.error("exception"+e.getMessage());
		}
	}
	

	@Override
	public int addNewCourse(CourseCatalog course) {
		int duplicateCourse=0;
		Connection conn=DBUtil.getConnection();             //getting connection
		try{
			 stmt = conn.prepareStatement(SQLConstantQuaries.catalog_creation);
			 stmt.setString(1, course.getCourseID()); //professor
			 stmt.setString(2,course.getCourseName());
			 stmt.setInt(3,course.getCatalogId());
			 int rows = stmt.executeUpdate();					// executing the sql update query
		    //  logger.info("course with name "+ professor.getProfessorName()+" is added "+DateTimeUtil.TimeDateDay());
		}catch(SQLException se){
			duplicateCourse=1;
		     logger.error("duplicate course "+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		return duplicateCourse;
		
	}

	@Override
	public void addCourseDetail(Course course) {
		Connection conn=DBUtil.getConnection();             //getting connection
		try{
			 stmt = conn.prepareStatement(SQLConstantQuaries.course_adition);
			 stmt.setString(1, course.getCoursecode()); //professor
			 stmt.setString(2,course.getCourseName());
			 stmt.setInt(3,course.getCredit());
			 stmt.setString(4,course.getCourseInfo());
			 stmt.setString(5,"null");
			 int rows = stmt.executeUpdate();					// executing the sql update query
		      logger.info("course with name "+ course.getCourseName()+" is added "+DateTimeUtil.TimeDateDay());
		}catch(SQLException se){
		     logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		
	}

	@Override
	public void dropOldCourse(String course) {
		Connection conn=DBUtil.getConnection();             //getting connection
		try{
			 stmt = conn.prepareStatement(SQLConstantQuaries.catalog_deletion);
			 stmt.setString(1, course); 
			 int row1 = stmt.executeUpdate();	
			 stmt = conn.prepareStatement(SQLConstantQuaries.course_deletion);
			 stmt.setString(1, course); 
			 int row2=stmt.executeUpdate();// executing the sql update query
			 if(row1==row2 && row1==1)
		      logger.info("course with name "+ course+" is deleted "+DateTimeUtil.TimeDateDay());
			 else
				 logger.info("error in deleting the course");
		}catch(SQLException se){
		     logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
	}

	@Override
	public List<Student> viewStudents() {
		Connection conn=DBUtil.getConnection();
		List<Student> students=new ArrayList<Student>();
		try{
		stmt = conn.prepareStatement(SQLConstantQuaries.view_student);
		ResultSet rs = stmt.executeQuery(SQLConstantQuaries.view_student);
		while(rs.next()){
			Student student=new Student();
			student.setStudentName(rs.getString("name"));
			student.setUsername(rs.getString("username"));
			student.setStudentEnrollmentNO(rs.getInt("enrollment no"));
			student.setStudentMobileNO(rs.getString("mobile number"));
			student.setStudentEmail(rs.getString("email"));
			student.setStudentAddress(rs.getString("address"));
			students.add(student);
			
		}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		return students;
	}

	@Override
	public List<Professor> viewProfessor() {
		Connection conn=DBUtil.getConnection();
		List<Professor> professors=new ArrayList<Professor>();
		try{
		stmt = conn.prepareStatement(SQLConstantQuaries.view_professor);
		ResultSet rs = stmt.executeQuery(SQLConstantQuaries.view_professor);
		while(rs.next()){
			Professor professor=new Professor();
			professor.setProfessorName(rs.getString("name"));
			professor.setProfessorusername(rs.getString("username"));
			professor.setProfessortMobileNO(rs.getString("mobile_number"));
			professor.setProfessorEmail(rs.getString("email"));
			professor.setProfessorRoomNo(rs.getString("room_number"));
			professors.add(professor);
			
		}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		return professors;
	}

	@Override
	public List<Course> viewCourse() {
		Connection conn=DBUtil.getConnection();
		List<Course> courses=new ArrayList<Course>();
		try{
		stmt = conn.prepareStatement(SQLConstantQuaries.view_course);
		ResultSet rs = stmt.executeQuery(SQLConstantQuaries.view_course);
		while(rs.next()){
			Course course=new Course();
			course.setCoursecode(rs.getString("coursecode"));
			course.setCourseName(rs.getString("courseName"));
			course.setCredit(rs.getInt("course_credit"));
			course.setProfessor(rs.getString("professor"));
			courses.add(course);
			
		}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		return courses;
	}
}
