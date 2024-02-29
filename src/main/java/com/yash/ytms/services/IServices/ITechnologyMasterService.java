/**
 * 
 */
package com.yash.ytms.services.IServices;

import java.util.List;

import com.yash.ytms.dto.TechnologyMasterDto;

/**
 * 
 */
public interface ITechnologyMasterService {
	
	public List<TechnologyMasterDto> getTechnologyMasterList();
	
	public TechnologyMasterDto createTechnologyMaster(TechnologyMasterDto trainingMasterDto);
	
	void technologyUpdateScheduler();
}
