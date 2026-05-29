package com.jhona.appsubirarchivos.services;

import com.jhona.appsubirarchivos.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    FileEntity store(MultipartFile file) throws IOException;
}
