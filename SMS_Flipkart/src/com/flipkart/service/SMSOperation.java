package com.flipkart.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.dao.SMSDaoImpl;
import com.flipkart.utils.DateTimeUtil;

public class SMSOperation implements SMSOperationInterface{
	private static Logger logger =Logger.getLogger(SMSClient.class);
	Scanner sc=new Scanner(System.in);
	SMSDaoImpl smsDaoImpl=new SMSDaoImpl();
	//checking user is valid and which type of suer is it
	public void CheckUser(String username,String Password){
		String role=smsDaoImpl.Checkuser(username, Password);
		
		switch(role){
		case "student":
			logger.info("student with username "+ SMSDaoImpl.userName+" logged in  at "+DateTimeUtil.TimeDateDay());
			logger.info("going to student page");
			StudentOperation student=new StudentOperation();
			student.studentJob();
			break;
		case "professor":
			logger.info("professor with username "+SMSDaoImpl.userName  +" logged in at "+DateTimeUtil.TimeDateDay());
			logger.info("going to Professor page ");
			ProfessorOperation professorOperation=new ProfessorOperation();
			professorOperation.professorJob();
			break;
		case "admin":// if role is 3 means an admin
			logger.info("admin logged at "+DateTimeUtil.TimeDateDay());
			logger.info("going to admin page");
			AdminOperation adminOperation=new AdminOperation();
			adminOperation.adminJob();
			
			break;
		case "error":
			logger.error("error in credentials at "+DateTimeUtil.TimeDateDay());
			logger.info("enter username");
			String username1=sc.next();
			logger.info("enter password");
			String loginPassword1=sc.next();
			CheckUser(username1,loginPassword1);
			break;
		}
		
	}
}
