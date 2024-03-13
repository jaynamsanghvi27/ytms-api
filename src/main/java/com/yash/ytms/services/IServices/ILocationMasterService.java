/**
 * 
 */
package com.yash.ytms.services.IServices;

import java.util.List;

import com.yash.ytms.dto.LocationMasterDto;
import com.yash.ytms.dto.ResponseWrapperDto;

/**
 * 
 */
public interface ILocationMasterService {
	public List<LocationMasterDto> getLocationMasterList();
	
	public LocationMasterDto createLocationMaster(LocationMasterDto userMasterDto);
	
	void locationUpdateScheduler();

	public ResponseWrapperDto saveLocation(LocationMasterDto formDto);

}
