/**
 * 
 */
package com.yash.ytms.domain;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.SQLDelete;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class CompetencyMaster {
	
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
	
	@NotEmpty(message = "Training name is mandatory")

	@Column(unique = false, nullable = false)

	private String name;
	
	private Boolean status = Boolean.TRUE;
}
