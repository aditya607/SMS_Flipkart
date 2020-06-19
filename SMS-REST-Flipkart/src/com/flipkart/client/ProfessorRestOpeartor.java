package com.flipkart.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.dao.ProfessorDaoImpl;
import com.flipkart.dao.SMSDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;

@Path("/professor")
public class ProfessorRestOpeartor {
// creating objects of dao class and varisbles............................................................	
	private static Logger logger =Logger.getLogger(AdminRestOpeartor.class);
	AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
	SMSDaoImpl smsDaoImpl =new SMSDaoImpl();
	ProfessorDaoImpl professorDaoImpl=new ProfessorDaoImpl();
	Scanner sc=new Scanner(System.in);

// getting all the course details...........................................................................
	@GET
	@Path("/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseDetails(){
		List<Course> courses=new ArrayList<Course>();
		courses=adminDaoImpl.viewCourse();
		return courses;
	}
// course selection done by professor..................................................................................	
	@PUT
	@Path("/selectCourse/{username}/{course}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateProfessor(@PathParam("course")String course,@PathParam("username")String username){
		smsDaoImpl.userName=username;
		professorDaoImpl.selectProfCourse(course);
		return "operation successful";
	}
// shows courses selected by the students ................................................................................
	@GET
	@Path("/selectedCourses/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getSelectedCourse(@PathParam("username")String username){
		List<Course> courses=new ArrayList<Course>();
		smsDaoImpl.userName=username;
		courses=professorDaoImpl.viewSelectedCourse();
		return courses;
	}
// 	shows all the student present in a course...................................................................
	@GET
	@Path("/studentInCourses/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getStudentCourse(@PathParam("course")String course){
		List<String> users=new ArrayList<String>();
		professorDaoImpl.recordAllCourse();
		users=professorDaoImpl.studentByCourse(course);
		return users;
	}
}
