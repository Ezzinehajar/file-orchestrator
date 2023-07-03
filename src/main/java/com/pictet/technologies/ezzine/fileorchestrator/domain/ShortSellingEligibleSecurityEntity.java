package com.pictet.technologies.ezzine.fileorchestrator.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "short_selling_eligible_security")
@Data
public class ShortSellingEligibleSecurityEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@Column(name = "row_number")
	private Integer rowNumber;

	@Column(name = "stock_code")
	private String stockCode;

	@Column(name = "stock_short_name")
	private String stockShortName;

	@Column(name = "currency_code")
	private String currencyCode;

	@Column(name = "security_type")
	private String securityType;

	@Column(name = "exempt_from_tick_rule")
	private String exemptFromTickRule;

	@Column(name = "remarks")
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "process_id")
	private ProcessEntity process;

}
