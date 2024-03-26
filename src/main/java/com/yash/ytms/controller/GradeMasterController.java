/**
 * 
 */
package com.yash.ytms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ytms.dto.GradeMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.services.IServices.IGradeMasterService;

/**
 * 
 */
@RestController
public class GradeMasterController {
	@Autowired
	IGradeMasterService gradeMasterService;
	
	final Logger LOGGER = LoggerFactory.getLogger(GradeMasterController.class);
	
	@GetMapping("/getGradeMasterList")
	public List<GradeMasterDto> getGradeMasterList(){
		LOGGER.info("Getting grade master list");
		return gradeMasterService.getGradeMasterList();
	}
	
	@PostMapping("/add-grade")
	public ResponseEntity<ResponseWrapperDto> saveUnit(@RequestBody GradeMasterDto dto) {
		LOGGER.info("Adding grade");
        return new ResponseEntity<>(gradeMasterService.saveGrade(dto), HttpStatus.OK);
    }
}
