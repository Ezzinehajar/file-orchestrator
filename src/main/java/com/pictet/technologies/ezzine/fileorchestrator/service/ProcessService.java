package com.pictet.technologies.ezzine.fileorchestrator.service;

import com.pictet.technologies.ezzine.fileorchestrator.controller.dto.ProcessDTO;
import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.ProcessRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProcessService {

	private final ProcessRepository repository;

	public ProcessService(ProcessRepository repository) {
		this.repository = repository;
	}

	public ProcessEntity startProcess(String filename) {
		var process = ProcessEntity.builder()
				.fileName(filename)
				.startedAt(LocalDateTime.now())
				.build();

		return repository.save(process);
	}

	public List<ProcessEntity> fetchProcess() {
		return repository.findAll();
	}

	public void endProcess(ProcessEntity process) {
		process.setFinishedAt(LocalDateTime.now());
		repository.save(process);
	}

	public List<ProcessDTO> fetchProcessInProgress(){
		var entities = repository.findAll();

		var processes = entities.stream()
				.filter(entity -> entity.getFinishedAt() == null)
				.map(this::convert)
				.toList();

		return processes;
	}

	private ProcessDTO convert(ProcessEntity entity) {
		ProcessDTO dto = new ProcessDTO(
				entity.getFileName(),
				entity.getStartedAt(),
				entity.getFinishedAt());
		return dto;
	}
}
