package com.coderhouse.api.lib;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DatesLib {

	public int getDateOfBirth(LocalDate d) {
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = d.format(pattern);  //17-02-2022
		
		//DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");  
		//String strDate = dateFormat.format(d);  
		LocalDate date = LocalDate.parse(formattedDate);
		
		LocalDate curDate = LocalDate.now();
		// calculates the amount of time between two dates and returns the years
		if ((date != null) && (curDate != null)) {
			return Period.between(date, curDate).getYears();
		} else {
			return 0;
		}
	}
	
	public String getOnlyDate(LocalDate d) {
		
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedDate = d.format(pattern);  //17-02-2022
		
		return formattedDate;
	}
}
