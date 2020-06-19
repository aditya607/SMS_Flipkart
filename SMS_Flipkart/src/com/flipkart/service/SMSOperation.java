package com.flipkart.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.dao.SMSDaoImpl;
import com.flipkart.exception.InvalidCredential;
import com.flipkart.utils.DateTimeUtil;

public class SMSOperation implements SMSOperationInterface{
//creating objects of dao classes,scanner and logger.......................................
	private static Logger logger =Logger.getLogger(SMSClient.class);
	Scanner sc=new Scanner(System.in);
	SMSDaoImpl smsDaoImpl=new SMSDaoImpl();
	
// checking user is valid and which type of user is it ..........................................................
	public void CheckUser(String username,String Password) throws InvalidCredential{
		String role=smsDaoImpl.Checkuser(username, Password);
		
		switch(role){
		case "student":     // if user is student
			logger.info("student with username "+ SMSDaoImpl.userName+" logged in  at "+DateTimeUtil.TimeDateDay());
			logger.info("welcome to student page");
			StudentOperation student=new StudentOperation();
			student.studentJob();
			break;
		case "professor":   // if user is professor
			logger.info("professor with username "+SMSDaoImpl.userName  +" logged in at "+DateTimeUtil.TimeDateDay());
			logger.info("welcome to Professor page ");
			ProfessorOperation professorOperation=new ProfessorOperation();
			professorOperation.professorJob();
			break;
		case "admin":// if role is 3 means user is an admin
			logger.info("admin logged at "+DateTimeUtil.TimeDateDay());
			logger.info("welcome to admin page");
			AdminOperation adminOperation=new AdminOperation();
			adminOperation.adminJob();
			
			break;
		case "error":  // the login credentiala are wrong
			logger.error("error in credentials at "+DateTimeUtil.TimeDateDay());
			
			logger.info("enter username");
			String username1=sc.next();
			logger.info("enter password");
			String loginPassword1=sc.next();
			CheckUser(username1,loginPassword1);
			throw new InvalidCredential("failed to login");
		}
		
	}
}
