package com.yash.ytms.services.ServiceImpls;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.yash.ytms.constants.UserAccountStatusTypes;
import com.yash.ytms.domain.FileName;
import com.yash.ytms.domain.YtmsUser;
import com.yash.ytms.repository.StoreFileNameInRepository;
import com.yash.ytms.repository.YtmsUserRepository;
import com.yash.ytms.services.IServices.IYtmsTraningRequestService;
import com.yash.ytms.util.EmailUtil;
import com.yash.ytms.util.ResponseMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.yash.ytms.constants.UserAccountStatusTypes;
import com.yash.ytms.constants.UserRoleTypes;
//import com.yash.ytms.constants.StatusTypes;
import com.yash.ytms.domain.TrainingRequestForm;
import com.yash.ytms.dto.NominationDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.dto.TrfWithNominationDto;
import com.yash.ytms.repository.TrainingRequestRepository;
import com.yash.ytms.security.userdetails.CustomUserDetails;
import com.yash.ytms.security.userdetails.CustomUserDetailsServiceImpl;
import com.yash.ytms.services.IServices.INominationService;
import com.yash.ytms.services.IServices.IYtmsTraningRequestService;
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
    private INominationService iNominationService;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private YtmsUserRepository ytmsUserRepository;
    @Autowired
    private StoreFileNameInRepository storeFileNameInRepository;

    @Value("${attachFile.filePath}")
    private String filePath;

    @Override
    public ResponseWrapperDto saveTrainingRequestForm(TrfWithNominationDto formDto) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        TrainingRequestForm trainingRequestForm = null;
        if (formDto != null && formDto.getTrainingRequestFormDto() != null) {
            try {
                trainingRequestForm = modelMapper.map(formDto.getTrainingRequestFormDto(), TrainingRequestForm.class);
                if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                    trainingRequestForm.setStatus(UserAccountStatusTypes.PENDING.toString());
                    trainingRequestForm = requestRepository.save(trainingRequestForm);
                    saveNomination(formDto.getNominationList(), trainingRequestForm.getId());
                    responseWrapperDto.setMessage("Data Save Successfully..");
                    List<String> usersList = this.ytmsUserRepository.findAllTechnicalManager();
                    if (ObjectUtils.isNotEmpty(usersList)) {
                        try {
                            emailUtil.sendNotificationMailForTechnicalManage(usersList, trainingRequestForm.getUserName());
                        } catch (MessagingException ex) {
                            responseWrapperDto.setMessage("unable send mail to technical manager ! " + ex.getMessage());
                        }
                    }

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
                        oldformDto.setTrainer(formDto.getTrainer());
                        oldformDto.setStatus(UserAccountStatusTypes.APPROVED.toString());
                        //oldformDto.setDeclinedMessage("NA");
                        trainingRequestForm = modelMapper.map(oldformDto, TrainingRequestForm.class);
                        if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                            requestRepository.save(trainingRequestForm);
                            responseWrapperDto.setMessage("Data Save Successfully..");
                            emailUtil.sendNotificationMailForRequestApproved(trainingRequestForm.getUserName(), formDto.getFileName(),oldformDto);
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
        } else {
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
                        emailUtil.sendNotificationMailForRequestReject(trainingRequestForm.getUserName(),trainingRequestForm);
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
            System.out.println(forms);
            requestFormList = modelMapper.map(forms, List.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return requestFormList;
    }

    @Override
    public TrainingRequestFormDto getTrainingRequestFormById(long trainingId) {
        // TODO Auto-generated method stub
        TrainingRequestFormDto requestForm = null;
        TrainingRequestForm form = new TrainingRequestForm();
        try {

            form = requestRepository.findById(trainingId).get();

            requestForm = modelMapper.map(form, TrainingRequestFormDto.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return requestForm;
    }

    @Override
    public ResponseWrapperDto editTrainingRequestForm(TrainingRequestFormDto formDto) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        TrainingRequestForm trainingRequestForm = null;
        Optional<TrainingRequestForm> getTraining = null;
        if (formDto != null) {
            try {
                getTraining = requestRepository.findById(formDto.getId());
                trainingRequestForm = modelMapper.map(formDto, TrainingRequestForm.class);
                if (getTraining.isPresent()) {
                    trainingRequestForm.setCreatedAt(getTraining.get().getCreatedAt());
                }
                if (ObjectUtils.isNotEmpty(trainingRequestForm)) {
                    trainingRequestForm.setStatus(UserAccountStatusTypes.PENDING.toString());
                    requestRepository.save(trainingRequestForm);
                    responseWrapperDto.setMessage("Data Updated Successfully..");
                } else {
                    responseWrapperDto.setMessage("transection fail !");
                }
            } catch (Exception e) {
                responseWrapperDto.setMessage("unable to update training data !");
            }

        } else {
            responseWrapperDto.setMessage("Training Request Form is empty !");

        }
        return responseWrapperDto;
    }

    private List<NominationDto> saveNomination(List<NominationDto> nominationDtos, Long trainingId) {
        nominationDtos.stream().forEach(e -> e.setTrainingId(trainingId));
        nominationDtos.stream().forEach(e -> {
            iNominationService.saveNomination(e);
        });
        return nominationDtos;
    }

    public ResponseWrapperDto uploadFile(MultipartFile file) {
        ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
        String fileName = file.getOriginalFilename();
        FileName fName = new FileName();
        try {
            File f = new File(filePath);
            if (f.exists() && f.isDirectory()) {
                file.transferTo(new File(filePath + fileName));
            } else {
                f.mkdir();
                file.transferTo(new File(filePath + fileName));
            }

            if (fileName.indexOf(".") > 0)
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
            fName.setFileName(fileName);
            responseWrapperDto.setMessage("File upload successfully ");
            List<String> listOfFileName = storeFileNameInRepository.findAllFileName();
            if (!listOfFileName.contains(fileName)) {
                storeFileNameInRepository.save(fName);
            }
        } catch (IOException e) {
            responseWrapperDto.setMessage("Fail to upload excel in to folder: " + e.getMessage());

        }
        return responseWrapperDto;
    }

    public List<String> getFileName() {
        return storeFileNameInRepository.findAllFileName();
    }
}
