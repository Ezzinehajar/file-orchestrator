package com.pictet.technologies.ezzine.fileorchestrator.repository;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuerRepository extends JpaRepository<IssuerEntity, String> {

}
