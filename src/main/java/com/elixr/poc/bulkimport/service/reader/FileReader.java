package com.elixr.poc.bulkimport.service.reader;

import com.elixr.poc.bulkimport.dto.Patient;

import com.elixr.poc.bulkimport.response.GenericResponse;
import com.elixr.poc.bulkimport.response.RowResponse;
import com.elixr.poc.bulkimport.service.PatientOperations;
import com.elixr.poc.common.enums.FileHeadersEnum;
import com.elixr.poc.common.enums.FileMessageEnum;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * Service layer for fileUpload operation.
 */
@Slf4j
@Service
public class FileReader {
    private final PatientOperations patientOperations;

    public FileReader(PatientOperations patientOperations) {
        this.patientOperations = patientOperations;
    }

    String stringValue = null;

    /**
     * Reading the Excel file and performing the Operation based on Action.
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public GenericResponse readExcelFileData(MultipartFile multipartFile) {
        HashMap<String, String> headerValues = new HashMap<>();
        try {
            Sheet sheet = creatWorkBook(multipartFile);
            Row row;
            List<RowResponse> rowResponse = new ArrayList<>();
            Iterator rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                if (row.getRowNum() != 0) {
                    iterateThroughCell(headerValues, row);
                    Patient patient;
                    if (StringUtils.isEmpty(headerValues.get(FileHeadersEnum.PATIENT_AGE.getKey()))) {
                        patient = buildPatientWhenAgeIsEmpty(headerValues);
                    } else {
                        patient = buildPatient(headerValues);
                    }
                    rowResponse.add(patientOperations.performAction(patient, headerValues));
                }
                headerValues.put(FileHeadersEnum.ACTION.getKey(), "");
            }
            return GenericResponse.builder().status(FileMessageEnum.SUCCESS.getFileKey()).data(rowResponse).build();
        } catch (Exception exception) {
            return GenericResponse.builder().status(FileMessageEnum.FAILURE.getFileKey()).data(null).build();
        }
    }

    private XSSFSheet creatWorkBook(MultipartFile multipartFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        return workbook.getSheetAt(0);
    }

    private void iterateThroughCell(HashMap<String, String> headerValues, Row row) {
        FileHeadersEnum[] enums = FileHeadersEnum.values();
        int currentHeaderIndex = 0;
        for (int cellNumber = 0; cellNumber < row.getLastCellNum(); cellNumber++) {
            stringValue = getCellValue(row, cellNumber);
            headerValues.put(Arrays.stream(enums).map(m -> m.getKey()).toList().get(currentHeaderIndex), stringValue);
            currentHeaderIndex++;
        }
    }

    private String getCellValue(Row row, int columnIndex) {
        DataFormatter df = new DataFormatter();
        String cellValue = df.formatCellValue(row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
        return StringUtils.isNotBlank(cellValue) ? cellValue : null;
    }

    /**
     * Building the Patient object from columnHeader.
     * Calling the respective methods based on the action.
     *
     * @param columnHeader
     * @return
     */
    private Patient buildPatient(HashMap<String, String> columnHeader) {
        String doctorId = columnHeader.get(FileHeadersEnum.DOCTOR_ID.getKey());
        List<String> doctorList = new ArrayList<>();
        doctorList.add(doctorId);
        return Patient.builder()
                .patientId(columnHeader.get(FileHeadersEnum.PATIENT_ID.getKey()))
                .patientFirstName(columnHeader.get(FileHeadersEnum.PATIENT_FIRSTNAME.getKey()))
                .patientLastName(columnHeader.get(FileHeadersEnum.PATIENT_LASTNAME.getKey()))
                .patientAge(columnHeader.get(FileHeadersEnum.PATIENT_AGE.getKey()))
                .patientAddress(columnHeader.get(FileHeadersEnum.PATIENT_ADDRESS.getKey()))
                .doctorList(doctorList).build();
    }

    /**
     * Building the Patient object when age is not provided in Excel.
     *
     * @param columnHeader
     * @return
     */
    private Patient buildPatientWhenAgeIsEmpty(HashMap<String, String> columnHeader) {
        String doctorId = columnHeader.get(FileHeadersEnum.DOCTOR_ID.getKey());
        List<String> doctorList = new ArrayList<>();
        doctorList.add(doctorId);
        return Patient.builder()
                .patientId(columnHeader.get(FileHeadersEnum.PATIENT_ID.getKey()))
                .patientFirstName(columnHeader.get(FileHeadersEnum.PATIENT_FIRSTNAME.getKey()))
                .patientLastName(columnHeader.get(FileHeadersEnum.PATIENT_LASTNAME.getKey()))
                .patientAddress(columnHeader.get(FileHeadersEnum.PATIENT_ADDRESS.getKey()))
                .doctorList(doctorList).build();
    }
}
