package com.pictet.technologies.ezzine.fileorchestrator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pictet.technologies.ezzine.fileorchestrator.service.ShortSellingEligibleSecurityService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/short-selling-eligible-securities")
@AllArgsConstructor
public class ShortSellingEligibleSecurityController {

	private final ShortSellingEligibleSecurityService service;

	@PostMapping
	public void importShortSellingEligibleSecurities(@RequestParam("file") MultipartFile file) {
		this.service.importShortSellingEligibleSecurities(file);
		
		System.out.println("ok");

	}
}
