/**
 * 
 */
package com.yash.ytms.services.ServiceImpls;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ytms.constants.UserAccountStatusTypes;
import com.yash.ytms.domain.TrainingRequestForm;
import com.yash.ytms.domain.UnitMaster;
import com.yash.ytms.dto.ResponseWrapperDto;
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

	public List<UnitMasterDto> getUnitMasterList() {
		return modelMapper.map(unitMasterRepository.findAll(), List.class);
	}

	public ResponseWrapperDto saveUnit(UnitMasterDto formDto) {
		ResponseWrapperDto responseWrapperDto = new ResponseWrapperDto();
		UnitMaster master = null;
		if (formDto != null) {
			try {
				master = modelMapper.map(formDto, UnitMaster.class);
				if (ObjectUtils.isNotEmpty(master)) {
					master.setStatus(true);
					responseWrapperDto.setData(unitMasterRepository.save(master));
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
