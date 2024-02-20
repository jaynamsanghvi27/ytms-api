package com.yash.ytms.services.ServiceImpls;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.yash.ytms.constants.StatusTypes;
import com.yash.ytms.domain.TrainingRequestForm;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.repository.TrainingRequestRepository;
import com.yash.ytms.services.IServices.IYtmsTraningRequestService;


@Service
public class YtmsTraningRequestServiceImpl implements IYtmsTraningRequestService {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private TrainingRequestRepository requestRepository;
	
	@Override
	public ResponseWrapperDto saveTrainingRequestForm(TrainingRequestFormDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		TrainingRequestForm trainingRequestForm = null;
        if (formDto != null) {
            try {
            	trainingRequestForm = modelMapper.map(formDto, TrainingRequestForm.class);
//                YtmsUser ytmsUser = this.userRepository.getUserByEmail(email);
                if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                	requestRepository.save(trainingRequestForm);
                    responseWrapperDto.setMessage("Data Save Successfully..");
//                    responseWrapperDto.setStatus(StatusTypes.SUCCESS.toString());
                } else {
                    responseWrapperDto.setMessage("transection fail !");
//                    responseWrapperDto.setStatus(StatusTypes.FAILED.toString());
                }
//
            } catch (Exception e) {
                responseWrapperDto.setMessage("unable to save training data !");
//                responseWrapperDto.setStatus(StatusTypes.FAILED.toString());
            }

        } else {
            responseWrapperDto.setMessage("Training Request Form is empty !");
//            responseWrapperDto.setStatus(StatusTypes.FAILED.toString());

        }
        return responseWrapperDto;
	}
}
