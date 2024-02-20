package com.yash.ytms.services.IServices;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;

public interface IYtmsTraningRequestService {

	ResponseWrapperDto saveTrainingRequestForm(TrainingRequestFormDto formDto);
}
