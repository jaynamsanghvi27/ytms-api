package com.yash.ytms.services.ServiceImpls;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yash.ytms.constants.UserRoleTypes;
//import com.yash.ytms.constants.StatusTypes;
import com.yash.ytms.domain.TrainingRequestForm;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.repository.TrainingRequestRepository;
import com.yash.ytms.security.userdetails.CustomUserDetails;
import com.yash.ytms.security.userdetails.CustomUserDetailsServiceImpl;
import com.yash.ytms.services.IServices.IYtmsTraningRequestService;


@Service
public class YtmsTraningRequestServiceImpl implements IYtmsTraningRequestService {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private TrainingRequestRepository requestRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsServiceImpl userDetails;
	
	@Override
	public ResponseWrapperDto saveTrainingRequestForm(TrainingRequestFormDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		TrainingRequestForm trainingRequestForm = null;
        if (formDto != null) {
            try {
            	trainingRequestForm = modelMapper.map(formDto, TrainingRequestForm.class);
                if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                	requestRepository.save(trainingRequestForm);
                    responseWrapperDto.setMessage("Data Save Successfully..");
                } else {
                    responseWrapperDto.setMessage("transection fail !");
                }
            } catch (Exception e) {
                responseWrapperDto.setMessage("unable to save training data !");
            }

        } else {
            responseWrapperDto.setMessage("Training Request Form is empty !");

        }
        return responseWrapperDto;
	}
	
	@Override
	public ResponseWrapperDto updateTrainingRequestForm(TrainingRequestFormDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		TrainingRequestForm trainingRequestForm = null;
        if (formDto != null) {
            try {
            	Optional<TrainingRequestForm> oldformDtoOpt = requestRepository.findById(formDto.getId());
            	if(oldformDtoOpt.isPresent()) {
            		TrainingRequestForm oldformDto = oldformDtoOpt.get();
            		oldformDto.setActualStartDate(formDto.getActualStartDate());
            		oldformDto.setActualEndDate(formDto.getActualEndDate());
            		oldformDto.setStatus(true);
            		trainingRequestForm = modelMapper.map(oldformDto, TrainingRequestForm.class);
                    if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                    	requestRepository.save(trainingRequestForm);
                        responseWrapperDto.setMessage("Data Save Successfully..");
                    } else {
                        responseWrapperDto.setMessage("transection fail !");
                    }
            	}
            	
            } catch (Exception e) {
                responseWrapperDto.setMessage("unable to save training data !");
            }

        } else {
            responseWrapperDto.setMessage("Training Request Form is empty !");

        }
        return responseWrapperDto;
	}
	
	@Override
	public List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal) {
		// TODO Auto-generated method stub
		List<TrainingRequestFormDto> requestFormList = null;
		List<TrainingRequestForm> forms = new ArrayList<TrainingRequestForm>();
		String userName = principal.getName();
		CustomUserDetails customUserDetails = this.userDetails.loadUserByUsername(userName);
		String role = customUserDetails.getGrantedAuthorities().getAuthority();
		try {
			if(UserRoleTypes.ROLE_TECHNICAL_MANAGER.toString().equals(role)) {
				forms = requestRepository.findAll();
			}else {
				forms = requestRepository.findByUserName(userName);
			}
			requestFormList = modelMapper.map(forms, List.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return requestFormList;
	}
	
	
}
