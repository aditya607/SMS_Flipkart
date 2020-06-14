package com.flipkart.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeUtil {

	static LocalDate localDate = LocalDate.now();
	static LocalTime localTime = LocalTime.now();
	
	public static String TimeDateDay(){
		
		return localTime+"  "+localDate.getDayOfMonth()+" "+localDate.getMonth()+" "+localDate.getYear()+" on "+localDate.getDayOfWeek();
		
	}
}
