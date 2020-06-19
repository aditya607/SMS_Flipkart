package com.flipkart.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.dao.SMSDaoImpl;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.service.SMSOperation;

@Path("/admin")
public class AdminRestOpeartor {
	private static Logger logger =Logger.getLogger(AdminRestOpeartor.class);
	AdminDaoImpl adminDaoImpl=new AdminDaoImpl();
	SMSDaoImpl smsDaoImpl =new SMSDaoImpl();
	Scanner sc=new Scanner(System.in);

// getting all the students...........................................................
	@GET
	@Path("/students")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getCustomerDetails(){
		List<Student> students=new ArrayList<Student>();
		students=adminDaoImpl.viewStudents();
		return students;
	}
// getting the list of all the professor................................................................
	@GET
	@Path("/professors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorDetails(){
		List<Professor> professors=new ArrayList<Professor>();
		professors=adminDaoImpl.viewProfessor();
		return professors;
		
	}
// getting the list of courses...........................................................................	
	@GET
	@Path("/courses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseDetails(){
		List<Course> courses=new ArrayList<Course>();
		courses=adminDaoImpl.viewCourse();
		return courses;
		
	}
// checking username and getting the role
	@GET
	@Path("/checkUser/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCourseDetails(@PathParam("username")String username,@PathParam("password")String password){
		String role=smsDaoImpl.Checkuser(username, password);
		logger.info("role is "+role);
		return role;
		
	}
	
	
// adding a new student in student table..............................................................	
	@POST
	@Path("/postStudent")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createStudent(Student student){
		SMSDaoImpl.userName="vivek";
		adminDaoImpl.createStudent(student);
		String result="track saved "+student;
		return Response.status(201).entity(result).build();
	}
// adding a new user in suer table....................................................................	
	@POST
	@Path("/postUser")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user){
		int duplicateUser=adminDaoImpl.createUser(user);
		if(duplicateUser==1){
			logger.info("sorry duplicate user");
		}
		String result="track saved "+user;
		return Response.status(201).entity(result).build();
	}
	

// deleting student from student table.........................................................
	@DELETE
	@Path("/delete/student/{userName}")
	public Response deleteStudent(@PathParam("userName")String userName){
		String username=userName;
		adminDaoImpl.deleteUser(username,1);
		return Response.status(200).entity("username deleted successfully ").build();
	}
//deleting professor from professor table ............................................................
	@DELETE
	@Path("/delete/professor/{userName}")
	public Response deleteProfessor(@PathParam("userName")String userName){
		String username=userName;
		adminDaoImpl.deleteUser(username,2);
		return Response.status(200).entity(" userName deleted successfully ").build();
	}
// deleting course from course table..................................................................	
	@DELETE
	@Path("/delete/course/{courseName}")
	public Response deleteCourse(@PathParam("courseName")String courseName){
		String username=courseName;
		adminDaoImpl.dropOldCourse(courseName);
		return Response.status(200).entity("courseName deleted successfully ").build();
	}
//deleting user from user table and student table ......................................................	
	@DELETE
	@Path("/delete/user/{userName}/{role}")
	public Response deleteUser(@PathParam("userName")String userName,@PathParam("role")int role){
		String username=userName;
		adminDaoImpl.deleteUser(username,role);
		return Response.status(200).entity("username deleted successfully ").build();
	}
	
// updation of student details..............................................................	
	@PUT
	@Path("/updateStudent/{username}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Student updateStudent(Student student,@PathParam("username")String userName){
		adminDaoImpl.updateStudent(student,userName);
		return student;
	}
// updating professor details.............................................................................
	@PUT
	@Path("/updateProfessor/{username}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(Professor professor,@PathParam("username")String userName){
		adminDaoImpl.updateProfessor(professor,userName);
		return professor;
	}

}
