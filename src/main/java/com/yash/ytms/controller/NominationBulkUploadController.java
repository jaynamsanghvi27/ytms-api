package com.yash.ytms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.dto.NominationDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import com.yash.ytms.services.IServices.INominationService;

@RestController
public class NominationBulkUploadController {
	

	@Autowired
	private INominationService iNominationUploadService;
	
	final Logger LOGGER = LoggerFactory.getLogger(NominationBulkUploadController.class);
	
	 @PostMapping("/register/readExcel")
	    public List<NominationDto> bulkUpload(@RequestParam("file") MultipartFile file) {
		List<NominationDto> nominationUploadDataList =null;
		System.out.println("FileName : "+file.getOriginalFilename());
		try {
            nominationUploadDataList = iNominationUploadService.parseExcel(file);
            nominationUploadDataList.stream().forEach(System.out::println);
            LOGGER.info("uploading file");
            return nominationUploadDataList ;
        } catch (Exception e) {
        	e.printStackTrace();
        	LOGGER.error("Error i uploading file"+e);
            return nominationUploadDataList; 
        }
	 }
	 
	 
	 @PostMapping("/register/saveNomination")
	 public NominationDto saveNomination(@RequestBody NominationDto dto) {
		 LOGGER.info("Saving nomination");
		 return iNominationUploadService.saveNomination(dto);
	 }
	 
	 
	 @GetMapping("/register/getNominationListByTrainingId/{trainingId}")
	 public List<NominationDto> getNominationListByTrainingId(@PathVariable Long trainingId){
		 LOGGER.info("Getting nomination by training Id");
		 return iNominationUploadService.findNominationsByTrainingID(trainingId); 
	 }
	 
	 @GetMapping("/register/getNominationById/{nominationId}")
	 public NominationDto getNominationById(@PathVariable Long nominationId){
		 LOGGER.info("Getting nomination by nomination Id");
		 return iNominationUploadService.getNomiationById(nominationId);
	 }
	 
	 @PutMapping("/register/update-nomination")
	 public NominationDto updateNominationById(@RequestBody NominationDto dto) {
		 LOGGER.info("Updating nomination Id");
		 return iNominationUploadService.saveNomination(dto);
	 }
	 
	 @DeleteMapping("/register/deleteNominationById/{nominationId}")
	    public void deleteTraining(@PathVariable long nominationId) {
		 LOGGER.info("Deleting nomination Id");
	        iNominationUploadService.deleteNominationById(nominationId);
	        return;
	 }
	
}
