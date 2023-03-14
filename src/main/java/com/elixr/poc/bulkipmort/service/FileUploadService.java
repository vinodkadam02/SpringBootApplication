package com.elixr.poc.bulkipmort.service;

import com.elixr.poc.common.MessagesKeyEnum;
import com.elixr.poc.common.exception.ExtensionException;
import com.elixr.poc.common.util.MessagesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    /**
     * Accepting only .csv files.
     *
     * @param file
     * @return
     */
    public void uploadFile(MultipartFile file) {
        if (!file.getOriginalFilename().endsWith(".csv")) {
            throw new ExtensionException(MessagesUtil.getMessage(MessagesKeyEnum.ENTITY_FILE_EXTENSION.getKey()));
        }
    }
}
