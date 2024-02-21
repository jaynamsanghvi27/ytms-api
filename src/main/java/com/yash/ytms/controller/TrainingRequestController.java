package com.yash.ytms.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ytms.domain.TrainingRequestForm;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.services.IServices.IYtmsTraningRequestService;


@RestController
@RequestMapping("/register")
public class TrainingRequestController {

	@Autowired
    private IYtmsTraningRequestService traningRequestService;
	
	@PostMapping("/saveTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> saveTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
        return new ResponseEntity<>(traningRequestService.saveTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }
	
	@GetMapping("/getTrainingRequestForm")
	public List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal){
		return traningRequestService.getTrainingRequestForm(principal);
	}
	//updateTrainingRequestForm
	@PutMapping("/updateTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> updateTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
        return new ResponseEntity<>(traningRequestService.updateTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }
}
