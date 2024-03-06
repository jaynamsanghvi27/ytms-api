package com.yash.ytms.services.ServiceImpls;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.aspectj.weaver.Iterators;
import org.modelmapper.internal.util.Iterables;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.dto.NominationDto;
import com.yash.ytms.services.IServices.INominationService;

@Service
public class NominationServiceImpl implements INominationService {

	/*
	 * @Autowired private NominationUploadRepository nominationUploadRepository;
	 * 
	 * @Autowired private ModelMapper modelMapper;
	 */

	@Override
	public List<NominationDto> parseExcel(MultipartFile file) throws IOException {

		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);

		List<NominationDto> nominationUploadDataList = new ArrayList();

		Iterator<Row> rowIterator = sheet.iterator();
		 // Conv
		// Skip header row if exists
		// rowIterator = rowIterator.skip(1);
		if (rowIterator.hasNext()) {
			rowIterator.next();
		}

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if(row.getCell(0)==null) {
				break;
			}

			// Nomination nomination = setNominationObject(row);

			NominationDto nominationData = setNominationObject(row);

			// nominationUploadRepository.save(nomination);

			nominationUploadDataList.add(nominationData);

		}

		workbook.close();
		return nominationUploadDataList;

	}

	NominationDto setNominationObject(Row row) {
		/* Nomination nomination = new Nomination(); */
		NominationDto nomination = new NominationDto();
		
		

		String formattedStringValue = new DecimalFormat("#").format(row.getCell(0).getNumericCellValue());
		nomination.setEmp_id(formattedStringValue);
		nomination.setEmp_name(row.getCell(1).getStringCellValue());
		nomination.setEmp_mail_id(row.getCell(2).getStringCellValue());
		nomination.setGrade(row.getCell(3).getStringCellValue());
		nomination.setSkill(row.getCell(4).getStringCellValue());
		nomination.setCurrent_allocation(row.getCell(5).getStringCellValue());
		nomination.setProject(row.getCell(6).getStringCellValue());
		nomination.setCurrent_location(row.getCell(7).getStringCellValue());

		return nomination;

	}

}
