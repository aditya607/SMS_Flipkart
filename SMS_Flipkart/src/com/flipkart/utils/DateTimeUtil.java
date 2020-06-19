package com.flipkart.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeUtil {

	static LocalDate localDate = LocalDate.now();
	static LocalTime localTime = LocalTime.now();
	
// returns date with time and day..........................................................
	public static String TimeDateDay(){
		
		return localTime+"  "+localDate.getDayOfMonth()+" "+localDate.getMonth()+" "+localDate.getYear()+" on "+localDate.getDayOfWeek();
		
	}
// this will give only the date ....................................................
	public static String viewDate(){
		return localDate.getDayOfMonth()+" "+localDate.getMonth()+" "+localDate.getYear();
	}
}
