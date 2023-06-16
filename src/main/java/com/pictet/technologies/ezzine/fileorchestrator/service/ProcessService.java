package com.pictet.technologies.ezzine.fileorchestrator.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.ProcessRepository;

@Service
public class ProcessService {
	
	private final ProcessRepository repository; 
	
	public ProcessService(ProcessRepository repository) {
		this.repository = repository;
	}
	
    // start process
	public ProcessEntity startProcess(String filename) {
		var  process = ProcessEntity.builder()
				.fileName(filename)
				.startedAt(LocalDateTime.now())
				.build();
		
		return repository.save(process);
	}
	
	 //methode for fetch process
	public List<ProcessEntity> fetchProcess() {
		return repository.findAll();
		
	}
	// end process

	public void endProcess(ProcessEntity process) {
		process.setFinishedAt(LocalDateTime.now());
		repository.save(process);
	}
}
