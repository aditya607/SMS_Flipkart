package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.service.PaymentOpeartion;
import com.flipkart.utils.DBUtil;
import com.flipkart.utils.DateTimeUtil;

public class PaymentDaoImpl implements PaymentDao{
	PreparedStatement stmt = null;
	private static Logger logger =Logger.getLogger(PaymentDaoImpl.class);
	Scanner sc=new Scanner(System.in);

	@Override
	public int calculateScholarship() {
		Connection conn=DBUtil.getConnection();
		int percent=0;
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.calculate_scholarship);
			stmt.setString(1,SMSDaoImpl.userName);
			ResultSet rs = stmt.executeQuery();
			int count=0;
			while(rs.next()){
				count++;
				System.out.println("scholarship "+rs.getString("scholarshipId")+" is applicable  with "+rs.getInt("amount")+" percent");
				percent=rs.getInt("amount");
			}
			if(count==0)
				System.out.println("sorry no scholarship for you ");
		}catch(SQLException se){
			logger.error("sql exception"+se.getMessage());
		}catch(Exception e){
			   logger.error("exception"+e.getMessage());
		}
		return percent;
	}

	@Override
	public int payByCreditCard(int totalAmount) {
		int status=0;
		System.out.println("enter credit card number ");
		String cardNumber=sc.next();
		System.out.println("enter expiry date");
		String expiryDate=sc.next();
		System.out.println("enter cvv");
		int cvv=sc.nextInt();
		Connection conn=DBUtil.getConnection();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.check_credit_card);
			stmt.setString(1,cardNumber);
			stmt.setString(2,expiryDate);
			stmt.setInt(3,cvv);
			ResultSet rs = stmt.executeQuery();
			int count=0;
			while(rs.next()){
				count++;
			}
			if(count==1)
				status=1;
		}catch(SQLException se){
			System.out.println("sql exception"+se.getMessage());
		}catch(Exception e){
			System.out.println("exception"+e.getMessage());
		}
		return status;
	}

	@Override
	public int payByDebitCard(int totalAmount) {
		int status=0;
		System.out.println("enter credit card number ");
		String cardNumber=sc.next();
		System.out.println("enter expiry date");
		String expiryDate=sc.next();
		System.out.println("enter cvv");
		int cvv=sc.nextInt();
		Connection conn=DBUtil.getConnection();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.check_debit_card);
			stmt.setString(1,cardNumber);
			stmt.setString(2,expiryDate);
			stmt.setInt(3,cvv);
			ResultSet rs = stmt.executeQuery();
			int count=0;
			while(rs.next()){
				count++;
			}
			if(count==1)
				status=1;
		}catch(SQLException se){
			System.out.println("sql exception"+se.getMessage());
		}catch(Exception e){
			System.out.println("exception"+e.getMessage());
		}
		return status;
	}

	@Override
	public void changeStatus() {
		Connection conn=DBUtil.getConnection();
		try{
			stmt = conn.prepareStatement(SQLConstantQuaries.final_registration);
			 stmt.setString(1,"complete" ); //professor
			 stmt.setString(2,SMSDaoImpl.userName);
			 int rows = stmt.executeUpdate();
			 if(rows!=0){
				 System.out.println("registration is completed. thank you :) :)");
			 }
		}catch(SQLException se){
			System.out.println("sql exception"+se.getMessage());
		}catch(Exception e){
			System.out.println("exception"+e.getMessage());
		}
		
	}

}
