package com.yash.ytms.services.IServices;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.dto.NominationData;

public interface INominationService {
	List<NominationData> parseExcel(MultipartFile file) throws IOException;
}
