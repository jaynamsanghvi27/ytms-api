package com.yash.ytms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

import org.springframework.stereotype.Component;

@Component
public class DateTimeUtil {

	
	//This method is used to return startdate of the month 
	public LocalDateTime getStartLocalDateTime(LocalDate localDate)
	{
		YearMonth yearMonth = YearMonth.of( localDate.getYear(), localDate.getMonth() );    
		LocalDate firstOfMonth = yearMonth.atDay( 1 );    
		return LocalDateTime.of(firstOfMonth, LocalTime.MIN);
	}
	
	//This method is used to return enddate of the month 
	public LocalDateTime getEndLocalDateTime(LocalDate localDate)
	{
		YearMonth yearMonth = YearMonth.of( localDate.getYear(), localDate.getMonth() );     
		LocalDate lastOfMonth = yearMonth.atEndOfMonth();  // 2015-01-31
		return LocalDateTime.of(lastOfMonth, LocalTime.MAX);
	}
	
}
