package com.flipkart.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.service.SMSOperation;

public class SMSClient {
	private static Logger logger =Logger.getLogger(SMSClient.class);
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
		smsOperation.CheckUser(username,loginPassword);
	}
}
