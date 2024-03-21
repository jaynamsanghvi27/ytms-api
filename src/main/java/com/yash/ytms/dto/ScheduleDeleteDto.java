package com.yash.ytms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.yash.ytms.domain.Calendar;
import com.yash.ytms.domain.YtmsUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDeleteDto {
	private Long id;
	private Calendar calendar;
	private YtmsUser scheduleUser;
	private Integer status;

}
