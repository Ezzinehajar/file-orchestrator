package com.pictet.technologies.ezzine.fileorchestrator.repository;

import com.pictet.technologies.ezzine.fileorchestrator.domain.UkListOfExemptedSharesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UkListOfExemptedSharesRepository extends JpaRepository<UkListOfExemptedSharesEntity, String> {


}
