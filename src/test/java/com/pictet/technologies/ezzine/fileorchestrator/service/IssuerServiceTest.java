package com.pictet.technologies.ezzine.fileorchestrator.service;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.IssuerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IssuerServiceTest {

    private IssuerRepository issuerRepository;
    private IssuerService issuerService;

    @BeforeEach
    void setUp() {
        this.issuerRepository = mock(IssuerRepository.class);
        this.issuerService = new IssuerService(issuerRepository);
    }

    @Test
    void returns_names_of_issuer() {
        when(this.issuerRepository.findAll())
                .thenReturn(List.of(
                        IssuerEntity.builder()
                                .name("iShares Currency Hedged Msci Germany ETF")
                                .build(),
                        IssuerEntity.builder()
                                .name("Schlumberger NV")
                                .build()
                ));

        var result = this.issuerService.getNamesOfIssuers();

        assertThat(result).contains("iShares Currency Hedged Msci Germany ETF");

    }
}