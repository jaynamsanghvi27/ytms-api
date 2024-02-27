/**
 * 
 */
package com.yash.ytms.services.ServiceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ytms.domain.CompetencyMaster;
import com.yash.ytms.dto.CompetencyMasterDto;
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
}
