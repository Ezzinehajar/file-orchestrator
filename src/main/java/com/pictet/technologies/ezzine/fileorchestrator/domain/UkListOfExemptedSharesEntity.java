package com.pictet.technologies.ezzine.fileorchestrator.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private String id;

	@Column(name = "root")
	private int root;

	@Column(name = "Isin")
	private String isin;
	
	@Column(name="COUNTR_YCODE")
	private String contrycode;

	@Column(name = "relevantAuthority")
	private String relevantAuthority;

	@Column(name = "modificationDateStr")
	private Date modificationDateStr;

	@Column(name = "version")
	private long version;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "TIME_STAMP")
	private Date timestamp;

	@Column(name = "exemptionStartDateStr")
	private LocalDateTime exemptionStartDateStr;

}
