package com.pictet.technologies.ezzine.fileorchestrator.service;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.IssuerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssuerService {

    private final IssuerRepository issuerRepository;

    public List<IssuerEntity> getAllIssuers() {
        return issuerRepository.findAll();
    }

}
