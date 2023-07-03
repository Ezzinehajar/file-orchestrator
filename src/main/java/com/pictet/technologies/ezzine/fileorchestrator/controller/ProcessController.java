package com.pictet.technologies.ezzine.fileorchestrator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pictet.technologies.ezzine.fileorchestrator.controller.dto.ProcessDTO;
import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;
import com.pictet.technologies.ezzine.fileorchestrator.service.ProcessService;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api/v1/process")
@AllArgsConstructor
@Data
public class ProcessController {

	private final ProcessService processservice;

	@GetMapping
	public List<ProcessDTO> getProcess() {
		var processes = new ArrayList<ProcessDTO>();

		var entities = processservice.fetchProcess();
		for (ProcessEntity entity : entities) {
			var dto = new ProcessDTO(
					entity.getFileName(), 
					entity.getStartedAt(),
					entity.getFinishedAt());

			processes.add(dto);

		}

		return processes;
	}
}
