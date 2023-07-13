package com.pictet.technologies.ezzine.fileorchestrator.service;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.IssuerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IssuerService {

    private final IssuerRepository issuerRepository;

    public List<IssuerEntity> getAllIssuers() {
        return issuerRepository.findAll();
    }

    public Set<String> getNamesOfIssuers() {
        return this.getAllIssuers().stream()
                .map(IssuerEntity::getName)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public List<IssuerEntity> filterNamesOfIssuer(String isin, String name, LocalDate date) {
        if (name == null && isin == null && date == null) {
            return this.getAllIssuers();
        }

        return this.getAllIssuers()
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
}
