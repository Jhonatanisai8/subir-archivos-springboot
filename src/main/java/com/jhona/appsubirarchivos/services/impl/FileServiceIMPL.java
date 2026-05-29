package com.jhona.appsubirarchivos.services.impl;

import com.jhona.appsubirarchivos.entity.FileEntity;
import com.jhona.appsubirarchivos.repo.FileRepository;
import com.jhona.appsubirarchivos.services.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileServiceIMPL
        implements IFileService {

    private final FileRepository fileRepository;

    @Override
    public FileEntity store(MultipartFile file) throws IOException {
        String nombreArchivo = StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity fileEntity = FileEntity.builder()
                .nombre(nombreArchivo)
                .tipo(file.getContentType())
                .data(file.getBytes())
                .build();
        return fileRepository.save(fileEntity);
    }
}
