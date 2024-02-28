/**
 * 
 */
package com.yash.ytms.services.ServiceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ytms.dto.TrainingTypesMasterDto;
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

}
