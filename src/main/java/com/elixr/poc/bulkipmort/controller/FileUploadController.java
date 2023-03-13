package com.elixr.poc.bulkipmort.controller;

import com.elixr.poc.bulkipmort.service.FileUploadService;
import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.util.MessagesUtil;
import com.elixr.poc.rest.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    /**
     * Uploading a csv file and showing a message.
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadfile")
    public ResponseEntity uploadFile(@RequestParam("file") @Valid MultipartFile file) {
        boolean success = fileUploadService.uploadFile(file);
        SuccessResponse successResponse = SuccessResponse.builder().success(success)
                .successMessage(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_UPLOADED_SUCCESSFULLY.getKey())).build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
