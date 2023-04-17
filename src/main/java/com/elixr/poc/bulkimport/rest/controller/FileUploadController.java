package com.elixr.poc.bulkimport.rest.controller;

import com.elixr.poc.bulkimport.response.GenericResponse;
import com.elixr.poc.bulkimport.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    private final PatientService patientService;

    public FileUploadController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Uploading a csv file and showing a message.
     */
    @PostMapping("/uploadfile")
    public ResponseEntity<GenericResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        GenericResponse genericResponse = patientService.readFile(multipartFile);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
}
