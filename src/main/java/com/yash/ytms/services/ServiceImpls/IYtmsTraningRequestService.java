package com.yash.ytms.services.IServices;

import java.security.Principal;
import java.util.List;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;

public interface IYtmsTraningRequestService {

	ResponseWrapperDto saveTrainingRequestForm(TrainingRequestFormDto formDto);
	
	ResponseWrapperDto approveTrainingRequestForm(TrainingRequestFormDto formDto);

	ResponseWrapperDto declineTrainingRequestForm(TrainingRequestFormDto formDto);

	List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal);
}
