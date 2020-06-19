package com.flipkart.dao;

import java.util.List;

import com.flipkart.model.Course;

public interface ProfessorDao {
	
	public void selectProfCourse(String courseId);
	public List<Course> viewSelectedCourse();
	public List<String> studentByCourse(String course);
	public void recordAllCourse();
	public void uploadGrade(String course);
}
