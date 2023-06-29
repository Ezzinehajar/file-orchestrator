package com.pictet.technologies.ezzine.fileorchestrator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pictet.technologies.ezzine.fileorchestrator.domain.UkListOfExemptedSharesEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.UkListOfExemptedSharesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class UkListOfExemptedSharesService {

	private final ProcessService processService;
	private final UkListOfExemptedSharesRepository repository;

	public void importUkListOfExemptedShares(MultipartFile file) {

		// var processEntity
		// =this.processService.startProcess(file.getOriginalFilename());

		UkListOfExemptedSharesEntity object = new UkListOfExemptedSharesEntity();
		object.setRoot(90912);
		object.setContrycode("");

		UkListOfExemptedSharesEntity savedEntity = repository.save(object);

		if (savedEntity != null && savedEntity.getContrycode() != null) {
			System.out.println(" Entity persisted successfully with COUNTRYCODE: " + savedEntity.getContrycode());
		} else {
			System.out.println("Failed to persist UK Entity");
		}

	}

}