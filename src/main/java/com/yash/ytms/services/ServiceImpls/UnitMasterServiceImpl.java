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

import com.yash.ytms.domain.UnitMaster;
import com.yash.ytms.dto.UnitMasterDto;
import com.yash.ytms.exception.ApplicationException;
import com.yash.ytms.repository.UnitMasterRepository;
import com.yash.ytms.services.IServices.IUnitMasterService;

/**
 * 
 */
@Service
public class UnitMasterServiceImpl implements IUnitMasterService {
	
	 @Autowired
	 private UnitMasterRepository unitMasterRepository;
	 
	 @Autowired
	 private ModelMapper modelMapper;
	 
	 @Override
	 public List<UnitMasterDto> getUnitMasterList(){
		 return modelMapper.map(unitMasterRepository.findAll(),List.class);
	 }
	 
	 @Override
	    public UnitMasterDto createUnitMaster(UnitMasterDto unitMasterDto) {
		 UnitMaster unitMaster = null;

	        if (ObjectUtils.isNotEmpty(unitMasterDto)) {
	        	unitMaster = this
	                    .modelMapper
	                    .map(unitMasterDto, UnitMaster.class);

	        	unitMaster = this
	                    .unitMasterRepository
	                    .save(unitMaster);

	            unitMasterDto = this
	                    .modelMapper
	                    .map(unitMaster, UnitMasterDto.class);
	        } else {
	            throw new ApplicationException("Unit Master are empty or null");
	        }

	        return unitMasterDto;
	    }
	 
	 @Override
	    @Async
	    @Scheduled(initialDelay = 1000, fixedDelay = 1800000)
	    public void unitUpdateScheduler() {
	        List<UnitMasterDto> allUnits = new ArrayList<>();
	        allUnits = this
	                .getUnitMasterList();

	        if (allUnits.isEmpty()) {

	            allUnits.add(new UnitMasterDto(1, "BG1-BU2", true));
	            allUnits.add(new UnitMasterDto(2, "BG1-BU3", true));
	            allUnits.add(new UnitMasterDto(3, "BG1-BU4", true));
	            allUnits.add(new UnitMasterDto(4, "BG1-BU5", true));

	            allUnits.forEach(this :: createUnitMaster);
	        }
	    }
}
