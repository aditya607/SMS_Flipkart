package com.flipkart.dao;

import java.util.HashMap;
import java.util.List;

public interface StudentDao {
	public void recordAllCourse();
	public void studentAddCourse(String course);
	public void studentDropCourse(String course);
}
