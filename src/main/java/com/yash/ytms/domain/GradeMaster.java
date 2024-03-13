/**
 * 
 */
package com.yash.ytms.domain;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class GradeMaster {
	
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
	
	@NotEmpty(message = "Grade name is mandatory")

	@Column(unique = false, nullable = false)

	private String name;
	
	private Boolean status = Boolean.TRUE;
}
