package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.service.StudentOperation;
import com.flipkart.utils.DBUtil;

public class CourseDaoImpl implements CourseDao{

// initializing logger and PreparedStatement.................................
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(SMSClient.class);
	
	@Override
	public void finalRegistration() {
		Connection conn=DBUtil.getConnection();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.student_selected_course);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			int courseCount=0;
			while(rs.next()){
				courseCount++;
			}
			stmt = conn.prepareStatement(SQLConstantQuaries.final_registration);
			 stmt.setString(1,"partial" ); //professor
			 stmt.setString(2,SMSDaoImpl.userName);
			 int rows = stmt.executeUpdate();	
			 	StudentOperation studentOperation=new StudentOperation();
			 if(courseCount==6){
				 	logger.info("registration partial done, do payment for complete registration");
				 	studentOperation.studentJob();
			 }
			 else{
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
