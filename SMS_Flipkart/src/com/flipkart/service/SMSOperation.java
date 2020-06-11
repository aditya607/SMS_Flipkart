package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.client.SMSClient;
import com.flipkart.dao.SMSDaoImpl;

public class SMSOperation {
	private static Logger logger =Logger.getLogger(SMSClient.class);
	
	SMSDaoImpl smsDaoImpl=new SMSDaoImpl();
	//checking user is valid and which type of suer is it
	public void CheckUser(String username,String Password){
		int a=smsDaoImpl.Checkuser(username, Password);
		logger.info(a);
		if(a==1){
			logger.info("student page");
		}
		else if(a==2){
			logger.info("Professor page");
		}
		else if(a==3){
			logger.info("admin page");
		}
		else if(a==5){
			logger.error("error in credentials");
		}
		
	}
}
