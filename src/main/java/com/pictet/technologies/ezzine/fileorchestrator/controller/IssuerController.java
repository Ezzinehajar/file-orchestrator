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
import java.util.Set;

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
        return service.filterNamesOfIssuer(isin, name, date);
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
    public Set<String> getNamesOfIssuers() {
        return service.getNamesOfIssuers();
    }

    @GetMapping(path = "/issuer/names/number-same-prefix-name")
    public long getNumbersOfIssuer(
            @RequestParam(required = false) String name
    ) {
        return service.getNumberOfIssuerWithTheSamePrefixName(name);
    }
}


