package com.pictet.technologies.ezzine.fileorchestrator.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.ProcessRepository;

@Service
public class ProcessService {
	
	private final ProcessRepository prepository; 
	
	public ProcessService(ProcessRepository repository) {
		this.prepository = repository;
	}
	
    // start process
	public ProcessEntity startProcess(String filename) {
		var  process = ProcessEntity.builder()
				.fileName(filename)
				.startedAt(LocalDateTime.now())
				.build();
		
		return prepository.save(process);
	}
	
	 //methode for fetch process
	public List<ProcessEntity> fetchProcess() {
		return prepository.findAll();
		
	}
	// end process

	public void endProcess(ProcessEntity process) {
		process.setFinishedAt(LocalDateTime.now());
		prepository.save(process);
	}
}
