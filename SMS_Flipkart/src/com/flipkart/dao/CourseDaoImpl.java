package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.utils.DBUtil;

public class CourseDaoImpl implements CourseDao{

// initializing logger and PreparedStatement.................................
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(SMSClient.class);
	

// method for students to select courses....................................................................................
	@Override
	public void selectCourse(List<Integer> selectedCourse) {
		Connection conn=DBUtil.getConnection();             //getting connection
		try{
		 stmt = conn.prepareStatement(SQLConstantQuaries.stu_SelectCourse);
		 stmt.setInt(1, 1);
		 stmt.setInt(2,selectedCourse.get(0));
		 stmt.setInt(3,selectedCourse.get(1));
		 stmt.setInt(4,selectedCourse.get(2));
		 stmt.setInt(5,selectedCourse.get(3));
		 stmt.setInt(6,selectedCourse.get(4));
		 stmt.setInt(7,selectedCourse.get(5));
		 stmt.setInt(8,selectedCourse.get(6));
		 int rows = stmt.executeUpdate();					// executing the sql update query
	      System.out.println("Rows impacted : " + rows );
		}catch(SQLException se){
		     logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		
		
	}
}
