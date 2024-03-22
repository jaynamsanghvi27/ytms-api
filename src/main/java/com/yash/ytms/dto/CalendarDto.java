package com.yash.ytms.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import com.yash.ytms.domain.YtmsUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDto {

	
	private Long id;
	private String title;
	private String start;
	private String end;
	private YtmsUser scheduleUser;
	private LocalDate start_date;
	private LocalDate end_date;
	private LocalTime start_time;
	private LocalTime end_time;
	private Long number_of_week_days;


}
