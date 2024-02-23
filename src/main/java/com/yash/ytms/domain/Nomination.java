package com.yash.ytms.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nominations")
public class Nomination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nomination_id", nullable = false)
	private Integer id;
	
	private String emp_id;
	private String emp_name;
	private String emp_mail_id;
	private String grade;
	private String skill;
	private String current_allocation;
	private String project;
	private String current_location;
	private Long training_id;
	
}
