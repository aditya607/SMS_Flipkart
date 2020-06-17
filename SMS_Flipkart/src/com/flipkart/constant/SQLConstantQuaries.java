package com.flipkart.constant;

public class SQLConstantQuaries {
	// intially for checking whoever login to system.....................................................
	public static final String check_Login="select role,password,id from user where username=?";
	
	//before selecting a student will be shown all courses available..........................................
	public static final String stu_getCourseDetails="select courseId,courseName from catalog";
	
	//after viewing all courses, he will select 4+2 courses...................................................
	public static final String stu_SelectCourse="insert into studentcourse values(?,?,?,?,?,?,?,?)";
	
	
	// admin opeartion...................................................................................
	public static final String user_creation="insert into user values(?,?,?,?)";
	public static final String student_creation="insert into student values(?,?,?,?,?,?,?,?)";
	public static final String professor_creation="insert into professor values(?,?,?,?,?,?,?)";
	public static final String user_deletion="delete from user where username=?";
	public static final String student_deletion="delete from student where username=?";
	public static final String professor_deletion="delete from professor where username=?";
	public static final String catalog_creation="insert into coursecatalog values(?,?,?)";
	public static final String course_adition="insert into course values(?,?,?,?,?)";
	public static final String catalog_deletion="delete from coursecatalog where courseName=?";
	public static final String course_deletion="delete from course where courseName=?";
	public static final String view_student="select * from student";
	public static final String view_professor="select * from professor";
	public static final String view_course="select * from course";
	
	
	// professor opeartion...................................register
	public static final String check_professor="select professor from course where courseName=?";
	public static final String prof_courseSelection="update course set professor=? where courseName=?";
	public static final String view_selectedCourse="select * from course where professor=?";
	public static final String student_in_course="select * from register where courseName=?";
	public static final String upload_grade="update register set grade=? where userName=? and courseName=?";
	public static final String student_in_course1="select register.userName from register inner join student on register.userName=student.username where register.courseName=?";
	
	//student opeartion
	public static final String student_selected_course="select * from register where username=?";
	public static final String student_add_course="insert into register values(?,?,?,?,?)";
	public static final String all_courses="select courseName from course";
	public static final String student_drop_course="delete from register where userName=? and courseName=?";
	
	//courseregister operation...........................................................................................
	public static final String final_registration="update register set registration=? where userName=?";
	
	//payment operation..................................................................................................
	public static final String calculate_scholarship="select * from scholarship where userName=?";
	public static final String check_credit_card="select * from creditcard where cardNumber=? and expiryDate=? and cvv=?";
	public static final String check_debit_card="select * from debitcard where cardNumber=? and expiryDate=? and cvv=?";
	public static final String change_status="update register set registration=? where userName=?";
	
}
