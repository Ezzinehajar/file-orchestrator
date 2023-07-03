package com.pictet.technologies.ezzine.fileorchestrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;

public interface IssuerRepository extends JpaRepository<IssuerEntity, String>  {
}
