package com.yash.ytms.services.IServices;

import java.security.Principal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.dto.TrfWithNominationDto;

public interface IYtmsTraningRequestService {

    ResponseWrapperDto saveTrainingRequestForm(TrfWithNominationDto formDto);

    ResponseWrapperDto approveTrainingRequestForm(TrainingRequestFormDto formDto);

    ResponseWrapperDto declineTrainingRequestForm(TrainingRequestFormDto formDto);

    List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal);

    TrainingRequestFormDto getTrainingRequestFormById(long trainingId);

    ResponseWrapperDto editTrainingRequestForm(TrainingRequestFormDto formDto);

    ResponseWrapperDto uploadFile(MultipartFile file);

    List<String> getFileName();
}
