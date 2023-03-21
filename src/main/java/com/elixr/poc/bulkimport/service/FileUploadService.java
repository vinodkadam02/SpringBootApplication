package com.elixr.poc.bulkimport.service;

import com.elixr.poc.bulkimport.dto.Patient;

import com.elixr.poc.common.enums.FileOperationEnum;
import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Service layer for fileUpload operation.
 */
@Slf4j
@Service
public class FileUploadService {
    private final FileOperationService fileOperationService;

    public FileUploadService(FileOperationService fileOperationService) {
        this.fileOperationService = fileOperationService;
    }

    /**
     * Reading the Excel file and performing the Operation based on Action.
     *
     * @param multipartFile
     * @throws IOException
     */
    public void readData(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String[] patientKey = {"Action", "PatientId", "PatientFirstName", "PatientLastName", "PatientAge", "PatientAddress", "DoctorId"};
        HashMap<String, String> columnHeader = new HashMap<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() != 0) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    int headerCount = 0;
                    String stringValue = null;
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (CellType.STRING == cell.getCellType()) {
                            stringValue = cell.getStringCellValue();
                        } else if (CellType.NUMERIC == cell.getCellType()) {
                            stringValue = String.valueOf((int) cell.getNumericCellValue());
                        }
                        columnHeader.put(Arrays.stream(patientKey).toList().get(headerCount), stringValue);
                        headerCount++;
                    }
                    buildPatient(columnHeader);
                }
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }

    /**
     * Building an Patient object from columnHeader.
     * Calling the respective methods based on the action.
     *
     * @param columnHeader
     */
    private void buildPatient(HashMap<String, String> columnHeader) {
        Patient patient = Patient.builder()
                .patientId(columnHeader.get("PatientId"))
                .patientFirstName(columnHeader.get("PatientFirstName"))
                .patientLastName(columnHeader.get("PatientLastName"))
                .patientAge(Integer.parseInt(columnHeader.get("PatientAge")))
                .patientAddress(columnHeader.get("PatientAddress"))
                .doctorId(columnHeader.get("DoctorId")).build();
        if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_ADD_OPERATION.getKey()).equals(columnHeader.get("Action"))) {
            fileOperationService.performAddPatient(patient);
        } else if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_UPDATE_OPERATION.getKey()).equals(columnHeader.get("Action"))) {
            fileOperationService.performUpdatePatient(patient.getPatientId(), patient);
        } else if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_DELETE_OPERATION.getKey()).equals(columnHeader.get("Action"))) {
            fileOperationService.performDeletePatient(patient.getPatientId());
        }
    }
}
