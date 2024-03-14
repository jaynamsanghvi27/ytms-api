/**
 * 
 */
package com.yash.ytms.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TechnologyMasterDto {

	private long id;
	
	private String name;
	
	private String shortDescription;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private Boolean status = Boolean.TRUE;
}
