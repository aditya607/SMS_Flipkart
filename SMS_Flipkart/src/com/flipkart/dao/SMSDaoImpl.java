package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.utils.DBUtil;

public class SMSDaoImpl implements SMSDao{


	PreparedStatement stmt = null;	
	
	
	@Override
	public int Checkuser(String username, String Password) {
		Connection conn=DBUtil.getConnection();
		int role=5;
		try{
		stmt = conn.prepareStatement(SQLConstantQuaries.check_Login);
		stmt.setString(1,username);
		 ResultSet rs = stmt.executeQuery();
		 
		 while(rs.next()){
			 if(rs.getString("password").equals(Password)){
					 role=rs.getInt("role");
		 	}
		 }
		 /*String pwsd=rs.getString("password");
		 if(pwsd.equals(Password)){
			 role=rs.getInt(role);
			 
		 }
		 else{
			 role=5;
		 }*/

			rs.close();
			stmt.close();
		}catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		
		
		
		return role;
	}


	@Override
	public List<String> getCourseDetails() {
		List<String> courses=new ArrayList<String>();

		Connection conn=DBUtil.getConnection();
		String sql="select courses from catalog";
		try{
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			
		}
		}catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		return null;
	}

}
