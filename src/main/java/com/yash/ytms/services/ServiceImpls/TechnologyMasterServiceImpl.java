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

import com.yash.ytms.domain.TechnologyMaster;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TechnologyMasterDto;
import com.yash.ytms.exception.ApplicationException;
import com.yash.ytms.repository.TechnologyMasterRepository;
import com.yash.ytms.services.IServices.ITechnologyMasterService;

/**
 * 
 */
@Service
public class TechnologyMasterServiceImpl implements ITechnologyMasterService {

	@Autowired
	TechnologyMasterRepository technologyMasterRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public List<TechnologyMasterDto> getTechnologyMasterList() {
		return modelMapper.map(technologyMasterRepository.findAll(), List.class);
	}
	
	@Override
    public TechnologyMasterDto createTechnologyMaster(TechnologyMasterDto technologyMasterDto) {
		TechnologyMaster technologyMaster = null;

        if (ObjectUtils.isNotEmpty(technologyMasterDto)) {
        	technologyMaster = this
                    .modelMapper
                    .map(technologyMasterDto, TechnologyMaster.class);

        	technologyMaster = this
                    .technologyMasterRepository
                    .save(technologyMaster);

        	technologyMasterDto = this
                    .modelMapper
                    .map(technologyMaster, TechnologyMasterDto.class);
        } else {
            throw new ApplicationException("Technology Master are empty or null");
        }

        return technologyMasterDto;
    }
 
	@Override
    @Async
    @Scheduled(initialDelay = 1000, fixedDelay = 1800000)
    public void technologyUpdateScheduler() {
        List<TechnologyMasterDto> allTechnology = new ArrayList<>();
        allTechnology = this.getTechnologyMasterList();

        if (allTechnology.isEmpty()) {

        	allTechnology.add(new TechnologyMasterDto(1, "JAVA", "java", null, null, true));
        	allTechnology.add(new TechnologyMasterDto(2, "REACT", "react", null, null, true));
        	allTechnology.add(new TechnologyMasterDto(3, "UI-HTML", "html", null, null, true));
        	allTechnology.add(new TechnologyMasterDto(4, "UI-CSS", "css", null, null, true));
        	allTechnology.add(new TechnologyMasterDto(5, "ANGULER", "anguler", null, null, true));
        	allTechnology.add(new TechnologyMasterDto(6, "DotNet", "c#", null, null, true));

        	allTechnology.forEach(this :: createTechnologyMaster);
        }
    }
	
	public ResponseWrapperDto saveTechnology(TechnologyMasterDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		TechnologyMaster master = null;
		if (formDto != null) {
			try {
				master = modelMapper.map(formDto, TechnologyMaster.class);
				if (ObjectUtils.isNotEmpty(master)) {
					master.setStatus(true);
					responseWrapperDto.setData(technologyMasterRepository.save(master));
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
