package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.service.StudentOperation;
import com.flipkart.utils.DBUtil;

public class CourseDaoImpl implements CourseDao{

// initializing logger and PreparedStatement.................................
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(CourseDaoImpl.class);
	
// after selecting all courses doing final registration............................................
	@Override
	public void finalRegistration() {
		Connection conn=DBUtil.getConnection();
	 	StudentOperation studentOperation=new StudentOperation();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.student_selected_course);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			int courseCount=0;
			while(rs.next()){
				courseCount++;
			}
			if(courseCount==6){  // checking if total courses is 6 or not
			stmt = conn.prepareStatement(SQLConstantQuaries.final_registration);
			 stmt.setString(1,"partial" ); //professor
			 stmt.setString(2,SMSDaoImpl.userName);
			 int rows = stmt.executeUpdate();	
				 	logger.info("registration partial done, do payment for complete registration");
				 	studentOperation.studentJob();
			 }
			 else{       // if  courses is not selected
				 	logger.info("first select all 6 courses");
				 	studentOperation.studentJob();
			 }
			}catch(SQLException se){
			       logger.info("sql exceptio"+se.getMessage());
			 }catch(Exception e){
			       logger.info("Exception "+e.getMessage());
			 }
	}
	


}
