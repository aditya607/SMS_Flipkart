package com.flipkart.dao;

import java.util.List;

public interface SMSDao {
		//cheking login credentials............
	public int Checkuser(String username,String Password);
	public List<String> getCourseDetails();
}
