package com.yash.ytms.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		System.out.println("on Controller");
        return new ResponseEntity<>(traningRequestService.saveTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }
}
