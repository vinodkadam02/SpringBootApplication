package com.elixr.poc.bulkimport.service;

import com.azure.storage.blob.BlobClient;
import com.elixr.poc.bulkimport.config.AzureBlobConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AzureBlobFileService {
    private final AzureBlobConfig azureBlobConfig;

    public AzureBlobFileService(AzureBlobConfig azureBlobConfig) {
        this.azureBlobConfig = azureBlobConfig;
    }

    public void uploadFileToAzure(MultipartFile file) throws IOException {
        BlobClient blob = azureBlobConfig.blobContainerClient().getBlobClient(file.getOriginalFilename());
        blob.upload(file.getInputStream(), file.getSize(), true);
    }
}
