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

import com.yash.ytms.dto.LocationMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.services.IServices.ILocationMasterService;

/**
 * 
 */
@RestController
public class LocationMasterController {
	@Autowired
	ILocationMasterService locationMasterService;
	
	final Logger LOGGER = LoggerFactory.getLogger(LocationMasterController.class);
	
	@GetMapping("/getLocationMasterList")
	public List<LocationMasterDto> getLocationMasterList(){
		LOGGER.info("Getting location master list");
		return locationMasterService.getLocationMasterList();
	}
	
	@PostMapping("/add-location")
	public ResponseEntity<ResponseWrapperDto> saveUnit(@RequestBody LocationMasterDto dto) {
		LOGGER.info("Adding location");
        return new ResponseEntity<>(locationMasterService.saveLocation(dto), HttpStatus.OK);
    }
}
