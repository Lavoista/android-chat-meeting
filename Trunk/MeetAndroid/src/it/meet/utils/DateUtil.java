package it.meet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.Context;

public class DateUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-mm-dd hh:mm:ss");

	public static Date getDate(String string) {
		Date toReturn;
		try {
			toReturn = sdf.parse(string);
			return toReturn;
		} catch (ParseException e) {
			// handle exception here ! }
			e.printStackTrace();
			return null;
		}
	}

	public static String getString(Date date) {
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String formatDateToLocale(Date date, Context context) {
		try {
			java.text.DateFormat dateFormat = android.text.format.DateFormat
					.getDateFormat(context);
			String s = dateFormat.format(date);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int getAge(Date birthday)
	{
	    GregorianCalendar today = new GregorianCalendar();
	    GregorianCalendar bday = new GregorianCalendar();
	    GregorianCalendar bdayThisYear = new GregorianCalendar();

	    bday.setTime(birthday);
	    bdayThisYear.setTime(birthday);
	    bdayThisYear.set(Calendar.YEAR, today.get(Calendar.YEAR));

	    int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);

	    if(today.getTimeInMillis() < bdayThisYear.getTimeInMillis())
	        age--;

	    return age;
	}

}
