package com.jhona.appsubirarchivos.services;

import com.jhona.appsubirarchivos.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public interface IFileService {
    FileEntity store(MultipartFile file) throws IOException;
    Optional<FileEntity> getFile(UUID id) throws FileNotFoundException;
}
