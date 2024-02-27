/**
 * 
 */
package com.yash.ytms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ytms.dto.CompetencyMasterDto;
import com.yash.ytms.services.IServices.ICompetencyMasterService;

/**
 * 
 */
@RestController
public class CompetencyMasterController {
	
	@Autowired
	ICompetencyMasterService competencyMasterService;
	
	@GetMapping("/getCompetencyMasterList")
	public List<CompetencyMasterDto> getCompetencyMasterList() {
		return competencyMasterService.getCompetencyMasterList();
	}
}
