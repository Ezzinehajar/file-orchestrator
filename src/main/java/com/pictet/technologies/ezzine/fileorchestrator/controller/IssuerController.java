package com.pictet.technologies.ezzine.fileorchestrator.controller;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import com.pictet.technologies.ezzine.fileorchestrator.service.UkListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/issuer")
@Slf4j

public class IssuerController {

    private final UkListService service;

    @GetMapping
    public List<IssuerEntity> retrieveAllIssuer() {
        return service.getAllIssuers();
    }
}
