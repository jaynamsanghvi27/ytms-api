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

import com.yash.ytms.domain.TrainingTypesMaster;
import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.TrainingTypesMasterDto;
import com.yash.ytms.exception.ApplicationException;
import com.yash.ytms.repository.TrainingTypesMasterRepository;
import com.yash.ytms.services.IServices.ITrainingTypesMasterService;

/**
 * 
 */
@Service
public class TrainingTypesMasterServiceImpl implements ITrainingTypesMasterService {
	
	@Autowired
	TrainingTypesMasterRepository typesMasterRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<TrainingTypesMasterDto> getTrainingTypesMasterList() {
		return modelMapper.map(typesMasterRepository.findAll(), List.class);
	}

	@Override
    public TrainingTypesMasterDto createTrainingMaster(TrainingTypesMasterDto trainingMasterDto) {
		TrainingTypesMaster unitMaster = null;

        if (ObjectUtils.isNotEmpty(trainingMasterDto)) {
        	unitMaster = this
                    .modelMapper
                    .map(trainingMasterDto, TrainingTypesMaster.class);

        	unitMaster = this
                    .typesMasterRepository
                    .save(unitMaster);

        	trainingMasterDto = this
                    .modelMapper
                    .map(unitMaster, TrainingTypesMasterDto.class);
        } else {
            throw new ApplicationException("User Role Details are empty or null");
        }

        return trainingMasterDto;
    }
 
	@Override
    @Async
    @Scheduled(initialDelay = 1000, fixedDelay = 1800000)
    public void trainingUpdateScheduler() {
        List<TrainingTypesMasterDto> allTrainings = new ArrayList<>();
        allTrainings = this.getTrainingTypesMasterList();

        if (allTrainings.isEmpty()) {

        	allTrainings.add(new TrainingTypesMasterDto(1, "ON_DEMAND", true));
        	allTrainings.add(new TrainingTypesMasterDto(2, "PROJECT_SPECIFIC", true));
        	allTrainings.add(new TrainingTypesMasterDto(3, "FRW", true));
        	allTrainings.add(new TrainingTypesMasterDto(4, "DRWF", true));

        	allTrainings.forEach(this :: createTrainingMaster);
        }
    }
	
	public ResponseWrapperDto saveTrainingType(TrainingTypesMasterDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		TrainingTypesMaster master = null;
		if (formDto != null) {
			try {
				master = modelMapper.map(formDto, TrainingTypesMaster.class);
				if (ObjectUtils.isNotEmpty(master)) {
					master.setStatus(true);
					responseWrapperDto.setData(typesMasterRepository.save(master));
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
