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
	
	@GetMapping("/getLocationMasterList")
	public List<LocationMasterDto> getLocationMasterList(){
		return locationMasterService.getLocationMasterList();
	}
	
	@PostMapping("/add-location")
	public ResponseEntity<ResponseWrapperDto> saveUnit(@RequestBody LocationMasterDto dto) {
        return new ResponseEntity<>(locationMasterService.saveLocation(dto), HttpStatus.OK);
    }
}
