/**
 * 
 */
package com.yash.ytms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
