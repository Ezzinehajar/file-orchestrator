package com.pictet.technologies.ezzine.fileorchestrator.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "ISSUER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssuerEntity {
	@Id
	@Column(name = "Isin")
	private String isin;

	@Column(name = "Name")
	private String name;

	@Column(name = "DATE_ADDED")
	private LocalDate date;
}
