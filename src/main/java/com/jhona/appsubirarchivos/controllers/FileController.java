package com.jhona.appsubirarchivos.controllers;

import com.jhona.appsubirarchivos.dtos.res.ResponseFile;
import com.jhona.appsubirarchivos.dtos.res.ResponseMessage;
import com.jhona.appsubirarchivos.entity.FileEntity;
import com.jhona.appsubirarchivos.services.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/file-manager")
@RequiredArgsConstructor
public class FileController {

    private final IFileService fileService;


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> subirArchivo(
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        fileService.store(file);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseMessage("Archivo subido correctamente"));
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(
            @PathVariable("id") UUID id
    ) throws Exception {
        FileEntity fileEntity = fileService.getFile(id).get();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment : filename\"" + fileEntity.getNombre()
                        + "\"")
                .body(fileEntity.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getAllFiles() {
        List<ResponseFile> files = fileService.getAllFiles();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(files);
    }

}
