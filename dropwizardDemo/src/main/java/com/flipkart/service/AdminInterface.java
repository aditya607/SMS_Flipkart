package com.flipkart.service;

import com.flipkart.model.User;

public interface AdminInterface {
	
// all the admin opeartions..................
	public void adminJob();
	public void studentOperation();
	public void userCreateOp(int role);
	public void userDeleteOP(int role);
	public void createStudent(User user);
	public void professorOperation();
	public void createProfessor(User user);
	public void addNewCourse();
	public void dropOldCourse();
	public void viewStudents();
	public void viewProfessor();
	public void viewCourse();

}
