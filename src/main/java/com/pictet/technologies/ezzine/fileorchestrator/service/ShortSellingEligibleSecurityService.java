package com.pictet.technologies.ezzine.fileorchestrator.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pictet.technologies.ezzine.fileorchestrator.domain.ShortSellingEligibleSecurityEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.ShortSellingEligibleSecurityRepository;

import liquibase.util.csv.CSVReader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ShortSellingEligibleSecurityService {
	
	private static final int SKIP_HEADER_LINES = 5;

	private final ProcessService processService;

	private final ShortSellingEligibleSecurityRepository repository;

	public void importShortSellingEligibleSecurities(MultipartFile file) {
		List<ShortSellingEligibleSecurityEntity> objects = new ArrayList<>();
		
		var processEntity = this.processService.startProcess(file.getOriginalFilename());
		
		try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(file.getInputStream())))) {
			String[] nextLine;

			var index = 0;
			while ((nextLine = reader.readNext()) != null) {
				if (index >= SKIP_HEADER_LINES) {
					ShortSellingEligibleSecurityEntity obj = new ShortSellingEligibleSecurityEntity();
					obj.setRowNumber(Integer.valueOf(nextLine[0]));
					obj.setStockCode(nextLine[1]);
					obj.setStockShortName(nextLine[2]);
					obj.setCurrencyCode(nextLine[3]);
					obj.setSecurityType(nextLine[4]);
					obj.setExemptFromTickRule(nextLine[5]);
					obj.setRemarks(nextLine[6]);
					obj.setProcess(processEntity);
					
					log.debug(obj.toString());

					objects.add(obj);
				}

				index += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		repository.saveAll(objects);

		this.processService.endProcess(processEntity);

		log.info("ok service");
	}
}
