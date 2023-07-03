package com.pictet.technologies.ezzine.fileorchestrator.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pictet.technologies.ezzine.fileorchestrator.service.UkListOfExemptedSharesService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/uk-list-exempted-shares")
@Slf4j
public class UkListOfExemptedSharesController {

	private final UkListOfExemptedSharesService service;

	@PostMapping
	public void importUkListExemptedShares(@RequestParam("file") MultipartFile file) throws IOException {
		this.service.importUkListOfExemptedShares(file);

		log.info("file imported successfully ");
	}
}
