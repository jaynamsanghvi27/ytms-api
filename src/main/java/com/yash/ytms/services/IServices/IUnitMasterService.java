/**
 * 
 */
package com.yash.ytms.services.IServices;

import java.util.List;

import com.yash.ytms.dto.ResponseWrapperDto;
import com.yash.ytms.dto.UnitMasterDto;

/**
 * 
 */
public interface IUnitMasterService {
	public List<UnitMasterDto> getUnitMasterList();
	
	public UnitMasterDto createUnitMaster(UnitMasterDto userMasterDto);
	
	void unitUpdateScheduler();

	public ResponseWrapperDto saveUnit(UnitMasterDto formDto);

}
