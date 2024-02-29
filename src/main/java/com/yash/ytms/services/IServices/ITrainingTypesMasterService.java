/**
 * 
 */
package com.yash.ytms.services.IServices;

import java.util.List;

import com.yash.ytms.dto.TrainingTypesMasterDto;

/**
 * 
 */
public interface ITrainingTypesMasterService {

	public List<TrainingTypesMasterDto> getTrainingTypesMasterList();
	
	public TrainingTypesMasterDto createTrainingMaster(TrainingTypesMasterDto trainingMasterDto);
	
	void trainingUpdateScheduler();
}
