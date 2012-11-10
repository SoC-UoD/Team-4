package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

public class UtilityFunctions {
	public static String convertStreamToString(InputStream is) {
		/*
		 * To convert the InputStream to string we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 * 
		 * From stack overflow example... refference?
		 */

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	 
	public static int[] previousFridayDate() {
		Calendar myCal = Calendar.getInstance();
		int date[] = new int[3];

		if (Calendar.DAY_OF_WEEK == Calendar.FRIDAY) {
			myCal.add(Calendar.DAY_OF_WEEK, -1);
		}

		while (myCal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
			myCal.add(Calendar.DAY_OF_WEEK, -1);
		}

		date[0] = myCal.get(Calendar.DAY_OF_MONTH);
		date[1] = (myCal.get(Calendar.MONTH)) + 1;
		date[2] = myCal.get(Calendar.YEAR);
		return date;
	}
	
	
	public static String currentDateAndTime() 
	{
		Calendar myCal = Calendar.getInstance();
		int dateAndTime[] = new int[5];
		
		dateAndTime[0] = myCal.get(Calendar.DAY_OF_MONTH);
		dateAndTime[1] = (myCal.get(Calendar.MONTH)) + 1;
		dateAndTime[2] = myCal.get(Calendar.YEAR);
		dateAndTime[3] = myCal.get(Calendar.HOUR_OF_DAY);
		dateAndTime[4] = myCal.get(Calendar.MINUTE);
		
		String dateTime = "";
		
		if (dateAndTime[0] < 10)
			dateTime += "0" + dateAndTime[0]; 
		else
			dateTime += dateAndTime[0]; 
		
		if (dateAndTime[1] < 10)
			dateTime += "/0" + dateAndTime[1]; 
		else
			dateTime += "/" + dateAndTime[1]; 
		
		dateTime += "/" + dateAndTime[2] + " " + dateAndTime[3] + ":";
		
		if (dateAndTime[4] < 10)
			dateTime += "0" + dateAndTime[4];
		else
			dateTime += dateAndTime[4];
		
		return dateTime;
	}
	
	
	public static long convertToPoundsRounded(double value)
	{
		double total = value / 100;
		return  Math.round(total);
	}
	
	public static String formatCommas(long roundedTotal)
	{
		String portfolio = String.valueOf(roundedTotal);

		String sub1 = "";
		String sub2 = "";
		String sub3 = "";
		if (portfolio.length() > 6) {
			sub1 = portfolio.substring((portfolio.length() - 3),
					(portfolio.length()));
			sub2 = portfolio.substring((portfolio.length() - 6),
					portfolio.length() - 3);
			sub3 = portfolio.substring(0, portfolio.length() - 6);

			return sub3 + "," + sub2 + "," + sub1;
		} else if (portfolio.length() > 3) {
			sub1 = portfolio.substring((portfolio.length() - 3),
					(portfolio.length()));
			sub2 = portfolio.substring(0, portfolio.length() - 3);

			return sub2 + "," + sub1 +  "\n";
		} else {
			return  portfolio;
		}
	}
}
