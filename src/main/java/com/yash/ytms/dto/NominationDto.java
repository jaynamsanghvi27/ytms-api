package com.yash.ytms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NominationDto {
	private String emp_id;
	private String emp_name;
	private String emp_mail_id;
	private String grade;
	private String skill;
	private String current_allocation;
	private String project;
	private String current_location;
	private Long trainingId;
	
}
