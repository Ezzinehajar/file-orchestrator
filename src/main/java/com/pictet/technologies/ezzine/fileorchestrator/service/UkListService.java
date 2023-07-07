package com.pictet.technologies.ezzine.fileorchestrator.service;

import com.pictet.technologies.ezzine.fileorchestrator.domain.IssuerEntity;
import com.pictet.technologies.ezzine.fileorchestrator.domain.ProcessEntity;
import com.pictet.technologies.ezzine.fileorchestrator.domain.UkListOfExemptedSharesEntity;
import com.pictet.technologies.ezzine.fileorchestrator.repository.IssuerRepository;
import com.pictet.technologies.ezzine.fileorchestrator.repository.UkListOfExemptedSharesRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@AllArgsConstructor
public class UkListService {

    private final ProcessService processService;
    private final IssuerRepository issuerRepository;
    private final UkListOfExemptedSharesRepository ukListRepository;

    public void importUkListOfExemptedShares(MultipartFile file) throws IOException {
        var processEntity = this.processService.startProcess(file.getOriginalFilename());

        Workbook workbook = new XSSFWorkbook(file.getInputStream());

        List<IssuerEntity> issuers = extractEntitiesOfIssuer(workbook);
        extractEntitiesOfUkList(workbook, issuers, processEntity);

        this.processService.endProcess(processEntity);
    }

    private void readHeader(Iterator<Row> iterator) {
        if (iterator.hasNext()) {
            iterator.next();
        }
    }

    private LocalDateTime convertToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]z");
        return LocalDateTime.parse(date, formatter);
    }

    public List<IssuerEntity> extractEntitiesOfIssuer(Workbook workbook) {
        List<IssuerEntity> issuerEntities = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(1);
        Iterator<Row> iterator = sheet.rowIterator();
        readHeader(iterator);
        while (iterator.hasNext()) {
            Row row = iterator.next();
            Cell isinCell = row.getCell(0);
            Cell nameCell = row.getCell(1);
            Cell dateCell = row.getCell(2);
            if (isinCell != null) {
                var isin = isinCell.getStringCellValue();
                var name = nameCell.getStringCellValue();
                var dateSQL = dateCell.getLocalDateTimeCellValue().toLocalDate();
                var entity = IssuerEntity.builder()
                        .isin(isin)
                        .name(name)
                        .date(dateSQL)
                        .build();
                issuerEntities.add(entity);
            }
        }

        this.issuerRepository.saveAll(issuerEntities);

        return issuerEntities;
    }

    public void extractEntitiesOfUkList(Workbook workbook, List<IssuerEntity> issuers, ProcessEntity processEntity) {
        List<UkListOfExemptedSharesEntity> ukListEntities = new ArrayList<>();

        Map<String, IssuerEntity> issuerByIsin = new HashMap<>();
        for (IssuerEntity issuerEntity : issuers) {
            issuerByIsin.put(issuerEntity.getIsin(), issuerEntity);
        }

        Sheet sheet = workbook.getSheetAt(2);
        Iterator<Row> iterator = sheet.rowIterator();
        readHeader(iterator);
        while (iterator.hasNext()) {
            Row row = iterator.next();
            var rootCell = row.getCell(0);
            var isinCell = row.getCell(1);
            var countryCodeCell = row.getCell(2);
            var relevantAuthorityCell = row.getCell(3);
            var modificationDateStrCell = row.getCell(4);
            var versionCell = row.getCell(5);
            var nameCell = row.getCell(6);
            var excelIdCell = row.getCell(8);
            var statusCell = row.getCell(9);
            var timeStampCell = row.getCell(10);
            var exemptionStartDateCell = row.getCell(11);

            if (rootCell != null) {
                var root = rootCell.getNumericCellValue();
                var isin = isinCell.getStringCellValue();
                var countryCode = countryCodeCell.getStringCellValue();
                var relevantAuthority = relevantAuthorityCell.getStringCellValue();
                var modificationDateStr = modificationDateStrCell.getLocalDateTimeCellValue().toLocalDate();
                var version = versionCell.getNumericCellValue();
                var name = nameCell.getStringCellValue();
                var excelId = excelIdCell.getStringCellValue();
                var status = statusCell.getStringCellValue();
                var timeStamp = timeStampCell.getLocalDateTimeCellValue().toLocalDate();
                var exemptionStartDateStr = convertToLocalDateTime(exemptionStartDateCell.getStringCellValue());

                var entityOfUkList = UkListOfExemptedSharesEntity.builder()
                        .root((int) root)
                        .issuer(issuerByIsin.get(isin))
                        .countryCode(countryCode)
                        .relevantAuthority(relevantAuthority)
                        .modificationDateStr(modificationDateStr)
                        .version((long) version)
                        .name(name)
                        .excelId(excelId)
                        .status(status)
                        .timestamp(timeStamp)
                        .exemptionStartDateStr(exemptionStartDateStr)
                        .process(processEntity)
                        .build();
                ukListEntities.add(entityOfUkList);
            }
        }

        this.ukListRepository.saveAll(ukListEntities);
    }
}
