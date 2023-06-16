package com.pictet.technologies.ezzine.fileorchestrator.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pictet.technologies.ezzine.fileorchestrator.domain.ShortSellingEligibleSecurityEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.ShortSellingEligibleSecurityRepository;

import liquibase.util.csv.CSVReader;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShortSellingEligibleSecurityService {

	private final ProcessService processService;
	private final ShortSellingEligibleSecurityRepository srepository;

	public void importShortSellingEligibleSecurities(MultipartFile file) {
		// starts process
		var processEntity = this.processService.startProcess(file.getOriginalFilename());

		List<ShortSellingEligibleSecurityEntity> objects = new ArrayList<>();
		
		// read line by line
		try (CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(file.getInputStream())))) {

			String[] nextLine;
			// Read one line at a time

			var index = 0;
			while ((nextLine = reader.readNext()) != null) {
				if (index >= 5) {
					ShortSellingEligibleSecurityEntity obj = new ShortSellingEligibleSecurityEntity();
					obj.setRowNumber(Integer.valueOf(nextLine[0]));
					obj.setStockCode(nextLine[1]);
					obj.setStockShortName(nextLine[2]);
					obj.setCurrencyCode(nextLine[3]);
					obj.setSecurityType(nextLine[4]);
					obj.setExemptFromTickRule(nextLine[5]);
					obj.setRemarks(nextLine[6]);
					obj.setProcess(processEntity);
					System.out.println(obj);

					objects.add(obj);
				}

				index += 1;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		srepository.saveAll(objects);
 
		this.processService.endProcess(processEntity);

		System.out.println("ok service");
	}

}
