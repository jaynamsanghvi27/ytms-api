package com.yash.ytms.dto;

import java.util.Date;

import com.yash.ytms.constants.UserAccountStatusTypes;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

	private String status = UserAccountStatusTypes.PENDING.toString();

	private String userName;

	//to be used only in case of request declined
	private String declinedMessage;
	
	private int noOfParticipant;

	private String fileName;
	
	private int noOfActualParticipant;
}
