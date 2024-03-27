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

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.UnitMasterDto;
import com.yash.ytms.services.IServices.IUnitMasterService;

/**
 * 
 */
@RestController
public class UnitMasterController {
	@Autowired
	IUnitMasterService unitMasterService;
	
	final Logger LOGGER = LoggerFactory.getLogger(UnitMasterController.class);
	
	@GetMapping("/getUnitMasterList")
	public List<UnitMasterDto> getUnitMasterList(){
		LOGGER.info("Getting unit list");
		return unitMasterService.getUnitMasterList();
	}
	
	@PostMapping("/add-unit")
	public ResponseEntity<ResponseWrapperDto> saveUnit(@RequestBody UnitMasterDto dto) {
		LOGGER.info("Adding unit ");
        return new ResponseEntity<>(unitMasterService.saveUnit(dto), HttpStatus.OK);
    }
}
