package com.flipkart.service;

import com.flipkart.dao.CourseDaoImpl;

public class CourseRegister implements courseRegInterface{
	public void finalRegistration(){
		CourseDaoImpl courseDaoImpl=new CourseDaoImpl();
		courseDaoImpl.finalRegistration();
	}
}
