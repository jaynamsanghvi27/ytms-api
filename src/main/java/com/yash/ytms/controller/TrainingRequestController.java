package com.yash.ytms.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.yash.ytms.services.IServices.IYtmsTraningRequestService;
import com.yash.ytms.services.IServices.IYtmsUserService;
import com.yash.ytms.util.ResponseMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.yash.ytms.dto.NominationDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.dto.TrfWithNominationDto;
import com.yash.ytms.dto.YtmsUserDto;
import com.yash.ytms.services.IServices.IYtmsTraningRequestService;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/register")
public class TrainingRequestController {
    //getTrainingRequestFormById

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    @Autowired
    private IYtmsTraningRequestService traningRequestService;
    
    @Autowired
    private IYtmsUserService userService;

    final Logger LOGGER = LoggerFactory.getLogger(TrainingRequestController.class);
    
    @PostMapping("/saveTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> saveTrainingRequestForm(@RequestBody TrfWithNominationDto trfNominationDto) {
    	LOGGER.info("Saving training request form");
        return new ResponseEntity<>(traningRequestService.saveTrainingRequestForm(trfNominationDto), HttpStatus.OK);
    }

    @GetMapping("/getTrainingRequestForm")
    public List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal) {
    	LOGGER.info("Getting training request form");
        return traningRequestService.getTrainingRequestForm(principal);
    }

    //updateTrainingRequestForm
    @PutMapping("/updateTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> approveTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
    	LOGGER.info("Updating training request form");
    	return new ResponseEntity<>(traningRequestService.approveTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }

    @PutMapping("/decline-trf")
    public ResponseEntity<ResponseWrapperDto> declineTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
    	LOGGER.info("Declining training request form");
    	return new ResponseEntity<>(traningRequestService.declineTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }

    @GetMapping("/getTrainingRequestFormById/{trainingID}")
    public TrainingRequestFormDto getTrainingRequestForm(@PathVariable long trainingID) {
    	LOGGER.info("Getting training request form by Id");
    	return traningRequestService.getTrainingRequestFormById(trainingID);
    }

    @PutMapping("/editTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> editTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
    	LOGGER.info("Editing training request form");
    	return new ResponseEntity<>(traningRequestService.editTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }

    @PostMapping(value = "/upload", headers = ("content-type=multipart/*"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (hasExcelFormat(file)) {
            try {
                traningRequestService.uploadFile(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                LOGGER.info("Uploaded the file successfully: " + file.getOriginalFilename());
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                LOGGER.error("Could not upload the file: " + file.getOriginalFilename() + "!");
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        LOGGER.info("Please upload an excel file!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    @GetMapping("/getFileName")
    public List<String> getFileName(Principal principal) {
        return traningRequestService.getFileName();
    }
    
    @GetMapping("/getTrainerList")
    public List<YtmsUserDto> getTrainerList() {
        return userService.getAllTrainers();
    }
    
    @GetMapping("/getRequesterList")
    public List<YtmsUserDto> getRequesterList() {
        return userService.findByUserRoleId(501L);
    }
}
