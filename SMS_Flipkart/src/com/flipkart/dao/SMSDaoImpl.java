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
import com.flipkart.utils.DBUtil;

public class SMSDaoImpl implements SMSDao{
// creating statement variable and logger oject............................................................
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(SMSClient.class);	
// storing username and userid of user who is currently logged in...................................	
	public static String userName;
	public static int userid;
	
// checking the user credentilas.......................................................................
	@Override
	public String Checkuser(String username, String Password) {
		Connection conn=DBUtil.getConnection();
		int role=5;
		try{
		stmt = conn.prepareStatement(SQLConstantQuaries.check_Login);
		stmt.setString(1,username);
		 ResultSet rs = stmt.executeQuery();
		 
		 while(rs.next()){   // checking is username is present by matching the password and username
			 if(rs.getString("password").equals(Password)){
					 role=rs.getInt("role"); // getting role for fi=urtur classification
					 userName=username;
		 	}
		 }
			rs.close();
			stmt.close();
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		   }catch(Exception e){
			   logger.error("exception"+e.getMessage());
		   }
		//classification according to the role.........
		if(role==1)
				return "student";
		else if(role==2)
				return "professor";
		else if(role==3)
				return "admin";
		else
				return "error";
	}



}
