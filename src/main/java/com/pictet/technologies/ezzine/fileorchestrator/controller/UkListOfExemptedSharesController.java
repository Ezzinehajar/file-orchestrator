package com.pictet.technologies.ezzine.fileorchestrator.controller;

import com.pictet.technologies.ezzine.fileorchestrator.service.UkListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/uk-list-exempted-shares")
@Slf4j
public class UkListOfExemptedSharesController {
	private final UkListService service;

	@PostMapping
	public void importUkListExemptedShares(@RequestParam("file") MultipartFile file) throws IOException {
		this.service.importUkListOfExemptedShares(file);

		log.info("file imported successfully ");
	}
}
