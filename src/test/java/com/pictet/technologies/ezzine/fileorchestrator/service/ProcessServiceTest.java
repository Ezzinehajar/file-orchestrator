package com.pictet.technologies.ezzine.fileorchestrator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.ProcessRepository;

public class ProcessServiceTest {

	private ProcessRepository repository;

	private ProcessService processService;

	@BeforeEach
	void setUp() {
		this.repository = mock(ProcessRepository.class);

		this.processService = new ProcessService(repository);
	}

	@Test
	public void testStartProcess() {
		String filename = "ds_list20230518.csv";
		LocalDateTime startDateTime = LocalDateTime.now();

		// or .builder
		// var processEntity = new ProcessEntity();
//        processEntity.setId(1L);
//        processEntity.setFileName(filename);
//        processEntity.setStartedAt(startDateTime);

		Mockito.when(repository.save(any(ProcessEntity.class)))
				.thenReturn(ProcessEntity.builder().id(1L).fileName(filename).startedAt(startDateTime).build());

		ProcessEntity result = processService.startProcess(filename);

		assertEquals(filename, result.getFileName());
		assertEquals(startDateTime.toLocalDate(), result.getStartedAt().toLocalDate());

		verify(repository, times(1)).save(any(ProcessEntity.class));
	}

	@Test
	public void testFetchProcess() {

		List<ProcessEntity> list = mock(List.class);
		when(repository.findAll()).thenReturn(list);

		List<ProcessEntity> result1 = processService.fetchProcess();

		verify(repository).findAll();
		assertEquals(list.size(), result1.size());

	}

	@Test
	public void testEndProcess() {

		var processEntity = new ProcessEntity();

		processService.endProcess(processEntity);
		// verify(processEntity).setFinishedAt(any(LocalDateTime.class));
		verify(repository, times(1)).save(any(ProcessEntity.class));

	}

}
