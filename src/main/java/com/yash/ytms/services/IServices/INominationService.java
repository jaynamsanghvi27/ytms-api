package com.yash.ytms.services.IServices;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.domain.Nomination;
import com.yash.ytms.dto.NominationDto;

public interface INominationService {
	List<NominationDto> parseExcel(MultipartFile file) throws IOException;

	NominationDto saveNomination(NominationDto dto);

	List<NominationDto> findNominationsByTrainingID(Long trainingId);
	
	NominationDto getNomiationById(Long id);
	
	void deleteNominationById(Long id);

}
