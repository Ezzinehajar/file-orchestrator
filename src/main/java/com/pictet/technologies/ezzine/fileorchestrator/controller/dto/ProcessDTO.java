package com.pictet.technologies.ezzine.fileorchestrator.controller.dto;

import java.time.LocalDateTime;

public record ProcessDTO(
		String fileName,
		LocalDateTime startedAt,
		LocalDateTime finishedAt

) {
}
