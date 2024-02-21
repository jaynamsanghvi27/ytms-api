package com.yash.ytms.services.IServices;

import java.security.Principal;
import java.util.List;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;

public interface IYtmsTraningRequestService {

	ResponseWrapperDto saveTrainingRequestForm(TrainingRequestFormDto formDto);
	
	ResponseWrapperDto updateTrainingRequestForm(TrainingRequestFormDto formDto);
	
	List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal);
}
