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

import com.yash.ytms.domain.LocationMaster;
import com.yash.ytms.dto.LocationMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.exception.ApplicationException;
import com.yash.ytms.repository.LocationMasterRepository;
import com.yash.ytms.services.IServices.ILocationMasterService;

/**
 * 
 */
@Service
public class LocationMasterServiceImpl implements ILocationMasterService {
	
	 @Autowired
	 private LocationMasterRepository locationMasterRepository;
	 
	 @Autowired
	 private ModelMapper modelMapper;
	 
	 @Override
	 public List<LocationMasterDto> getLocationMasterList(){
		 return modelMapper.map(locationMasterRepository.findAll(),List.class);
	 }
	 
	 @Override
	    public LocationMasterDto createLocationMaster(LocationMasterDto LocationMasterDto) {
		 LocationMaster LocationMaster = null;

	        if (ObjectUtils.isNotEmpty(LocationMasterDto)) {
	        	LocationMaster = this
	                    .modelMapper
	                    .map(LocationMasterDto, LocationMaster.class);

	        	LocationMaster = this
	                    .locationMasterRepository
	                    .save(LocationMaster);

	            LocationMasterDto = this
	                    .modelMapper
	                    .map(LocationMaster, LocationMasterDto.class);
	        } else {
	            throw new ApplicationException("Location Master are empty or null");
	        }

	        return LocationMasterDto;
	    }
	 
	 @Override
	    @Async
	    @Scheduled(initialDelay = 1000, fixedDelay = 1800000)
	    public void locationUpdateScheduler() {
	        List<LocationMasterDto> allLocations = new ArrayList<>();
	        allLocations = this
	                .getLocationMasterList();

	        if (allLocations.isEmpty()) {

	            allLocations.add(new LocationMasterDto(1, "Bangalore-BHIVE-DC (303)", true));
	            allLocations.add(new LocationMasterDto(2, "Bangalore-Whitefield-DC (301)", true));
	            allLocations.add(new LocationMasterDto(3, "Chennai-DC -II (702)", true));
	            allLocations.add(new LocationMasterDto(4, "Cyberjaya (6030)", true));
	            allLocations.add(new LocationMasterDto(5, "Des Moines, IA (5007)", true));
	            allLocations.add(new LocationMasterDto(6, "Gurgaon-DC (601)", true));
	            allLocations.add(new LocationMasterDto(7, "Hyderabad-Mindspace I-DC (202)", true));
	            allLocations.add(new LocationMasterDto(8, "Hyderabad-Mindspace II-DC (203)", true));
	            allLocations.add(new LocationMasterDto(9, "Hyderabad-Vatika-DC (205)", true));
	            allLocations.add(new LocationMasterDto(10, "Indore-Crystal IT Park-DC (102)", true));
	            allLocations.add(new LocationMasterDto(11, "Indore-Crystal IT Park-DC-II (103)", true));
	            allLocations.add(new LocationMasterDto(12, "Indore-YASH IT Park-SC-DC (104)", true));
	            allLocations.add(new LocationMasterDto(13, "Jarfalla (6011)", true));
	            allLocations.add(new LocationMasterDto(14, "London (8001)", true));
	            allLocations.add(new LocationMasterDto(15, "Minneapolis, MN (5011)", true));
	            allLocations.add(new LocationMasterDto(16, "Mississauga (6016)", true));
	            allLocations.add(new LocationMasterDto(17, "Moline, IL (5001)", true));
	            allLocations.add(new LocationMasterDto(18, "Pune-Hinjewadi III-DC (404)", true));
	            allLocations.add(new LocationMasterDto(19, "Pune-Magarpatta-DC (401)", true));
	            allLocations.add(new LocationMasterDto(20, "Pune-Magarpatta-DC-II (405)", true));
	            allLocations.add(new LocationMasterDto(21, "Sandviken (6012)", true));

	            allLocations.forEach(this :: createLocationMaster);
	        }
	    }

	

	public ResponseWrapperDto saveLocation(LocationMasterDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		LocationMaster master = null;
		if (formDto != null) {
			try {
				master = modelMapper.map(formDto, LocationMaster.class);
				if (ObjectUtils.isNotEmpty(master)) {
					master.setStatus(true);
					responseWrapperDto.setData(locationMasterRepository.save(master));
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
