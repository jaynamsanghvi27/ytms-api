/**
 * 
 */
package com.yash.ytms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingTypesMasterDto;
import com.yash.ytms.services.IServices.ITrainingTypesMasterService;

/**
 * 
 */
@RestController
public class TrainingTypesMasterController {

	@Autowired
	ITrainingTypesMasterService iTrainingTypesMasterService;
	
	@GetMapping("/getTrainingTypesMasterList")
	public List<TrainingTypesMasterDto> getTrainingTypesMasterList(){
		return iTrainingTypesMasterService.getTrainingTypesMasterList();
	} 
	
	@PostMapping("/add-training-type")
	public ResponseEntity<ResponseWrapperDto> saveTrainingType(@RequestBody TrainingTypesMasterDto dto) {
        return new ResponseEntity<>(iTrainingTypesMasterService.saveTrainingType(dto), HttpStatus.OK);
    }
	
}
