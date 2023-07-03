package com.pictet.technologies.ezzine.fileorchestrator.domain;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="ISSUER")
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
	private Date date;

}
