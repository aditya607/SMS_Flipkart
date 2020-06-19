package com.flipkart.constant;

public class SQLConstantQuaries {

// ADMMIN OPERATION ......................................................................................
	// creating a user by admin
	public static final String user_creation="insert into user values(?,?,?,?)";
	// creating a student by admin
	public static final String student_creation="insert into student values(?,?,?,?,?,?,?,?)";
	//creating a professor by admin
	public static final String professor_creation="insert into professor values(?,?,?,?,?,?,?)";
	//deleting any user by admin
	public static final String user_deletion="delete from user where username=?";
	//deleting student by admin
	public static final String student_deletion="delete from student where username=?";
	//deleting professor by admin
	public static final String professor_deletion="delete from professor where username=?";
	//creating a new course in catalog
	public static final String catalog_creation="insert into coursecatalog values(?,?,?)";
	//creating a new course after catalog
	public static final String course_adition="insert into course values(?,?,?,?,?)";
	//deleting a course in the catalog
	public static final String catalog_deletion="delete from coursecatalog where courseName=?";
	//course deletion in course after catalog deletion
	public static final String course_deletion="delete from course where courseName=?";
	//viewing all the student
	public static final String view_student="select * from student";
	//viewing all the profesor
	public static final String view_professor="select * from professor";
	//viewing all the student
	public static final String view_course="select * from course";
	//updating existing student details
	public static final String update_student="update student set email=?,address=?,mobileNumber=? where username=?";
	// updating eisting professor details
	public static final String update_professor="update professor set email=?,address=?,mobile_number=?,room_number=? where username=?";
	
// PROFESSOR OPERATION..................................................................................
	//checking the professor who teach the course
	public static final String check_professor="select professor from course where courseName=?";
	// course selection done by the professor
	public static final String prof_courseSelection="update course set professor=? where courseName=?";
	// which are the courses selected ny the professor
	public static final String view_selectedCourse="select * from course where professor=?";
	// all the student who have selected particular course
	public static final String student_in_course="select * from register where courseName=?";
	// uploading grade of the student
	public static final String upload_grade="update register set grade=? where userName=? and courseName=?";
	// uploading grade with inner join to student table
	public static final String student_in_course1="select register.userName from register inner join student on register.userName=student.username where register.courseName=?";
	
// STUDENT OPERATION ....................................................................................
	// selecting students from register table a/c to user name
	public static final String student_selected_course="select * from register where username=?";
	//course added by the student
	public static final String student_add_course="insert into register values(?,?,?,?,?)";
	//viewing al the courses selected by the student
	public static final String all_courses="select courseName from course";
	//course dropping by the student
	public static final String student_drop_course="delete from register where userName=? and courseName=?";
	
// COURSE REGISTRATION OPERATION...........................................................................................
	public static final String final_registration="update register set registration=? where userName=?";
	
// PAYMENT OPERATION ..................................................................................................
	// if student have scholarship, get the scholarship details
	public static final String calculate_scholarship="select * from scholarship where userName=?";
	// credit card verification
	public static final String check_credit_card="select * from creditcard where cardNumber=? and expiryDate=? and cvv=?";
	//debit card verification
	public static final String check_debit_card="select * from debitcard where cardNumber=? and expiryDate=? and cvv=?";
	// changing status of registration after payment complete
	public static final String change_status="update register set registration=? where userName=?";
	
	
	
	// intially for checking whoever login to system.....................................................
	public static final String check_Login="select role,password,id from user where username=?";
	
	//before selecting a student will be shown all courses available..........................................
	public static final String stu_getCourseDetails="select courseId,courseName from catalog";
	
	//after viewing all courses, he will select 4+2 courses...................................................
	public static final String stu_SelectCourse="insert into studentcourse values(?,?,?,?,?,?,?,?)";
	
	
}
