package com.pictet.technologies.ezzine.fileorchestrator.controller;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import com.pictet.technologies.ezzine.fileorchestrator.service.IssuerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class IssuerController {

    private final IssuerService service;

    @GetMapping(path = "/issuer")
    public List<IssuerEntity> retrieveAllIssuer(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String isin,
            @RequestParam(required = false) LocalDate date

    ) {
        if (name == null && isin == null && date == null) {
            return service.getAllIssuers();
        }

        return service.getAllIssuers()
                .stream()
//                .filter(it -> filterByName(it, name))
                .filter(issuerEntity -> {
                    if (isin != null) return issuerEntity.getIsin().startsWith(isin);
                    return true;
                })
                .filter(IssuerEntity -> {
                    if (name != null) return IssuerEntity.getName().toUpperCase().contains(name.toUpperCase());
                    return true;
                })
                .filter(issuerEntity -> {
                    if (date != null) return issuerEntity.getDate().isBefore(date);
                    return true;
                })
                .toList();
    }

//    private boolean filterByName(IssuerEntity entity, String name) {
//       return Optional.ofNullable(name)
//                .map(String::toUpperCase)
//                .map(it -> entity.getName().toUpperCase().contains(it))
//                .orElse(true);
//        if (name != null) {
//            return entity.getName().toUpperCase().contains(name.toUpperCase());
//        }
//        return true;
//    }

    @GetMapping(path = "/issuer/names")
    public List<String> getNamesOfIssuers() {
        return service.getAllIssuers().stream()
                .map(IssuerEntity::getName)
                .toList();
    }
}


