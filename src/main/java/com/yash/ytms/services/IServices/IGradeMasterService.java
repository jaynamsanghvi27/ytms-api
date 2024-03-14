/**
 * 
 */
package com.yash.ytms.services.IServices;

import java.util.List;

import com.yash.ytms.dto.GradeMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;

/**
 * 
 */
public interface IGradeMasterService {
	public List<GradeMasterDto> getGradeMasterList();
	
	public GradeMasterDto createGradeMaster(GradeMasterDto userMasterDto);
	
	void gradeUpdateScheduler();

	public ResponseWrapperDto saveGrade(GradeMasterDto formDto);

}
