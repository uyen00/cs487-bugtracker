package com.gheewhiz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	
	public static String format(Date date) {
		return dateFormat.format(date);
	}
	
	public static String format(Object obj) {
		return format(obj);
	}
	
	public static String format(Calendar cal) {
		return format(cal.getTime());
	}
	
	public static Date parse(String date) throws ParseException {
			return dateFormat.parse(date);
	}
}
