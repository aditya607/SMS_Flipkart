package com.flipkart.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipkart.dao.PaymentDaoImpl;

public class PaymentOpeartion {
	int totalAmount=20000;
	Scanner sc=new Scanner(System.in);
	private static Logger logger =Logger.getLogger(PaymentOpeartion.class);
	PaymentDaoImpl paymentDaoImpl=new PaymentDaoImpl();
	StudentOperation studentOperation=new StudentOperation();
	
	
	public void makePayment(){
		logger.info("select choices");logger.info("1.have a scholarship");logger.info("2.regular payment");
		int choice=sc.nextInt();
		if(choice==1){
			payByScholarship();
		}else if(choice==2){
			payRegular();
		}
		
	}
	public void payByScholarship(){
		int scholarship=paymentDaoImpl.calculateScholarship();
		totalAmount=totalAmount-(200*scholarship);
		logger.info("now you have to pay "+totalAmount+ " Rupees");
		
		payRegular();
	}
	public void payRegular(){
		logger.info("select choices");logger.info("1.make credit card payment");logger.info("2.make debit card payment");
		logger.info("3.make regular payment");logger.info("4.logout");
		int choice=sc.nextInt();
		if(choice==1){
			creditCardPayment();
		}else if(choice==2){
			debitCardPayment();
		}else if(choice==3){
			regularPay();
		}else if(choice==4){
			studentOperation.studentJob();
		}
	}
	public void creditCardPayment(){
		System.out.println("in credit card payment");
		int status=paymentDaoImpl.payByCreditCard(totalAmount);
		if(status==1){
			logger.info("payment Succesfull ,"+totalAmount+" is deducted from account on ");
			paymentDaoImpl.changeStatus();
			studentOperation.studentJob();
		}else{
			logger.info("wrong information , try again");
			payRegular();
		}
	}
	public void debitCardPayment(){
		int status=paymentDaoImpl.payByDebitCard(totalAmount);
		if(status==1){
			logger.info("payment Succesfull ,"+totalAmount+" is deducted from account on ");
			paymentDaoImpl.changeStatus();
			studentOperation.studentJob();
		}else{
			logger.info("wrong information , try again");
			payRegular();
		}
	}
	public void regularPay(){
		logger.info("1.if payment done");
		logger.info("2.payment done");
		int Choice=sc.nextInt();
		if(Choice==1){
			paymentDaoImpl.changeStatus();
			studentOperation.studentJob();
		}else if (Choice==2){
			logger.info("erroe in payment ");
			payRegular();
		}
	}
}
