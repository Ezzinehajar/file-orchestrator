package com.pictet.technologies.ezzine.fileorchestrator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pictet.technologies.ezzine.fileorchestrator.service.UkListOfExemptedSharesService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/uk-list-exempted-shares")

public class UkListOfExemptedSharesController {

	private final UkListOfExemptedSharesService service;

	@PostMapping
	public void importUkListExemptedShares(@RequestParam("file") MultipartFile file) {
		this.service.importUkListOfExemptedShares(file);

		System.out.println("file imported successfully ");

	}

}
