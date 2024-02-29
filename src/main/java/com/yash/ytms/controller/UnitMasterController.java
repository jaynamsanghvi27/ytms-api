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
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.dto.UnitMasterDto;
import com.yash.ytms.services.IServices.IUnitMasterService;

/**
 * 
 */
@RestController
public class UnitMasterController {
	@Autowired
	IUnitMasterService unitMasterService;
	
	@GetMapping("/getUnitMasterList")
	public List<UnitMasterDto> getUnitMasterList(){
		return unitMasterService.getUnitMasterList();
	}
	
	@PostMapping("/add-unit")
	public ResponseEntity<ResponseWrapperDto> saveTrainingRequestForm(@RequestBody UnitMasterDto dto) {
        return new ResponseEntity<>(unitMasterService.saveUnit(dto), HttpStatus.OK);
    }
}
