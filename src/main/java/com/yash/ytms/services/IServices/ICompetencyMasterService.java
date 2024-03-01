/**
 * 
 */
package com.yash.ytms.services.IServices;

import java.util.List;

import com.yash.ytms.dto.CompetencyMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TechnologyMasterDto;

/**
 * 
 */
public interface ICompetencyMasterService {
	public List<CompetencyMasterDto> getCompetencyMasterList();
	
	public CompetencyMasterDto createCompetencyMaster(CompetencyMasterDto competencyMasterDto);
	
	void competencyMasterUpdateScheduler();
	
	public ResponseWrapperDto saveCompetency(CompetencyMasterDto formDto);
}
