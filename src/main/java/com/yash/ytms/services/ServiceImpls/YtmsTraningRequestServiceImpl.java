package com.yash.ytms.services.ServiceImpls;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yash.ytms.constants.UserAccountStatusTypes;
import com.yash.ytms.services.IServices.IYtmsTraningRequestService;
import com.yash.ytms.util.EmailUtil;
import com.yash.ytms.util.ResponseMessage;
import jakarta.mail.Multipart;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.yash.ytms.constants.UserRoleTypes;
//import com.yash.ytms.constants.StatusTypes;
import com.yash.ytms.domain.TrainingRequestForm;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.repository.TrainingRequestRepository;
import com.yash.ytms.security.userdetails.CustomUserDetails;
import com.yash.ytms.security.userdetails.CustomUserDetailsServiceImpl;
import org.springframework.web.multipart.MultipartFile;


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


    @Autowired
    private EmailUtil emailUtil;

    @Override
    public ResponseWrapperDto saveTrainingRequestForm(TrainingRequestFormDto formDto) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        TrainingRequestForm trainingRequestForm = null;
        if (formDto != null) {
            try {
                trainingRequestForm = modelMapper.map(formDto, TrainingRequestForm.class);
                if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                    trainingRequestForm.setStatus(UserAccountStatusTypes.PENDING.toString());
                    requestRepository.save(trainingRequestForm);
                    responseWrapperDto.setMessage("Data Save Successfully..");
                    emailUtil.sendNotificationMailForTechnicalManage(trainingRequestForm.getUserName());
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

    public ResponseWrapperDto uploadFile(MultipartFile file) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\Internal Project\\" + fileName));
            responseWrapperDto.setMessage("File upload successfully ");
        } catch (IOException e) {
            responseWrapperDto.setMessage("Fail to upload excel in to folder: " + e.getMessage());

        }
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto approveTrainingRequestForm(TrainingRequestFormDto formDto) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        TrainingRequestForm trainingRequestForm = null;

        if (formDto != null) {
            if (formDto.getActualEndDate().after(formDto.getActualStartDate())) {
                try {
                    Optional<TrainingRequestForm> oldformDtoOpt = requestRepository.findById(formDto.getId());
                    if (oldformDtoOpt.isPresent()) {
                        TrainingRequestForm oldformDto = oldformDtoOpt.get();
                        oldformDto.setActualStartDate(formDto.getActualStartDate());
                        oldformDto.setActualEndDate(formDto.getActualEndDate());
                        oldformDto.setStatus(UserAccountStatusTypes.APPROVED.toString());
                        oldformDto.setDeclinedMessage("NA");
                        trainingRequestForm = modelMapper.map(oldformDto, TrainingRequestForm.class);
                        if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                            requestRepository.save(trainingRequestForm);
                            responseWrapperDto.setMessage("Data Save Successfully..");
                            emailUtil.sendNotificationMailForRequestApproved(trainingRequestForm.getUserName(), formDto.getFileName());
                        } else {
                            responseWrapperDto.setMessage("transection fail !");
                        }
                    }

                } catch (Exception e) {
                    responseWrapperDto.setMessage("unable to save training data !");
                }

            } else {
                responseWrapperDto.setMessage("can not select End date after start date! ");
            }
        }else {
            responseWrapperDto.setMessage("Training Request Form is empty !");
        }
        return responseWrapperDto;
    }

    @Override
    public ResponseWrapperDto declineTrainingRequestForm(TrainingRequestFormDto formDto) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        TrainingRequestForm trainingRequestForm = null;
        if (formDto != null) {
            try {
                Optional<TrainingRequestForm> oldformDtoOpt = requestRepository.findById(formDto.getId());
                if (oldformDtoOpt.isPresent()) {
                    TrainingRequestForm oldformDto = oldformDtoOpt.get();
                    oldformDto.setStatus(UserAccountStatusTypes.DECLINED.toString());
                    trainingRequestForm = modelMapper.map(oldformDto, TrainingRequestForm.class);
                    trainingRequestForm.setDeclinedMessage(formDto.getDeclinedMessage());
                    if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                        requestRepository.save(trainingRequestForm);
                        responseWrapperDto.setMessage("Data Save Successfully..");

                        emailUtil.sendNotificationMailForRequestReject(trainingRequestForm.getUserName());
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
            if (UserRoleTypes.ROLE_TECHNICAL_MANAGER.toString().equals(role)) {
                forms = requestRepository.findAll();
            } else {
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
