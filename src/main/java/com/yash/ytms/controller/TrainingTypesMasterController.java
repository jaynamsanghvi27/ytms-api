/**
 * 
 */
package com.yash.ytms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
