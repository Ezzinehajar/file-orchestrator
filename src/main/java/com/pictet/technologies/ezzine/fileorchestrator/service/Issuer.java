package com.pictet.technologies.ezzine.fileorchestrator.service;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.IssuerRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class Issuer {

	private final ProcessService processService;
	private final IssuerRepository repository;

	public void importUkListOfExemptedShares(MultipartFile file) throws IOException {
		var processEntity = this.processService.startProcess(file.getOriginalFilename());
		List<IssuerEntity> issuerEntities = new ArrayList<>();

		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(1);
		Iterator<Row> iterator = sheet.rowIterator();

		readHeader(iterator);

		while (iterator.hasNext()) {
			Row row = iterator.next();
			Cell isinCell = row.getCell(0);
			Cell nameCell = row.getCell(1);
			Cell dateCell = row.getCell(2);

			if (isinCell != null) {
				var isin = isinCell.getStringCellValue();
				var name = nameCell.getStringCellValue();
				var dateSQL = dateCell.getDateCellValue();

				var entity = IssuerEntity.builder()
						.isin(isin)
						.name(name)
						.date(convertToDate(dateSQL))
						.build();
				
				issuerEntities.add(entity);
			}
		}

		repository.saveAll(issuerEntities);
		
		this.processService.endProcess(processEntity);
	}

	private Date convertToDate(java.util.Date dateSQL) {
		return new Date(dateSQL.getYear(), dateSQL.getMonth(), dateSQL.getDay());
	}

	private void readHeader(Iterator<Row> iterator) {
		if (iterator.hasNext()) {
			iterator.next();
		}
	}
}
