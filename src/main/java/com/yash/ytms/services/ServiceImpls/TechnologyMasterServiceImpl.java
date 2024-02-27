/**
 * 
 */
package com.yash.ytms.services.ServiceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ytms.dto.TechnologyMasterDto;
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
}
