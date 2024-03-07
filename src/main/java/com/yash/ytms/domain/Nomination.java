package com.yash.ytms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Nominations")
public class Nomination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
