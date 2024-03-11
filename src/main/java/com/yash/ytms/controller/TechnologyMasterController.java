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
import com.yash.ytms.dto.TechnologyMasterDto;
import com.yash.ytms.services.IServices.ITechnologyMasterService;

/**
 * 
 */
@RestController
public class TechnologyMasterController {
	
	@Autowired
	ITechnologyMasterService technologyMasterService;
	
	@GetMapping("/getTechnologyMasterList")
	public List<TechnologyMasterDto> getTechnologyMasterList(){
		return technologyMasterService.getTechnologyMasterList();
	}
	
	@PostMapping("/add-technology")
	public ResponseEntity<ResponseWrapperDto> saveTechnology(@RequestBody TechnologyMasterDto dto) {
        return new ResponseEntity<>(technologyMasterService.saveTechnology(dto), HttpStatus.OK);
    }
	
}
