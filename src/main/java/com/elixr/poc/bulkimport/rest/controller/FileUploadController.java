package com.elixr.poc.bulkimport.rest.controller;

import com.elixr.poc.bulkimport.service.reader.FileReader;
import com.elixr.poc.common.enums.MessagesKeyEnum;
import com.elixr.poc.common.exception.ExtensionException;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.rest.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileUploadController {

    private final FileReader fileUploadService;

    public FileUploadController(FileReader fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    /**
     * Uploading a csv file and showing a message.
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/uploadfile")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.getOriginalFilename().endsWith(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_EXTENSION.getKey()))) {
            throw new ExtensionException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_EXTENSION_ERROR_MESSAGE.getKey()));
        }
        fileUploadService.readData(multipartFile);
        SuccessResponse successResponse = SuccessResponse.builder().success(true)
                .successMessage(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_UPLOADED_SUCCESSFULLY.getKey())).build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
