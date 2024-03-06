package com.yash.ytms.services.IServices;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.dto.NominationDto;

public interface INominationService {
	List<NominationDto> parseExcel(MultipartFile file) throws IOException ;
}
