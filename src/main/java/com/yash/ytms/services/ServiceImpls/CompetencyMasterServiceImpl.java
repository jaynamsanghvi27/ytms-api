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

import com.yash.ytms.domain.CompetencyMaster;
import com.yash.ytms.dto.CompetencyMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.exception.ApplicationException;
import com.yash.ytms.repository.CompetencyMasterRepository;
import com.yash.ytms.services.IServices.ICompetencyMasterService;

/**
 * 
 */
@Service
public class CompetencyMasterServiceImpl implements ICompetencyMasterService {
	@Autowired
	CompetencyMasterRepository competencyMasterRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	public List<CompetencyMasterDto> getCompetencyMasterList(){
		return modelMapper.map(competencyMasterRepository.findAll(), List.class); 
	}
	
	@Override
    public CompetencyMasterDto createCompetencyMaster (CompetencyMasterDto competencyMasterDto) {
		CompetencyMaster competencyMaster = null;

        if (ObjectUtils.isNotEmpty(competencyMasterDto)) {
        	competencyMaster = this
                    .modelMapper
                    .map(competencyMasterDto, CompetencyMaster.class);

        	competencyMaster = this
                    .competencyMasterRepository
                    .save(competencyMaster);

        	competencyMasterDto = this
                    .modelMapper
                    .map(competencyMaster, CompetencyMasterDto.class);
        } else {
            throw new ApplicationException("Competency Master are empty or null");
        }

        return competencyMasterDto;
    }
 
	@Override
    @Async
    @Scheduled(initialDelay = 1000, fixedDelay = 1800000)
    public void competencyMasterUpdateScheduler() {
        List<CompetencyMasterDto> allCompetency = new ArrayList<>();
        allCompetency = this.getCompetencyMasterList();

        if (allCompetency.isEmpty()) {

        	allCompetency.add(new CompetencyMasterDto(1, "JAVA", true));
        	allCompetency.add(new CompetencyMasterDto(2, "REACT", true));
        	allCompetency.add(new CompetencyMasterDto(3, "UI-HTML", true));
        	allCompetency.add(new CompetencyMasterDto(4, "UI-CSS", true));
        	allCompetency.add(new CompetencyMasterDto(5, "ANGULER", true));
        	allCompetency.add(new CompetencyMasterDto(6, "DotNet", true));

        	allCompetency.forEach(this :: createCompetencyMaster);
        }
    }
	
	public ResponseWrapperDto saveCompetency(CompetencyMasterDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		CompetencyMaster master = null;
		if (formDto != null) {
			try {
				master = modelMapper.map(formDto, CompetencyMaster.class);
				if (ObjectUtils.isNotEmpty(master)) {
					master.setStatus(true);
					responseWrapperDto.setData(competencyMasterRepository.save(master));
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
