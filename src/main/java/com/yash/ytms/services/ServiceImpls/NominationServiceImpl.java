package com.yash.ytms.services.ServiceImpls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }
 
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            System.out.println("----"+Long.parseLong(row.getCell(0).getNumericCellValue()+"")+"----"+row.getCell(1).getStringCellValue()+"----"+row.getCell(2).getStringCellValue()+"----"+row.getCell(3).getStringCellValue()+"----"+row.getCell(4).getStringCellValue()+"----"+row.getCell(5).getStringCellValue()+"----"+row.getCell(6).getStringCellValue()+"----"+row.getCell(7).getStringCellValue()+"-----");
            Nomination nomination=new Nomination(0l,"hello",
//            		row.getCell(0).getStringCellValue(),
            		row.getCell(1).getStringCellValue(),        
                    row.getCell(2).getStringCellValue(),        
                    row.getCell(3).getStringCellValue(),        
                    row.getCell(4).getStringCellValue(),
                    row.getCell(5).getStringCellValue(),
                    row.getCell(6).getStringCellValue(),
                    row.getCell(7).getStringCellValue(),
                    1l
                   );
            
            NominationData nominationData=this.modelMapper.map(nomination,NominationData.class);
            
            nominationUploadRepository.save(nomination);
            
            nominationUploadDataList.add(nominationData);
        }
 
        workbook.close();
        return nominationUploadDataList;
    
	}

}
