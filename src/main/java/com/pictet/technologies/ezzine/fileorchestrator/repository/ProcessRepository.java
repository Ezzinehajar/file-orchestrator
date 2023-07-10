package com.pictet.technologies.ezzine.fileorchestrator.repository;

import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {
}