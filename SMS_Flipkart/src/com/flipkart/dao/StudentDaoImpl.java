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
import com.flipkart.client.SMSClient;
import com.flipkart.utils.DBUtil;

public class StudentDaoImpl implements StudentDao{
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(SMSClient.class);
	@Override
	public HashMap<Integer,String> getCourseDetails() {
		HashMap<Integer,String> allCourses=new HashMap<Integer,String>();

		Connection conn=DBUtil.getConnection();
		try{
		stmt = conn.prepareStatement(SQLConstantQuaries.stu_getCourseDetails);
		ResultSet rs = stmt.executeQuery(SQLConstantQuaries.stu_getCourseDetails);
		while(rs.next()){
			int courseId=rs.getInt("courseId");
			String courseName=rs.getString("courseName");
			allCourses.put(courseId, courseName);
			
		}
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }

		return allCourses;
	}


}
