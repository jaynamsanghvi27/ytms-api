/**
 * 
 */
package com.yash.ytms.services.ServiceImpls;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ytms.dto.UnitMasterDto;
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
	 
	 public List<UnitMasterDto> getUnitMasterList(){
		 return modelMapper.map(unitMasterRepository.findAll(),List.class);
	 }
}
