package com.yash.ytms.services.IServices;

import java.io.File;
import java.security.Principal;
import java.util.List;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import jakarta.mail.Multipart;
import org.springframework.web.multipart.MultipartFile;

public interface IYtmsTraningRequestService {

	ResponseWrapperDto saveTrainingRequestForm(TrainingRequestFormDto formDto);
	
	ResponseWrapperDto approveTrainingRequestForm(TrainingRequestFormDto formDto);
	ResponseWrapperDto uploadFile( MultipartFile file);
	ResponseWrapperDto declineTrainingRequestForm(TrainingRequestFormDto formDto);

	List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal);
}
