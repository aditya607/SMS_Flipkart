package com.flipkart.dao;

import java.util.HashMap;
import java.util.List;

import com.flipkart.model.Course;

public interface StudentDao {
	public void recordAllCourse();
	public void studentAddCourse(String course);
	public void studentDropCourse(String course);
	public HashMap<String,String> studentCourse();
	public String checkRegistration();
	public HashMap<String,String> viewGrades();
}
