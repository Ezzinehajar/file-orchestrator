package com.pictet.technologies.ezzine.fileorchestrator.service;

import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.pictet.technologies.ezzine.fileorchestrator.domain.*;
import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.ShortSellingEligibleSecurityRepository;

import net.bytebuddy.asm.MemberSubstitution.Argument;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class ShortSellingEligibleSecurityTest {

	private ProcessService processService;
	private ShortSellingEligibleSecurityRepository srepository;
	private ProcessEntity processentity;
	private ShortSellingEligibleSecurityService service;

	@BeforeEach
	void setUp() {

		this.processService = mock(ProcessService.class);
		srepository = mock(ShortSellingEligibleSecurityRepository.class);
		processentity = mock(ProcessEntity.class);
		service = new ShortSellingEligibleSecurityService(processService, srepository);

	}

	@Test

	public void TestImporttShortSellingEligibleSecuritiesFile() throws IOException {

		String csvData = "List of Designated Securities Eligible for Short Selling,\n"
				+ "Effective date : 18/05/2023,\n" + "Note : Delisted securities excluded,\n" + "\n"
				+ "No.,Stock Code,Stock Short Name,Currency,Type,Exempt from Tick Rule,Remarks\n"
				+ "1,1,CKH HOLDINGS,HKD,EQTY,,,\n" + "2,2,CLP HOLDINGS,HKD,EQTY,,,\n";

		MockMultipartFile file = new MockMultipartFile("ds_list20230518.csv", "ds_list20230518.csv", null,
				csvData.getBytes());

		when(processService.startProcess("ds_list20230518.csv")).thenReturn(processentity);

		service.importShortSellingEligibleSecurities(file);
		verify(processService, times(1)).startProcess("ds_list20230518.csv");
		verify(processService, times(1)).endProcess(processentity);
		ArgumentCaptor<List<ShortSellingEligibleSecurityEntity>> captorEntity = ArgumentCaptor.forClass(List.class);
		List<ShortSellingEligibleSecurityEntity> savedEntities = new ArrayList<>();
		verify(srepository, times(1)).saveAll(captorEntity.capture());
		savedEntities = captorEntity.getValue();

		ShortSellingEligibleSecurityEntity entity1 = savedEntities.get(0);
		assertEquals(1, entity1.getRowNumber());
		assertEquals("1", entity1.getStockCode());
		assertEquals("CKH HOLDINGS", entity1.getStockShortName());
		assertEquals("HKD", entity1.getCurrencyCode());
		assertEquals("EQTY", entity1.getSecurityType());
		assertEquals("", entity1.getExemptFromTickRule());
		assertEquals("", entity1.getRemarks());

		ShortSellingEligibleSecurityEntity entity2 = savedEntities.get(1);
		assertEquals(2, entity2.getRowNumber());
		assertEquals("2", entity2.getStockCode());
		assertEquals("CLP HOLDINGS", entity2.getStockShortName());
		assertEquals("HKD", entity2.getCurrencyCode());
		assertEquals("EQTY", entity2.getSecurityType());
		assertEquals("", entity2.getExemptFromTickRule());
		assertEquals("", entity2.getRemarks());

	}

}
