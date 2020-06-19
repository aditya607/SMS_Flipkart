package com.flipkart.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.exception.InvalidCredential;
import com.flipkart.utils.DateTimeUtil;

public interface defaultInterface {
	Scanner sc=new Scanner(System.in);
	default void logout1(String role){
		System.out.println( role +" logging out at "+ DateTimeUtil.TimeDateDay());
		System.out.println("WELCOME");
		SMSOperation smsopeartion=new SMSOperation();
		System.out.println("enter username");
		String username1=sc.next();
		System.out.println("enter password");
		String loginPassword1=sc.next();
		try{
			smsopeartion.CheckUser(username1,loginPassword1);
			}catch (InvalidCredential e){
				System.out.println(e.getError());
			}
	}
}
