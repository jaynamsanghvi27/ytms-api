package com.yash.ytms.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingRequestFormDto {

	private long id;
	
	private String trainingIdentifierName;
	
	private String trainingName;
	
	private String trainingDescription;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date actualStartDate;
	
	private Date actualEndDate;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private boolean status = Boolean.FALSE;
	
	private String userName;
	
	private int noOfParticipant;
	
	
}
