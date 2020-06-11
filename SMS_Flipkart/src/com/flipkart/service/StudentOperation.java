package com.flipkart.service;

import java.util.ArrayList;
import java.util.List;

import com.flipkart.dao.SMSDaoImpl;

public class StudentOperation {
	SMSDaoImpl smsDaoImpl=new SMSDaoImpl();
	List<String> courses=new ArrayList<String>();
		public void getCourseDetails(){
			smsDaoImpl.getCourseDetails();
			
		}
}
