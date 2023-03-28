package com.elixr.poc.bulkimport.service.reader;

import com.elixr.poc.bulkimport.dto.Patient;

import com.elixr.poc.bulkimport.response.GenericResponse;
import com.elixr.poc.bulkimport.response.RowResponse;
import com.elixr.poc.bulkimport.service.FileOperationService;
import com.elixr.poc.common.enums.FileOperationEnum;
import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Service layer for fileUpload operation.
 */
@Slf4j
@Service
public class FileReader {
    private final FileOperationService fileOperationService;

    public FileReader(FileOperationService fileOperationService) {
        this.fileOperationService = fileOperationService;
    }

    String stringValue = null;

    /**
     * Reading the Excel file and performing the Operation based on Action.
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public GenericResponse readData(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        String[] patientKey = {FileOperationEnum.ACTION.getFileKey(), FileOperationEnum.PATIENT_ID.getFileKey(),
                FileOperationEnum.PATIENT_FIRSTNAME.getFileKey(), FileOperationEnum.PATIENT_LASTNAME.getFileKey(),
                FileOperationEnum.PATIENT_AGE.getFileKey(), FileOperationEnum.PATIENT_ADDRESS.getFileKey(),
                FileOperationEnum.DOCTOR_ID.getFileKey()};
        HashMap<String, String> columnHeader = new HashMap<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            Cell cell;
            List<RowResponse> rowResponse = new ArrayList<>();
            Iterator rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                if (row.getRowNum() != 0) {
                    int headerCount = 0;
                    for (int cellNumber = 0; cellNumber < row.getLastCellNum(); cellNumber++) {
                        cell = row.getCell(cellNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        stringValue = checkCellType(cell);
                        columnHeader.put(Arrays.stream(patientKey).toList().get(headerCount), stringValue);
                        headerCount++;
                    }
                    Patient patient;
                    if (StringUtils.isEmpty(columnHeader.get(FileOperationEnum.PATIENT_AGE.getFileKey()))) {
                        patient = buildPatientWhenAgeIsEmpty(columnHeader);
                    } else {
                        patient = buildPatient(columnHeader);
                    }
                    rowResponse.add(performAction(patient, columnHeader, row.getRowNum() + 1));
                }
            }
            return GenericResponse.builder().status(FileOperationEnum.SUCCESS.getFileKey()).data(rowResponse).build();
        } catch (Exception exception) {
            return GenericResponse.builder().status(FileOperationEnum.FAILURE.getFileKey()).data(null).build();
        }
    }

    /**
     * Check cell type like is it String or Numeric.
     * @param cell
     * @return
     */
    private String checkCellType(Cell cell) {
        if (CellType.STRING == cell.getCellType() || CellType.BLANK == cell.getCellType()) {
            stringValue = cell.getStringCellValue();
        } else if (CellType.NUMERIC == cell.getCellType() || CellType.BLANK == cell.getCellType()) {
            stringValue = String.valueOf((int) cell.getNumericCellValue());
        }
        return stringValue;
    }

    /**
     * Building the Patient object from columnHeader.
     * Calling the respective methods based on the action.
     *
     * @param columnHeader
     * @return
     */
    private Patient buildPatient(HashMap<String, String> columnHeader) {
        String doctorId = columnHeader.get(FileOperationEnum.DOCTOR_ID.getFileKey());
        List<String> doctorList = new ArrayList<>();
        doctorList.add(doctorId);
        return Patient.builder()
                .patientId(columnHeader.get(FileOperationEnum.PATIENT_ID.getFileKey()))
                .patientFirstName(columnHeader.get(FileOperationEnum.PATIENT_FIRSTNAME.getFileKey()))
                .patientLastName(columnHeader.get(FileOperationEnum.PATIENT_LASTNAME.getFileKey()))
                .patientAge(Integer.parseInt(columnHeader.get(FileOperationEnum.PATIENT_AGE.getFileKey())))
                .patientAddress(columnHeader.get(FileOperationEnum.PATIENT_ADDRESS.getFileKey()))
                .doctorId(doctorList).build();
    }

    /**
     * Building the Patient object when age is not provided in Excel.
     *
     * @param columnHeader
     * @return
     */
    private Patient buildPatientWhenAgeIsEmpty(HashMap<String, String> columnHeader) {
        String doctorId = columnHeader.get(FileOperationEnum.DOCTOR_ID.getFileKey());
        List<String> doctorList = new ArrayList<>();
        doctorList.add(doctorId);
        Patient patient = Patient.builder()
                .patientId(columnHeader.get(FileOperationEnum.PATIENT_ID.getFileKey()))
                .patientFirstName(columnHeader.get(FileOperationEnum.PATIENT_FIRSTNAME.getFileKey()))
                .patientLastName(columnHeader.get(FileOperationEnum.PATIENT_LASTNAME.getFileKey()))
                .patientAddress(columnHeader.get(FileOperationEnum.PATIENT_ADDRESS.getFileKey()))
                .doctorId(doctorList).build();
        return patient;
    }

    /**
     * Performing the Action Operation based on the Excel actions.
     *
     * @param patient
     * @param columnHeader
     * @return
     */
    private RowResponse performAction(Patient patient, HashMap<String, String> columnHeader, int row) {
        if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_ADD_OPERATION.getKey()).equals(columnHeader
                .get(FileOperationEnum.ACTION.getFileKey()))) {
            return fileOperationService.performAddPatient(patient, row);
        } else if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_UPDATE_OPERATION.getKey()).equals(columnHeader
                .get(FileOperationEnum.ACTION.getFileKey()))) {
            return fileOperationService.performUpdatePatient(patient, row);
        } else if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_DELETE_OPERATION.getKey()).equals(columnHeader
                .get(FileOperationEnum.ACTION.getFileKey()))) {
            return fileOperationService.performDeletePatient(patient, row);
        } else if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_ASSIGN_NEW_DOCTOR.getKey()).equals(columnHeader
                .get(FileOperationEnum.ACTION.getFileKey()))) {
            return fileOperationService.performAssignNewDoctor(patient, row);
        } else if (MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_REMOVE_DOCTOR.getKey()).equals(columnHeader
                .get(FileOperationEnum.ACTION.getFileKey()))) {
            return fileOperationService.performRemoveDoctor(patient, row);
        }
        return null;
    }
}
