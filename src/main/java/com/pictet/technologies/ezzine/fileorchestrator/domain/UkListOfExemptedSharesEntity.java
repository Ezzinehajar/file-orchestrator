package com.pictet.technologies.ezzine.fileorchestrator.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "UK_LIST_OF_EXEMPTED_SHARES ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UkListOfExemptedSharesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXCEL_ID")
	private String EXCEL_ID;

	@Column(name = "root")
	private int root;

	@ManyToOne
	@JoinColumn(name = "isin")
	private IssuerEntity isin;

	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	@Column(name = "relevantAuthority")
	private String relevantAuthority;

	@Column(name = "modificationDateStr")
	private LocalDate modificationDateStr;

	@Column(name = "version")
	private long version;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "TIME_STAMP")
	private LocalDate timestamp;

	@Column(name = "exemptionStartDateStr")
	private LocalDateTime exemptionStartDateStr;

	@ManyToOne
	@JoinColumn(name = "process_id")
	private ProcessEntity process;
}
