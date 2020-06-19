package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.client.AdminRestOpeartor;
import com.flipkart.constant.SQLConstantQuaries;
import com.flipkart.model.Course;
import com.flipkart.model.CourseCatalog;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.utils.DBUtil;

public interface AdminDao {
	
	public int createUser(User user);
	public void createStudent(Student student);
	public int addNewCourse(CourseCatalog course);
	public void createProfessor(Professor professor);
	public void deleteUser(String username,int role);
	public void addCourseDetail(Course course);
	public void dropOldCourse(String course);
	public List<Student> viewStudents();
	public List<Professor> viewProfessor();
	public List<Course> viewCourse();
	public void updateStudent(Student student,String username);
	public void updateProfessor(Professor professor, String username);
}
