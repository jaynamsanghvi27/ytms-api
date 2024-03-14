/**
 * 
 */
package com.yash.ytms.domain;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
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
@SQLDelete(sql = "UPDATE technology_master SET Status = true WHERE id=?")
@Table(name = "technology_master")
@Entity
public class TechnologyMaster {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
	
	@NotEmpty(message = "Training name is mandatory")

	@Column(unique = false, nullable = false)

	private String name;
	
	
	private String shortDescription;
	
	@JsonFormat(pattern = "yyyy-MM-dd")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	@CreatedDate

	private Date createdAt;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd")

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	@LastModifiedDate

	private Date updatedAt;
	
	private Boolean status = Boolean.TRUE;
}
