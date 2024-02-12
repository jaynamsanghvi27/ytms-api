package com.yash.ytms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.dto.NominationData;
import com.yash.ytms.services.IServices.INominationService;


@RestController
public class NominationUploadController {
	
	@Autowired
	private INominationService iNominationUploadService;
	
	 @PostMapping("/register/upload")
	    public ResponseEntity<String> bulkUpload(@RequestParam("file") MultipartFile file) {
		System.out.println("FileName : "+file.getOriginalFilename());
		try {
            List<NominationData> nominationUploadDataList = iNominationUploadService.parseExcel(file);
            
            
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
        	e.printStackTrace();
            return ResponseEntity.badRequest().body("Error uploading file: " + e.getMessage());
        }
	 }
}


