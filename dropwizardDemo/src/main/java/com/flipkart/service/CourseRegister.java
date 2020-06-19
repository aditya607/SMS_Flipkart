package com.flipkart.service;

import com.flipkart.dao.CourseDaoImpl;

public class CourseRegister implements courseRegInterface{
	
// after all the course selection if student wishes to do the final registration......................
	public void finalRegistration(){
		CourseDaoImpl courseDaoImpl=new CourseDaoImpl();
		courseDaoImpl.finalRegistration();
	}
}
