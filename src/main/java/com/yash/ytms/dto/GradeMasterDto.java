/**
 * 
 */
package com.yash.ytms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeMasterDto {
	

	private long id;

	private String name;
	
	private Boolean status = Boolean.TRUE;
}
