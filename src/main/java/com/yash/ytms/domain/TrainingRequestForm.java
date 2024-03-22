package com.yash.ytms.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.yash.ytms.constants.UserAccountStatusTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@SQLDelete(sql = "UPDATE Training SET Status = true WHERE id=?")
@Table(name = "Training_Managment")
@Entity
public class TrainingRequestForm {

	/** Unique identifier for the training. */

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	/** Name used to identify the training. */

	@Size(min = 3, max = 25, message = "Training identifier name should be between 3 to 25 characters only")

	private String trainingIdentifierName;

	/** Name of the training. */

	@NotEmpty(message = "Training name is mandatory")

	@Column(unique = false, nullable = false)

	private String trainingName;

	/** Description of the training. */

	@NotBlank(message = "Training description is mandatory")

	private String trainingDescription;

	/** Start date of the training. */

	@NotNull(message = "Start date cannot be Null")

	@JsonFormat(pattern = "yyyy-MM-dd")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	private Date startDate;

	/** End date of the training. */

	@JsonFormat(pattern = "yyyy-MM-dd")

	@NotNull(message = "End date cannot be null")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	private Date endDate;

	/** Actual start date of the training. */

	@JsonFormat(pattern = "yyyy-MM-dd")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	private Date actualStartDate;

	/** Actual end date of the training. */

	@JsonFormat(pattern = "yyyy-MM-dd")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	private Date actualEndDate;

	/** Date when the training was created. */

	@JsonFormat(pattern = "yyyy-MM-dd")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	@CreatedDate

	private Date createdAt;

	/** Date when the training was last updated. */

	@JsonFormat(pattern = "yyyy-MM-dd")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	@LastModifiedDate

	private Date updatedAt;

	/** Status of the training. */

	private String status;

	/** Transient field for storing user name. */

//	@Transient

	private String userName;

	@Column(columnDefinition="String default 'Planned'")
	private String trainingStatus;

	
	private int noOfParticipant;

	//to be used only in case of request declined
	private String declinedMessage;
	
	@Formula(value = "(SELECT COUNT(*) FROM Nominations v WHERE v.training_id=id)")
	private int noOfActualParticipant;

	/** Pre-persist action to set the created date. */

	@PrePersist

	public void onCreate() {

		this.createdAt = new Date();

	}

	/** Pre-update action to set the updated date. */

	@PreUpdate

	public void onUpdate() {

		this.updatedAt = new Date();

	}

}
