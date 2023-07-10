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

        int sheetOfIssuer = 1;
        int columnIndexIsin = 0;
        int columnIndexName = 1;
        int columnIndexDate = 2;

        Sheet sheet = workbook.getSheetAt(sheetOfIssuer);
        Iterator<Row> iterator = sheet.rowIterator();
        readHeader(iterator);

        while (iterator.hasNext()) {
            Row row = iterator.next();
            Cell isinCell = row.getCell(columnIndexIsin);
            Cell nameCell = row.getCell(columnIndexName);
            Cell dateCell = row.getCell(columnIndexDate);
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

        int sheetOfUkList = 2;
        int columnIndexRoot = 0;
        int columnIndexIsin = 1;
        int columnIndexCountryCode = 2;
        int columnIndexRelevantAuthority = 3;
        int columnModificationDate = 4;
        int columnVersion = 5;
        int columnName = 6;
        int columnExcelId = 8;
        int columnStatus = 9;
        int columnTimeStamp = 10;
        int columnExemptionStartDate = 11;

        Sheet sheet = workbook.getSheetAt(sheetOfUkList);
        Iterator<Row> iterator = sheet.rowIterator();
        readHeader(iterator);

        while (iterator.hasNext()) {
            Row row = iterator.next();
            var rootCell = row.getCell(columnIndexRoot);
            var isinCell = row.getCell(columnIndexIsin);
            var countryCodeCell = row.getCell(columnIndexCountryCode);
            var relevantAuthorityCell = row.getCell(columnIndexRelevantAuthority);
            var modificationDateStrCell = row.getCell(columnModificationDate);
            var versionCell = row.getCell(columnVersion);
            var nameCell = row.getCell(columnName);
            var excelIdCell = row.getCell(columnExcelId);
            var statusCell = row.getCell(columnStatus);
            var timeStampCell = row.getCell(columnTimeStamp);
            var exemptionStartDateCell = row.getCell(columnExemptionStartDate);

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
