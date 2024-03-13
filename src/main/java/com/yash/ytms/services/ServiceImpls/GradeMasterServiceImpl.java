/**
 * 
 */
package com.yash.ytms.services.ServiceImpls;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.yash.ytms.domain.GradeMaster;
import com.yash.ytms.dto.GradeMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.exception.ApplicationException;
import com.yash.ytms.repository.GradeMasterRepository;
import com.yash.ytms.services.IServices.IGradeMasterService;

/**
 * 
 */
@Service
public class GradeMasterServiceImpl implements IGradeMasterService {
	
	 @Autowired
	 private GradeMasterRepository gradeMasterRepository;
	 
	 @Autowired
	 private ModelMapper modelMapper;
	 
	 @Override
	 public List<GradeMasterDto> getGradeMasterList(){
		 return modelMapper.map(gradeMasterRepository.findAll(),List.class);
	 }
	 
	 @Override
	    public GradeMasterDto createGradeMaster(GradeMasterDto GradeMasterDto) {
		 GradeMaster GradeMaster = null;

	        if (ObjectUtils.isNotEmpty(GradeMasterDto)) {
	        	GradeMaster = this
	                    .modelMapper
	                    .map(GradeMasterDto, GradeMaster.class);

	        	GradeMaster = this
	                    .gradeMasterRepository
	                    .save(GradeMaster);

	            GradeMasterDto = this
	                    .modelMapper
	                    .map(GradeMaster, GradeMasterDto.class);
	        } else {
	            throw new ApplicationException("Grade Master are empty or null");
	        }

	        return GradeMasterDto;
	    }
	 
	 @Override
	    @Async
	    @Scheduled(initialDelay = 1000, fixedDelay = 1800000)
	    public void gradeUpdateScheduler() {
	        List<GradeMasterDto> allGrades = new ArrayList<>();
	        allGrades = this
	                .getGradeMasterList();

	        if (allGrades.isEmpty()) {

	            allGrades.add(new GradeMasterDto(1, "AE1", true));
	            allGrades.add(new GradeMasterDto(2, "AE2", true));
	            allGrades.add(new GradeMasterDto(3, "AE3", true));
	            allGrades.add(new GradeMasterDto(4, "AE4", true));
	            allGrades.add(new GradeMasterDto(5, "C", true));
	            allGrades.add(new GradeMasterDto(6, "T", true));
	            allGrades.add(new GradeMasterDto(7, "E1", true));
	            allGrades.add(new GradeMasterDto(8, "E2", true));
	            allGrades.add(new GradeMasterDto(9, "E3", true));
	            allGrades.add(new GradeMasterDto(10, "E4", true));
	            allGrades.add(new GradeMasterDto(11, "E5", true));
	            allGrades.add(new GradeMasterDto(12, "E6", true));
	            allGrades.add(new GradeMasterDto(13, "E7", true));
	            allGrades.add(new GradeMasterDto(14, "E8", true));
	            
	            allGrades.forEach(this :: createGradeMaster);
	        }
	    }

	

	public ResponseWrapperDto saveGrade(GradeMasterDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		GradeMaster master = null;
		if (formDto != null) {
			try {
				master = modelMapper.map(formDto, GradeMaster.class);
				if (ObjectUtils.isNotEmpty(master)) {
					master.setStatus(true);
					responseWrapperDto.setData(gradeMasterRepository.save(master));
					responseWrapperDto.setMessage("Data Save Successfully..");
				} else {
					responseWrapperDto.setMessage("transection fail !");
				}
			} catch (Exception e) {
				responseWrapperDto.setMessage("unable to save data !");
			}

		} else {
			responseWrapperDto.setMessage("Request Form is empty !");

		}
		return responseWrapperDto;
	}
}
