package com.yash.ytms.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ytms.dto.NominationDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.dto.TrfWithNominationDto;
import com.yash.ytms.services.IServices.IYtmsTraningRequestService;


@RestController
@RequestMapping("/register")
public class TrainingRequestController {
	//getTrainingRequestFormById

	@Autowired
    private IYtmsTraningRequestService traningRequestService;
	
	@PostMapping("/saveTrainingRequestForm")
	public ResponseEntity<ResponseWrapperDto> saveTrainingRequestForm(@RequestBody TrfWithNominationDto trfNominationDto) {
        return new ResponseEntity<>(traningRequestService.saveTrainingRequestForm(trfNominationDto), HttpStatus.OK);
    }
	
	@GetMapping("/getTrainingRequestForm")
	public List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal){
		return traningRequestService.getTrainingRequestForm(principal);
	}
	//updateTrainingRequestForm
	@PutMapping("/updateTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> approveTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
        return new ResponseEntity<>(traningRequestService.approveTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }	

	@PutMapping("/decline-trf")
    public ResponseEntity<ResponseWrapperDto> declineTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
        return new ResponseEntity<>(traningRequestService.declineTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }
	
	@GetMapping("/getTrainingRequestFormById/{trainingID}")
	public TrainingRequestFormDto getTrainingRequestForm(@PathVariable long trainingID){
		return traningRequestService.getTrainingRequestFormById(trainingID);
	}
	
	@PutMapping("/editTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> editTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
        return new ResponseEntity<>(traningRequestService.editTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }
}