package com.flipkart.constant;

public class SQLConstantQuaries {
	// intially for checking whoever login to system.....................................................
	public static final String check_Login="select role,password,id from userData where username=?";
	
	// professor will select a course he will teach.........................................................
	//public static final String prof_courseSelection="insert into professor values(?,?,?)";
	
	//before selecting a student will be shown all courses available..........................................
	public static final String stu_getCourseDetails="select courseId,courseName from catalog";
	
	//after viewing all courses, he will select 4+2 courses...................................................
	public static final String stu_SelectCourse="insert into studentcourse values(?,?,?,?,?,?,?,?)";
	
	
	// admin opeartion...................................................................................
	public static final String user_creation="insert into userdata values(?,?,?,?)";
	public static final String student_creation="insert into studentData values(?,?,?,?,?,?,?,?)";
	public static final String professor_creation="insert into professordata values(?,?,?,?,?,?,?)";
	public static final String user_deletion="delete from userdata where username=?";
	public static final String student_deletion="delete from studentdata where username=?";
	public static final String professor_deletion="delete from professordata where username=?";
	public static final String catalog_creation="insert into coursecatalog values(?,?,?)";
	public static final String course_adition="insert into course values(?,?,?,?,?)";
	public static final String catalog_deletion="delete from coursecatalog where courseName=?";
	public static final String course_deletion="delete from course where courseName=?";
	public static final String view_student="select * from studentdata";
	public static final String view_professor="select * from professordata";
	public static final String view_course="select * from course";
	
	
	// professor opeartion...................................
	public static final String check_professor="select professor from course where courseName=?";
	public static final String prof_courseSelection="update course set professor=? where courseName=?";
	public static final String view_selectedCourse="select * from course where professor=?";
	
	//student opeartion
	public static final String student_selected_course="select * from studentcourse where username=?";
	public static final String student_add_course="insert into studentcourse values(?,?,?,?,?)";
	public static final String all_courses="select courseName from course";
	public static final String student_drop_course="delete from studentcourse where userName=? and courseName=?";
	
}
