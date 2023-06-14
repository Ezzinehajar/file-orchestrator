package com.pictet.technologies.ezzine.fileorchestrator.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Service
@Data
public class ShortSellingEligibleSecurityService {

	private final ProcessService processService;

	public void importShortSellingEligibleSecurities(@RequestParam("file") MultipartFile file) {
		// starts process
		var processEntity = this.processService.startProcess(file.getOriginalFilename());

		// TODO convert file
		//temp code
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}

		// TODO end process
        this.processService.endProcess(processEntity);
		
		System.out.println("ok service");
	}
}
