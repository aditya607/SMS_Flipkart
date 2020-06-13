package com.flipkart.constant;

public class SQLConstantQuaries {
	// intially for checking whoever login to system.....................................................
	public static final String check_Login="select role,password,id from userData where username=?";
	
	// professor will select a course he will teach.........................................................
	public static final String prof_courseSelection="insert into professor values(?,?,?)";
	
	//before selecting a student will be shown all courses available..........................................
	public static final String stu_getCourseDetails="select courseId,courseName from catalog";
	
	//after viewing all courses, he will select 4+2 courses...................................................
	public static final String stu_SelectCourse="insert into studentcourse values(?,?,?,?,?,?,?,?)";
	
	public static final String user_creation="insert into userdata values(?,?,?,?)";
	public static final String student_creation="insert into studentData values(?,?,?,?,?,?,?,?)";
	public static final String professor_creation="insert into professordata values(?,?,?,?,?,?,?)";
	public static final String user_deletion="delete from userdata where username=?";
	public static final String student_deletion="delete from studentdata where username=?";
	public static final String professor_deletion="delete from professordata where username=?";
	
}
