package com.yash.ytms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.dto.NominationDto;
import com.yash.ytms.services.IServices.INominationService;

@RestController
public class NominationBulkUploadController {
	

	@Autowired
	private INominationService iNominationUploadService;
	
	 @PostMapping("/register/upload")
	    public List<NominationDto> bulkUpload(@RequestParam("file") MultipartFile file) {
		List<NominationDto> nominationUploadDataList =null;
		System.out.println("FileName : "+file.getOriginalFilename());
		try {
            nominationUploadDataList = iNominationUploadService.parseExcel(file);
            nominationUploadDataList.stream().forEach(System.out::println);
            return nominationUploadDataList ;
        } catch (Exception e) {
        	e.printStackTrace();
            return nominationUploadDataList; 
        }
	 }
	 
	 @PostMapping("/register/saveNomination")
	 public NominationDto saveNomination(@RequestBody NominationDto dto) {
		 return iNominationUploadService.saveNomination(dto);
	 }
	 
	 
	 @GetMapping("/register/getNominationListByTrainingId/{trainingId}")
	 public List<NominationDto> getNominationListByTrainingId(@PathVariable Long trainingId){
		 return iNominationUploadService.findNominationsByTrainingID(trainingId); 
	 }
	 
	 @GetMapping("/register/getNominationById/{nominationId}")
	 public NominationDto getNominationById(@PathVariable Long nominationId){
		 return iNominationUploadService.getNomiationById(nominationId);
	 }
	 
	 @PutMapping("/register/update-nomination")
	 public NominationDto updateNominationById(@RequestBody NominationDto dto) {
		 return iNominationUploadService.saveNomination(dto);
	 }
	
}
