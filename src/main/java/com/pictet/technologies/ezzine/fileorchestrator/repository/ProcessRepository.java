package com.pictet.technologies.ezzine.fileorchestrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {
	
	
}