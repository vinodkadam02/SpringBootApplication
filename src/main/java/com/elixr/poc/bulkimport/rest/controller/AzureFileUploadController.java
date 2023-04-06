package com.elixr.poc.bulkimport.rest.controller;

import com.elixr.poc.bulkimport.service.AzureBlobFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AzureFileUploadController {
    private final AzureBlobFileService azureBlobFileService;

    public AzureFileUploadController(AzureBlobFileService azureBlobFileService) {
        this.azureBlobFileService = azureBlobFileService;
    }

    @PostMapping(path = "/azure-upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        azureBlobFileService.uploadFileToAzure(multipartFile);
        return new ResponseEntity("File Uploaded into the Azure cloud", HttpStatus.OK);
    }
}