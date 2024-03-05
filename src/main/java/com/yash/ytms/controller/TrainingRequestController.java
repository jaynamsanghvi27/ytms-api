package com.yash.ytms.controller;

import java.security.Principal;
import java.util.List;

import com.yash.ytms.services.IServices.IYtmsTraningRequestService;
import com.yash.ytms.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingRequestFormDto;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/register")
public class TrainingRequestController {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	@Autowired
    private IYtmsTraningRequestService traningRequestService;
	
	@PostMapping("/saveTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> saveTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
        return new ResponseEntity<>(traningRequestService.saveTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }
	
	@GetMapping("/getTrainingRequestForm")
	public List<TrainingRequestFormDto> getTrainingRequestForm(Principal principal){
		return traningRequestService.getTrainingRequestForm(principal);
	}
	//updateTrainingRequestForm
	@PutMapping("/updateTrainingRequestForm")
    public ResponseEntity<ResponseWrapperDto> approveTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
        return new ResponseEntity<>(traningRequestService.approveTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }

	@PutMapping("/decline-trf")
    public ResponseEntity<ResponseWrapperDto> declineTrainingRequestForm(@RequestBody TrainingRequestFormDto trainingRequestFormDto) {
        return new ResponseEntity<>(traningRequestService.declineTrainingRequestForm(trainingRequestFormDto), HttpStatus.OK);
    }
    //@PostMapping("/upload")
    @PostMapping(value = "/upload", headers = ("content-type=multipart/*"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (hasExcelFormat(file)) {
            try {
                traningRequestService.uploadFile(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
    public static boolean hasExcelFormat(MultipartFile file) {

        if(!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
}
