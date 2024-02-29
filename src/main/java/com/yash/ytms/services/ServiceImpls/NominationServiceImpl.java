package com.yash.ytms.services.ServiceImpls;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ytms.domain.Nomination;
import com.yash.ytms.dto.NominationData;
import com.yash.ytms.repository.NominationUploadRepository;
import com.yash.ytms.services.IServices.INominationService;

@Service
public class NominationServiceImpl implements INominationService {

	@Autowired
	private NominationUploadRepository nominationUploadRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<NominationData> parseExcel(MultipartFile file) throws IOException {

		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);

		List<NominationData> nominationUploadDataList = new ArrayList();

		Iterator<Row> rowIterator = sheet.iterator();
		// Skip header row if exists
		// rowIterator = rowIterator.skip(1);
		if (rowIterator.hasNext()) {
			rowIterator.next();
		}

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Nomination nomination = setNominationObject(row);

			NominationData nominationData = this.modelMapper.map(nomination, NominationData.class);

			nominationUploadRepository.save(nomination);

			nominationUploadDataList.add(nominationData);
		}

		workbook.close();
		return nominationUploadDataList;

	}

	Nomination setNominationObject(Row row) {
		Nomination nomination = new Nomination();

		String formattedStringValue = new DecimalFormat("#").format(row.getCell(0).getNumericCellValue());
		nomination.setEmp_id(formattedStringValue);
		nomination.setEmp_name(row.getCell(1).getStringCellValue());
		nomination.setEmp_mail_id(row.getCell(2).getStringCellValue());
		nomination.setGrade(row.getCell(3).getStringCellValue());
		nomination.setSkill(row.getCell(4).getStringCellValue());
		nomination.setCurrent_allocation(row.getCell(5).getStringCellValue());
		nomination.setProject(row.getCell(6).getStringCellValue());
		nomination.setCurrent_location(row.getCell(7).getStringCellValue());
		nomination.setTraining_id(1l);

		return nomination;
	}

}
