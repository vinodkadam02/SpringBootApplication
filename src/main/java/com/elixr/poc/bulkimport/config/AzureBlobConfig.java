package com.elixr.poc.bulkimport.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobConfig {
    @Value("${azure.storage.connection-string}")
    private String connectionString;
    @Value("${azure.storage.container-name}")
    private String containerName;

    public BlobContainerClient blobContainerClient() {
        return new BlobContainerClientBuilder().containerName(containerName)
                .connectionString(connectionString).buildClient();
    }
}
