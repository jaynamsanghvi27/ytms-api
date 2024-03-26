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

import com.yash.ytms.dto.CompetencyMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.services.IServices.ICompetencyMasterService;

/**
 * 
 */
@RestController
public class CompetencyMasterController {
	
	final Logger LOGGER = LoggerFactory.getLogger(CompetencyMasterController.class);
	
	@Autowired
	ICompetencyMasterService competencyMasterService;

	@GetMapping("/getCompetencyMasterList")
	public List<CompetencyMasterDto> getCompetencyMasterList() {
		LOGGER.info("Getting competency master list");
		return competencyMasterService.getCompetencyMasterList();
	}
	
	@PostMapping("/add-competency")
	public ResponseEntity<ResponseWrapperDto> saveCompetency(@RequestBody CompetencyMasterDto dto) {
		LOGGER.info("Adding new competency");
        return new ResponseEntity<>(competencyMasterService.saveCompetency(dto), HttpStatus.OK);
    }
}
