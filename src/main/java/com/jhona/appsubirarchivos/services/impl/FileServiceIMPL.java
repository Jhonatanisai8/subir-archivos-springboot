package com.jhona.appsubirarchivos.services.impl;

import com.jhona.appsubirarchivos.dtos.res.ResponseFile;
import com.jhona.appsubirarchivos.entity.FileEntity;
import com.jhona.appsubirarchivos.repo.FileRepository;
import com.jhona.appsubirarchivos.services.IFileService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceIMPL
        implements IFileService {

    private final FileRepository fileRepository;

    @Override
    public FileEntity store(MultipartFile file) throws IOException {
        if (file.getContentType() == null) {
            throw new IOException("El archivo no tiene un tipo de contenido");
        }
        String nombreArchivo = StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity fileEntity = FileEntity.builder()
                .nombre(nombreArchivo)
                .tipo(file.getContentType())
                .data(file.getBytes())
                .build();
        return fileRepository.save(fileEntity);
    }

    @Override
    public Optional<FileEntity> getFile(UUID id) throws FileNotFoundException {
        Optional<FileEntity> fileEntity = fileRepository.findById(id);
        if (fileEntity.isEmpty()) throw new FileNotFoundException("Archivo no encontrado");
        return fileEntity;
    }

    @Override
    public List<ResponseFile> getAllFiles() {
        List<ResponseFile> files = fileRepository.findAll()
                .stream()
                .map(fileEntity -> {
                    String archivoDescargaURI = obtenerURIfile(fileEntity);
                    return construirResponse(fileEntity, archivoDescargaURI);
                }).collect(Collectors.toList()) ;
        return files;
    }

    private static ResponseFile construirResponse(FileEntity fileEntity, String archivoDescargaURI) {
        return ResponseFile.builder()
                .nombre(fileEntity.getNombre())
                .url(archivoDescargaURI)
                .tipo(fileEntity.getTipo())
                .tamanio(fileEntity.getData().length)
                .build();
    }

    private static @NonNull String obtenerURIfile(FileEntity fileEntity) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/fileManager/files/")
                .path(fileEntity.getId().toString())
                .toUriString();
    }
}
