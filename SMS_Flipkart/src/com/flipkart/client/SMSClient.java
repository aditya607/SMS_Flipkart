package com.flipkart.client;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.flipkart.exception.InvalidCredential;
import com.flipkart.service.SMSOperation;

public class SMSClient {
	private static Logger logger =Logger.getLogger(SMSClient.class);// creting logger objects
	public static void main(String[] args){
		SMSOperation smsOperation=new SMSOperation();
		System.out.println("welcome to :: STUDENT MANAGEMENT SYSTEM");
		
//taking login id and passqoed from user ........................................	

		logger.info("WELCOME");
		Scanner sc=new Scanner(System.in);
		logger.info("enter username");
		String username=sc.next();
		logger.info("enter password");
		String loginPassword=sc.next();
		try{
		smsOperation.CheckUser(username,loginPassword); //sending userid and password for checking
		}catch (InvalidCredential e){
			logger.error(e.getError());
		}
		
	}
}
