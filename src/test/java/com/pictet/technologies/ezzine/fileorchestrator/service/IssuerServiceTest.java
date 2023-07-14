package com.pictet.technologies.ezzine.fileorchestrator.service;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.IssuerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

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
    void testFilterNamesOfIssuer() {
        when(this.issuerRepository.findAll())
                .thenReturn(List.of(
                        IssuerEntity.builder()
                                .name("Duplicated name")
                                .build(),
                        IssuerEntity.builder()
                                .name("Duplicated name")
                                .build(),
                        IssuerEntity.builder()
                                .name("A Duplicated name")
                                .build(),
                        IssuerEntity.builder()
                                .name("B Duplicated name")
                                .build()
                ));

        var result = this.issuerService.getNamesOfIssuers();

        assertThat(result)
                .isEqualTo(
                        Set.of(
                                "A Duplicated name",
                                "B Duplicated name",
                                "Duplicated name"
                        ));
    }

    @Test
    void testGetNamesOfIssuers() {

//        when(this.issuerRepository.findAll())
//                .thenReturn(( Set.of(
//                        IssuerEntity.builder()
//                                .name("Schlumberger NV ")
//                                .build(),
//                        IssuerEntity.builder()
//                                .name("Sol Gel ")
//                                .build()
//
//                ));

        var issuerEntity1 = new IssuerEntity("", "Hunter Technology Corp", null);
        var issuerEntity2 = new IssuerEntity(" ", "Sol Gel", null);


        when(this.issuerRepository.findAll())
                .thenReturn(List.of(
                        issuerEntity1,
                        issuerEntity2));

        Set<String> names = issuerService.getNamesOfIssuers();

        assertThat(names)
                .isEqualTo(
                        Set.of(
                                "Hunter Technology Corp",
                                "Sol Gel"
                        ));

    }

}