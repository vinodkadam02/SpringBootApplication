package com.elixr.poc.bulkimport.service;

import com.elixr.poc.bulkimport.response.GenericResponse;
import com.elixr.poc.bulkimport.service.reader.FileReader;
import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.exception.ExtensionException;
import com.elixr.poc.common.util.MessagesUtil;
import lombok.extern.slf4j.Slf4j;;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Class for performing actions.
 */
@Slf4j
@Service
public class PatientService {
    private final FileReader fileReader;

    public PatientService(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public GenericResponse readFile(MultipartFile multipartFile){
        if (!multipartFile.getOriginalFilename().endsWith(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_EXTENSION.getKey()))) {
            throw new ExtensionException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_EXTENSION_ERROR_MESSAGE.getKey()));
        }
        return fileReader.readExcelFileData(multipartFile);
    }
}
