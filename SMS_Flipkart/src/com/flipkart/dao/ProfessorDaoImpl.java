package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.utils.DBUtil;

public class ProfessorDaoImpl implements ProfessorDao{
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(SMSClient.class);

	@Override
	public void selectProfCourse(int courseId) {
		
		Connection conn=DBUtil.getConnection();
		try{
			 stmt = conn.prepareStatement(SQLConstantQuaries.prof_courseSelection);
			 stmt.setInt(1, 2);
			 stmt.setInt(2,SMSDaoImpl.userid);
			 stmt.setInt(3,courseId);
			 int rows = stmt.executeUpdate();
		      logger.info("Rows impacted : " + rows );
			
		}catch(SQLException se){
		      logger.info("sql exceptio"+se.getMessage());
		   }catch(Exception e){
		      logger.info("Exception "+e.getMessage());
		   }
		
		
		
		
	}

}
